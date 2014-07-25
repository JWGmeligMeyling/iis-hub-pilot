package gov.hhs.onc.iishubpilot.ws.hub.impl;

import gov.hhs.onc.iishubpilot.net.utils.HubUrnUtils;
import gov.hhs.onc.iishubpilot.ws.HubHttpHeaders;
import gov.hhs.onc.iishubpilot.ws.HubHttpHeaders.HubHttpHeaderPredicate;
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
import gov.hhs.onc.iishubpilot.xml.HubXmlNs;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.jws.WebService;
import javax.xml.ws.Holder;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.ws.addressing.AddressingProperties;
import org.apache.cxf.ws.addressing.AttributedURIType;
import org.apache.cxf.ws.addressing.ContextUtils;
import org.apache.cxf.ws.addressing.JAXWSAConstants;
import org.springframework.beans.factory.annotation.Autowired;

@WebService(portName = HubWsNames.PORT_HUB, serviceName = HubWsNames.SERVICE_HUB, targetNamespace = HubXmlNs.IIS_HUB)
public class IisHubServiceImpl extends AbstractIisService implements IisHubService, IisHubPortType {
    @Autowired
    private ObjectFactory objFactoryIisHub;

    private String clientIisBeanName;

    public IisHubServiceImpl(String clientIisBeanName) {
        this.clientIisBeanName = clientIisBeanName;
    }

    @Override
    public void submitSingleMessage(SubmitSingleMessageRequestType reqParams, HubRequestHeaderType hubReqHeader,
        Holder<SubmitSingleMessageResponseType> respParams, Holder<HubResponseHeaderType> hubRespHeader) throws DestinationConnectionFault, HubClientFault,
        MessageTooLargeFault, SecurityFault, UnknownDestinationFault {
        Pair<SoapMessage, SoapMessage> msgs = this.getMessages();
        Pair<SubmitSingleMessageResponseType, HubResponseHeaderType> respPair = this.submitSingleMessageInternal(msgs, reqParams, hubReqHeader);

        this.auditor.auditSubmitSingleMessage(msgs, reqParams, hubReqHeader, (respParams.value = respPair.getLeft()),
            (hubRespHeader.value = respPair.getRight()));
    }

    @SuppressWarnings({ "unchecked" })
    private Pair<SubmitSingleMessageResponseType, HubResponseHeaderType> submitSingleMessageInternal(Pair<SoapMessage, SoapMessage> msgs,
        SubmitSingleMessageRequestType reqParams, HubRequestHeaderType hubReqHeader) {
        SoapMessage reqMsg = msgs.getLeft();
        
        IisPortType clientIisService = this.appContext.getBean(this.clientIisBeanName, IisPortType.class);
        Client clientIisServiceObj = ClientProxy.getClient(clientIisService);
        
        Map<String, Object> clientReqContext = clientIisServiceObj.getRequestContext();
        clientReqContext.put(Message.ENDPOINT_ADDRESS, "https://localhost:18443/iis-hub-pilot/test/1/IISService");
        
        AddressingProperties reqAddrProps = ContextUtils.retrieveMAPs(reqMsg, true, false);

        if (reqAddrProps != null) {
            AddressingProperties clientReqAddrProps = new AddressingProperties();
            AttributedURIType reqAddrPropValueType;

            if ((reqAddrPropValueType = reqAddrProps.getAction()) != null) {
                clientReqAddrProps.setAction(ContextUtils.getAttributedURI((HubXmlNs.IIS + HubUrnUtils.DELIM + HubWsNames.PORT_TYPE + StringUtils.removeStart(
                    reqAddrPropValueType.getValue(), HubXmlNs.IIS_HUB + HubUrnUtils.DELIM + HubWsNames.PORT_TYPE_HUB))));
            }

            if ((reqAddrPropValueType = reqAddrProps.getMessageID()) != null) {
                clientReqAddrProps.setMessageID(reqAddrPropValueType);
            }

            clientReqContext.put(JAXWSAConstants.CLIENT_ADDRESSING_PROPERTIES, clientReqAddrProps);
        }

        String destId = hubReqHeader.getDestinationId(), destUri = clientIisServiceObj.getEndpoint().getEndpointInfo().getAddress();
        Pair<Map<String, List<String>>, Map<String, List<String>>> httpHeaders = this.getHttpHeaders();

        final Map<String, List<String>> hubHttpHeaders =
            MapUtils.putAll(new TreeMap<String, List<String>>(String.CASE_INSENSITIVE_ORDER),
                CollectionUtils.select(httpHeaders.getLeft().entrySet(), HubHttpHeaderPredicate.INSTANCE).toArray());
        hubHttpHeaders.put(HubHttpHeaders.DEST_ID, Arrays.asList(destId));
        hubHttpHeaders.put(HubHttpHeaders.DEST_URI, Arrays.asList(destUri));

        clientIisServiceObj.getEndpoint().getOutInterceptors().add(new AbstractPhaseInterceptor<Message>(Phase.PRE_STREAM) {
            @Override
            public void handleMessage(Message msg) throws Fault {
                ((Map<String, List<String>>) msg.get(Message.PROTOCOL_HEADERS)).putAll(hubHttpHeaders);
            }
        });

        HubResponseHeaderType hubRespHeader = this.objFactoryIisHub.createHubResponseHeaderType();
        hubRespHeader.setDestinationId(destId);
        hubRespHeader.setDestinationUri(destUri);

        return new MutablePair<>(clientIisService.submitSingleMessage(reqParams), hubRespHeader);
    }
}
