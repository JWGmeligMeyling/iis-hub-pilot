package gov.hhs.onc.iishubpilot.interceptor.impl;

import gov.hhs.onc.iishubpilot.audit.HubAuditEvent;
import gov.hhs.onc.iishubpilot.interceptor.AuditInterceptor;
import javax.jms.Destination;
import org.apache.cxf.binding.soap.SoapMessage;
import org.springframework.jms.core.JmsTemplate;

public abstract class AbstractAuditInterceptor<T extends HubAuditEvent> extends AbstractHubOperationInterceptor<SoapMessage> implements AuditInterceptor<T> {
    protected JmsTemplate jmsTemplate;
    protected Destination jmsDest;

    protected AbstractAuditInterceptor(String phase, JmsTemplate jmsTemplate, Destination jmsDest) {
        super(phase);

        this.jmsTemplate = jmsTemplate;
        this.jmsDest = jmsDest;
    }

    @Override
    protected void handleMessageInternal(SoapMessage msg) throws Exception {
        this.jmsTemplate.convertAndSend(this.jmsDest, msg);
    }
}
