package gov.hhs.onc.iishubpilot.ws;

public interface TestHubOperationFaultCallback<T, U, V, W extends RuntimeException> extends TestHubOperationCallback<T, U> {
    public Class<W> getFaultClass();

    public Class<V> getFaultTypeClass();
}
