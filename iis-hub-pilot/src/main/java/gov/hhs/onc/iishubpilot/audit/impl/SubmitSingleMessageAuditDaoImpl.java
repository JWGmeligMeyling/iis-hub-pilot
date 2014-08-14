package gov.hhs.onc.iishubpilot.audit.impl;

import gov.hhs.onc.iishubpilot.audit.SubmitSingleMessageAuditDao;
import gov.hhs.onc.iishubpilot.audit.SubmitSingleMessageAuditEvent;
import org.springframework.stereotype.Repository;

@Repository("auditDaoSubmitSingleMsg")
public class SubmitSingleMessageAuditDaoImpl extends AbstractHubAuditDao<SubmitSingleMessageAuditEvent> implements SubmitSingleMessageAuditDao {
    public SubmitSingleMessageAuditDaoImpl() {
        super(SubmitSingleMessageAuditEvent.class, SubmitSingleMessageAuditEventImpl.class);
    }
}
