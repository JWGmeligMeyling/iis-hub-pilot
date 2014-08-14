package gov.hhs.onc.iishubpilot.audit.impl;

import gov.hhs.onc.iishubpilot.audit.SubmitSingleMessageAuditDao;
import gov.hhs.onc.iishubpilot.audit.SubmitSingleMessageAuditEvent;
import gov.hhs.onc.iishubpilot.audit.SubmitSingleMessageAuditEventListener;
import gov.hhs.onc.iishubpilot.audit.SubmitSingleMessageAuditService;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("listenerAuditEventSubmitSingleMsg")
public class SubmitSingleMessageAuditEventListenerImpl extends
    AbstractHubAuditEventListener<SubmitSingleMessageAuditEvent, SubmitSingleMessageAuditDao, SubmitSingleMessageAuditService> implements
    SubmitSingleMessageAuditEventListener {
    @Autowired
    public SubmitSingleMessageAuditEventListenerImpl(SubmitSingleMessageAuditService auditService) {
        super(SubmitSingleMessageAuditEvent.class, auditService);
    }

    @Override
    protected void onMessageInternal(SubmitSingleMessageAuditEvent auditEvent, ObjectMessage msg, Session session) throws Exception {
        this.auditService.save(auditEvent);
    }
}
