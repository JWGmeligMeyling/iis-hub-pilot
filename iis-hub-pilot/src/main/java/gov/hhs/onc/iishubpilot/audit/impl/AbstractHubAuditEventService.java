package gov.hhs.onc.iishubpilot.audit.impl;

import gov.hhs.onc.iishubpilot.audit.HubAuditEventDao;
import gov.hhs.onc.iishubpilot.audit.HubAuditEvent;
import gov.hhs.onc.iishubpilot.audit.HubAuditEventService;
import gov.hhs.onc.iishubpilot.data.impl.AbstractHubDataService;
import java.math.BigInteger;

public abstract class AbstractHubAuditEventService<T extends HubAuditEvent, U extends HubAuditEventDao<T>> extends AbstractHubDataService<BigInteger, T, U> implements
    HubAuditEventService<T, U> {
    protected AbstractHubAuditEventService(Class<T> entityClass, U dao) {
        super(BigInteger.class, entityClass, dao);
    }
}
