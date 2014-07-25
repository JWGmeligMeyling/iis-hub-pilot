package gov.hhs.onc.iishubpilot.crypto;

public interface HubCryptoInfo<T> extends HubCryptoDescriptor<T> {
    public T getObject();
}
