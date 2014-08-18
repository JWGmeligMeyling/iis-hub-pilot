package gov.hhs.onc.iishubpilot.audit.impl;

import gov.hhs.onc.iishubpilot.audit.SubmitSingleMessageAuditEvent;
import gov.hhs.onc.iishubpilot.audit.SubmitSingleMessageAuditEventConverter;
import gov.hhs.onc.iishubpilot.audit.SubmitSingleMessageAuditEventDao;
import gov.hhs.onc.iishubpilot.audit.SubmitSingleMessageAuditEventListener;
import gov.hhs.onc.iishubpilot.audit.SubmitSingleMessageAuditEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("msgListenerAuditEventSubmitSingleMsg")
public class SubmitSingleMessageAuditEventListenerImpl
    extends
    AbstractHubAuditEventListener<SubmitSingleMessageAuditEvent, SubmitSingleMessageAuditEventConverter, SubmitSingleMessageAuditEventDao, SubmitSingleMessageAuditEventService>
    implements SubmitSingleMessageAuditEventListener {
    @Autowired
    public SubmitSingleMessageAuditEventListenerImpl(SubmitSingleMessageAuditEventConverter auditEventConv,
        SubmitSingleMessageAuditEventService auditEventService) {
        super(SubmitSingleMessageAuditEvent.class, auditEventConv, auditEventService);
    }
}
