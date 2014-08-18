package gov.hhs.onc.iishubpilot.interceptor.impl;

import gov.hhs.onc.iishubpilot.audit.HubAuditEvent;
import gov.hhs.onc.iishubpilot.interceptor.AuditInterceptor;
import org.apache.cxf.binding.soap.SoapMessage;
import org.springframework.jms.core.JmsTemplate;

public abstract class AbstractAuditInterceptor<T extends HubAuditEvent> extends AbstractHubOperationInterceptor<SoapMessage> implements AuditInterceptor<T> {
    protected JmsTemplate jmsTemplate;
    protected String jmsDestName;

    protected AbstractAuditInterceptor(String phase, JmsTemplate jmsTemplate, String jmsDestName) {
        super(phase);

        this.jmsTemplate = jmsTemplate;
        this.jmsDestName = jmsDestName;
    }

    @Override
    protected void handleMessageInternal(SoapMessage msg) throws Exception {
        this.jmsTemplate.convertAndSend(this.jmsDestName, msg);
    }
}
