package gov.hhs.onc.iishubpilot.interceptor.impl;

import gov.hhs.onc.iishubpilot.audit.SubmitSingleMessageAuditEvent;
import org.springframework.jms.core.JmsTemplate;

public class SubmitSingleMessageAuditInterceptor extends AbstractAuditInterceptor<SubmitSingleMessageAuditEvent> {
    public SubmitSingleMessageAuditInterceptor(String phase, JmsTemplate jmsTemplate, String jmsDestName) {
        super(phase, jmsTemplate, jmsDestName);
    }
}
