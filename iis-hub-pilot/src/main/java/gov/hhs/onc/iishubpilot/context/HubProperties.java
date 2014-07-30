package gov.hhs.onc.iishubpilot.context;

public final class HubProperties {
    private final static String PREFIX = "hub.";

    public final static String MODE_NAME = PREFIX + "mode";
    public final static String DEV_MODE_VALUE = "dev";
    public final static String PROD_MODE_VALUE = "prod";

    private HubProperties() {
    }
}
