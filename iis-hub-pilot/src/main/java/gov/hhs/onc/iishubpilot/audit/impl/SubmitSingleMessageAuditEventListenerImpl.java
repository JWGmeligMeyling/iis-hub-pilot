package gov.hhs.onc.iishubpilot.audit.impl;

import gov.hhs.onc.iishubpilot.audit.SubmitSingleMessageAuditEventDao;
import gov.hhs.onc.iishubpilot.audit.SubmitSingleMessageAuditEvent;
import gov.hhs.onc.iishubpilot.audit.SubmitSingleMessageAuditEventListener;
import gov.hhs.onc.iishubpilot.audit.SubmitSingleMessageAuditEventService;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("auditEventListenerSubmitSingleMsg")
public class SubmitSingleMessageAuditEventListenerImpl extends
    AbstractHubAuditEventListener<SubmitSingleMessageAuditEvent, SubmitSingleMessageAuditEventDao, SubmitSingleMessageAuditEventService> implements
    SubmitSingleMessageAuditEventListener {
    @Autowired
    public SubmitSingleMessageAuditEventListenerImpl(SubmitSingleMessageAuditEventService auditEventService) {
        super(SubmitSingleMessageAuditEvent.class, auditEventService);
    }

    @Override
    protected void onMessageInternal(SubmitSingleMessageAuditEvent auditEvent, ObjectMessage msg, Session session) throws Exception {
        this.auditEventService.save(auditEvent);
    }
}
