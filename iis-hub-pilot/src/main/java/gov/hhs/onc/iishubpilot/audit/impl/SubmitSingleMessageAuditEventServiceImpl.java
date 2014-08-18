package gov.hhs.onc.iishubpilot.audit.impl;

import gov.hhs.onc.iishubpilot.audit.SubmitSingleMessageAuditEventDao;
import gov.hhs.onc.iishubpilot.audit.SubmitSingleMessageAuditEvent;
import gov.hhs.onc.iishubpilot.audit.SubmitSingleMessageAuditEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("serviceAuditEventSubmitSingleMsg")
public class SubmitSingleMessageAuditEventServiceImpl extends AbstractHubAuditEventService<SubmitSingleMessageAuditEvent, SubmitSingleMessageAuditEventDao>
    implements SubmitSingleMessageAuditEventService {
    @Autowired
    public SubmitSingleMessageAuditEventServiceImpl(SubmitSingleMessageAuditEventDao dao) {
        super(SubmitSingleMessageAuditEvent.class, dao);
    }
}
