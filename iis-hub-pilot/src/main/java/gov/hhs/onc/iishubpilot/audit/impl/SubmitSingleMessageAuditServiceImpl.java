package gov.hhs.onc.iishubpilot.audit.impl;

import gov.hhs.onc.iishubpilot.audit.SubmitSingleMessageAuditDao;
import gov.hhs.onc.iishubpilot.audit.SubmitSingleMessageAuditEvent;
import gov.hhs.onc.iishubpilot.audit.SubmitSingleMessageAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("auditServiceSubmitSingleMsg")
public class SubmitSingleMessageAuditServiceImpl extends AbstractHubAuditService<SubmitSingleMessageAuditEvent, SubmitSingleMessageAuditDao> implements
    SubmitSingleMessageAuditService {
    @Autowired
    public SubmitSingleMessageAuditServiceImpl(SubmitSingleMessageAuditDao dao) {
        super(SubmitSingleMessageAuditEvent.class, dao);
    }
}
