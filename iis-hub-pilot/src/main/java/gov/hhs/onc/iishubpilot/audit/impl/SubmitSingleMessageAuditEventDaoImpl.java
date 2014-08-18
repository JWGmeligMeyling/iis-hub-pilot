package gov.hhs.onc.iishubpilot.audit.impl;

import gov.hhs.onc.iishubpilot.audit.SubmitSingleMessageAuditEventDao;
import gov.hhs.onc.iishubpilot.audit.SubmitSingleMessageAuditEvent;
import org.springframework.stereotype.Repository;

@Repository("daoAuditEventSubmitSingleMsg")
public class SubmitSingleMessageAuditEventDaoImpl extends AbstractHubAuditEventDao<SubmitSingleMessageAuditEvent> implements SubmitSingleMessageAuditEventDao {
    public SubmitSingleMessageAuditEventDaoImpl() {
        super(SubmitSingleMessageAuditEvent.class, SubmitSingleMessageAuditEventImpl.class);
    }
}
