package gov.hhs.onc.iishubpilot.audit.impl;

import gov.hhs.onc.iishubpilot.audit.HubAuditEventDao;
import gov.hhs.onc.iishubpilot.audit.HubAuditEvent;
import gov.hhs.onc.iishubpilot.data.impl.AbstractHubDao;
import java.math.BigInteger;

public abstract class AbstractHubAuditEventDao<T extends HubAuditEvent> extends AbstractHubDao<BigInteger, T> implements HubAuditEventDao<T> {
    protected AbstractHubAuditEventDao(Class<T> entityClass, Class<? extends T> entityImplClass) {
        super(BigInteger.class, entityClass, entityImplClass);
    }
}
