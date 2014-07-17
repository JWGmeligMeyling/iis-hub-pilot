package gov.hhs.onc.iishubpilot.ws.impl;

import gov.hhs.onc.iishubpilot.audit.HubAuditor;
import gov.hhs.onc.iishubpilot.ws.ConnectivityTestRequestType;
import gov.hhs.onc.iishubpilot.ws.ConnectivityTestResponseType;
import gov.hhs.onc.iishubpilot.ws.IisService;
import gov.hhs.onc.iishubpilot.ws.UnsupportedOperationFault;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.xml.ws.WebServiceContext;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.jaxws.context.WrappedMessageContext;
import org.apache.cxf.message.Exchange;
import org.apache.cxf.message.Message;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public abstract class AbstractIisService implements IisService {
    @Autowired
    protected ObjectFactory objFactoryIis;

    @Autowired
    @SuppressWarnings({ "SpringJavaAutowiringInspection" })
    protected HubAuditor auditor;

    @Resource
    @SuppressWarnings({ "SpringJavaAutowiringInspection" })
    protected WebServiceContext wsContext;

    protected AbstractApplicationContext appContext;

    @Override
    public ConnectivityTestResponseType connectivityTest(ConnectivityTestRequestType reqParams) throws UnsupportedOperationFault {
        ConnectivityTestResponseType respParams = this.connectivityTestInternal(reqParams);

        this.auditor.auditConnectivityTest(this.getMessages(), reqParams, respParams);

        return respParams;
    }

    protected ConnectivityTestResponseType connectivityTestInternal(ConnectivityTestRequestType reqParams) {
        ConnectivityTestResponseType respParams = this.objFactoryIis.createConnectivityTestResponseType();
        respParams.setEchoBack(reqParams.getEchoBack());

        return respParams;
    }

    @SuppressWarnings({ "unchecked" })
    protected Pair<Map<String, List<String>>, Map<String, List<String>>> getHttpHeaders() {
        Pair<SoapMessage, SoapMessage> msgs = this.getMessages();
        SoapMessage respMsg;

        return new MutablePair<>(((Map<String, List<String>>) msgs.getLeft().get(Message.PROTOCOL_HEADERS)), ((Map<String, List<String>>) (((respMsg =
            msgs.getRight()) != null) ? respMsg.get(Message.PROTOCOL_HEADERS) : null)));
    }

    protected Pair<SoapMessage, SoapMessage> getMessages() {
        Exchange exchange = this.getMessageContext().getWrappedMessage().getExchange();

        return new MutablePair<>(((SoapMessage) exchange.getInMessage()), ((SoapMessage) exchange.getOutMessage()));
    }

    protected WrappedMessageContext getMessageContext() {
        return ((WrappedMessageContext) this.wsContext.getMessageContext());
    }

    @Override
    public void setApplicationContext(ApplicationContext appContext) throws BeansException {
        this.appContext = ((AbstractApplicationContext) appContext);
    }
}
