package gov.hhs.onc.iishubpilot.crypto.impl;

import gov.hhs.onc.iishubpilot.crypto.HubCryptoInfo;

public abstract class AbstractHubCryptoInfo<T> extends AbstractHubCryptoDescriptor<T> implements HubCryptoInfo<T> {
    protected T obj;

    protected AbstractHubCryptoInfo(T obj) {
        this.obj = obj;
    }

    @Override
    public T getObject() {
        return this.obj;
    }
}
