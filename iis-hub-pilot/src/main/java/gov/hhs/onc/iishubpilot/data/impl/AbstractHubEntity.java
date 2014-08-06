package gov.hhs.onc.iishubpilot.data.impl;

import gov.hhs.onc.iishubpilot.data.HubEntity;
import java.io.Serializable;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import org.hibernate.annotations.Proxy;

@Access(AccessType.PROPERTY)
@MappedSuperclass
@Proxy(lazy = false)
public abstract class AbstractHubEntity<T extends Serializable> implements HubEntity<T> {
    protected T id;

    @Override
    @Transient
    public T getId() {
        return this.id;
    }

    @Override
    public void setId(T id) {
        this.id = id;
    }
}
