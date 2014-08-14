package gov.hhs.onc.iishubpilot.audit.impl;

import gov.hhs.onc.iishubpilot.audit.HubAuditDao;
import gov.hhs.onc.iishubpilot.audit.HubAuditEvent;
import gov.hhs.onc.iishubpilot.data.impl.AbstractHubDao;
import java.math.BigInteger;

public abstract class AbstractHubAuditDao<T extends HubAuditEvent> extends AbstractHubDao<BigInteger, T> implements HubAuditDao<T> {
    protected AbstractHubAuditDao(Class<T> entityClass, Class<? extends T> entityImplClass) {
        super(BigInteger.class, entityClass, entityImplClass);
    }
}
