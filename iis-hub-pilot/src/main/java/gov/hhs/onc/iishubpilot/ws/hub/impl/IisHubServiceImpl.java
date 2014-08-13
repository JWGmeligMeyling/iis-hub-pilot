package gov.hhs.onc.iishubpilot.ws.hub.impl;

import gov.hhs.onc.iishubpilot.destination.HubDestination;
import gov.hhs.onc.iishubpilot.destination.HubDestinationRegistry;
import gov.hhs.onc.iishubpilot.jaxws.impl.HubJaxWsClientProxyFactoryBean;
import gov.hhs.onc.iishubpilot.ws.HubHttpHeaders;
import gov.hhs.onc.iishubpilot.ws.HubWsNames;
import gov.hhs.onc.iishubpilot.ws.IisPortType;
import gov.hhs.onc.iishubpilot.ws.MessageTooLargeFault;
import gov.hhs.onc.iishubpilot.ws.SecurityFault;
import gov.hhs.onc.iishubpilot.ws.SubmitSingleMessageRequestType;
import gov.hhs.onc.iishubpilot.ws.SubmitSingleMessageResponseType;
import gov.hhs.onc.iishubpilot.ws.hub.DestinationConnectionFault;
import gov.hhs.onc.iishubpilot.ws.hub.HubClientFault;
import gov.hhs.onc.iishubpilot.ws.hub.HubRequestHeaderType;
import gov.hhs.onc.iishubpilot.ws.hub.HubResponseHeaderType;
import gov.hhs.onc.iishubpilot.ws.hub.IisHubPortType;
import gov.hhs.onc.iishubpilot.ws.hub.IisHubService;
import gov.hhs.onc.iishubpilot.ws.hub.UnknownDestinationFault;
import gov.hhs.onc.iishubpilot.ws.impl.AbstractIisService;
import gov.hhs.onc.iishubpilot.ws.utils.HubWsUtils;
import gov.hhs.onc.iishubpilot.xml.HubXmlNs;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Holder;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.jaxws.context.WrappedMessageContext;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.transport.http.Headers;
import org.springframework.beans.factory.annotation.Autowired;

@WebService(portName = HubWsNames.PORT_HUB, serviceName = HubWsNames.SERVICE_HUB, targetNamespace = HubXmlNs.IIS_HUB)
public class IisHubServiceImpl extends AbstractIisService implements IisHubService, IisHubPortType {
    @Autowired
    private HubDestinationRegistry destReg;

    private ObjectFactory hubObjFactory;
    private String clientIisFactoryBeanName;

    public IisHubServiceImpl(String clientIisFactoryBeanName) {
        this.clientIisFactoryBeanName = clientIisFactoryBeanName;
    }

    @Override
    public void submitSingleMessage(SubmitSingleMessageRequestType reqParams, HubRequestHeaderType hubReqHeader,
        Holder<SubmitSingleMessageResponseType> respParams, Holder<HubResponseHeaderType> hubRespHeader) throws DestinationConnectionFault, HubClientFault,
        MessageTooLargeFault, SecurityFault, UnknownDestinationFault {
        Pair<SubmitSingleMessageResponseType, HubResponseHeaderType> respPair = this.submitSingleMessageInternal(reqParams, hubReqHeader);

        this.auditor.auditSubmitSingleMessage(this.wsContext, reqParams, hubReqHeader, (respParams.value = respPair.getLeft()),
            (hubRespHeader.value = respPair.getRight()));
    }

    private Pair<SubmitSingleMessageResponseType, HubResponseHeaderType> submitSingleMessageInternal(SubmitSingleMessageRequestType reqParams,
        HubRequestHeaderType hubReqHeader) throws DestinationConnectionFault, HubClientFault, MessageTooLargeFault, SecurityFault, UnknownDestinationFault {
        String destId = hubReqHeader.getDestinationId();
        HubDestination dest = this.destReg.findById(destId);

        if (dest == null) {
            throw new UnknownDestinationFault("IIS destination ID is not registered.", new UnknownDestinationFaultTypeImpl(destId));
        }

        WrappedMessageContext reqMsgContext = HubWsUtils.getMessageContext(this.wsContext);
        SoapMessage reqMsg = ((SoapMessage) reqMsgContext.getWrappedMessage());
        HttpServletRequest httpServletReq = HubWsUtils.getHttpServletRequest(reqMsgContext);
        URI destUri = dest.getUri();
        String destUriStr = destUri.toString();

        if (!destUri.isAbsolute()) {
            try {
                destUriStr =
                    (destUri =
                        destUri.relativize(new URI(httpServletReq.getScheme(), null, httpServletReq.getServerName(), httpServletReq.getServerPort(),
                            StringUtils.prependIfMissing(destUri.getPath(), httpServletReq.getContextPath()), null, null))).toString();
            } catch (URISyntaxException e) {
                throw new DestinationConnectionFault("Unable to relativize local IIS destination URI.", new DestinationConnectionFaultTypeImpl(destId,
                    destUriStr), e);
            }
        }

        HubJaxWsClientProxyFactoryBean clientIisFactoryBean = this.appContext.getBean(this.clientIisFactoryBeanName, HubJaxWsClientProxyFactoryBean.class);
        clientIisFactoryBean.setAddress(destUri.toString());

        Map<String, List<String>> httpReqHeaders =
            Headers.getSetProtocolHeaders(reqMsg).entrySet().stream()
                .filter(((Entry<String, List<String>> httpReqHeaderEntry) -> httpReqHeaderEntry.getKey().equalsIgnoreCase(HubHttpHeaders.DEV_ACTION_NAME)))
                .collect(Collectors.toMap(Entry::getKey, Entry::getValue));

        clientIisFactoryBean.getOutInterceptors().add(new AbstractPhaseInterceptor<Message>(Phase.PRE_STREAM) {
            @Override
            public void handleMessage(Message clientReqMsg) throws Fault {
                Headers.getSetProtocolHeaders(clientReqMsg).putAll(httpReqHeaders);
            }
        });

        IisPortType clientIis;

        try {
            clientIis = ((IisPortType) clientIisFactoryBean.getObject());
        } catch (Exception e) {
            throw new HubClientFault("Unable to build IIS destination web service client.", new HubClientFaultTypeImpl(destId, destUriStr), e);
        }

        return new MutablePair<>(clientIis.submitSingleMessage(reqParams), new HubResponseHeaderTypeImpl(destId, destUriStr));
    }

    @Override
    public ObjectFactory getHubObjectFactory() {
        return this.hubObjFactory;
    }

    @Override
    public void setHubObjectFactory(ObjectFactory hubObjFactory) {
        this.hubObjFactory = hubObjFactory;
    }
}
