package gov.hhs.onc.iishubpilot.audit.impl;

import gov.hhs.onc.iishubpilot.audit.SubmitSingleMessageAuditEvent;
import gov.hhs.onc.iishubpilot.audit.SubmitSingleMessageAuditEventSender;
import javax.jms.Destination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component("auditEventSenderSubmitSingleMsg")
public class SubmitSingleMessageAuditEventSenderImpl extends AbstractHubAuditEventSender<SubmitSingleMessageAuditEvent> implements
    SubmitSingleMessageAuditEventSender {
    @Autowired
    public SubmitSingleMessageAuditEventSenderImpl(JmsTemplate jmsTemplate, Destination dest) {
        super(jmsTemplate, dest, SubmitSingleMessageAuditEvent.class);
    }
}
