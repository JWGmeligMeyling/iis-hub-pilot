package gov.hhs.onc.iishubpilot.data;

import java.io.Serializable;

public interface HubEntity<T extends Serializable> {
    public T getId();

    public void setId(T id);
}
