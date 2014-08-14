package gov.hhs.onc.iishubpilot.audit.impl;

import gov.hhs.onc.iishubpilot.audit.HubAuditDao;
import gov.hhs.onc.iishubpilot.audit.HubAuditEvent;
import gov.hhs.onc.iishubpilot.audit.HubAuditService;
import gov.hhs.onc.iishubpilot.data.impl.AbstractHubDataService;
import java.math.BigInteger;

public abstract class AbstractHubAuditService<T extends HubAuditEvent, U extends HubAuditDao<T>> extends AbstractHubDataService<BigInteger, T, U> implements
    HubAuditService<T, U> {
    protected AbstractHubAuditService(Class<T> entityClass, U dao) {
        super(BigInteger.class, entityClass, dao);
    }
}
