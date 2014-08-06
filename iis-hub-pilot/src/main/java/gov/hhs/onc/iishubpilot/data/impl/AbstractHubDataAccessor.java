package gov.hhs.onc.iishubpilot.data.impl;

import gov.hhs.onc.iishubpilot.data.HubDataAccessor;
import gov.hhs.onc.iishubpilot.data.HubEntity;
import java.io.Serializable;

public abstract class AbstractHubDataAccessor<T extends Serializable, U extends HubEntity<T>> implements HubDataAccessor<T, U> {
    protected Class<T> idClass;
    protected Class<U> entityClass;

    protected AbstractHubDataAccessor(Class<T> idClass, Class<U> entityClass) {
        this.idClass = idClass;
        this.entityClass = entityClass;
    }
}
