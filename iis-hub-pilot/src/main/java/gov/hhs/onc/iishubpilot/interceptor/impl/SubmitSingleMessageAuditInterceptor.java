package gov.hhs.onc.iishubpilot.interceptor.impl;

import gov.hhs.onc.iishubpilot.audit.SubmitSingleMessageAuditEvent;
import javax.jms.Destination;
import org.springframework.jms.core.JmsTemplate;

public class SubmitSingleMessageAuditInterceptor extends AbstractAuditInterceptor<SubmitSingleMessageAuditEvent> {
    public SubmitSingleMessageAuditInterceptor(String phase, JmsTemplate jmsTemplate, Destination jmsDest) {
        super(phase, jmsTemplate, jmsDest);
    }
}
