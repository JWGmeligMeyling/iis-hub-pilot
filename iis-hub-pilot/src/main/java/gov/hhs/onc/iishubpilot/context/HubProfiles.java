package gov.hhs.onc.iishubpilot.context;

public final class HubProfiles {
    private final static String PREFIX = "hub.";
    private final static String CONTEXT_PREFIX = PREFIX + "context.";
    private final static String MODE_PREFIX = PREFIX + "mode.";

    public final static String WEBAPP_CONTEXT = CONTEXT_PREFIX + "webapp";

    public final static String DEV_MODE = MODE_PREFIX + "dev";
    public final static String PROD_MODE = MODE_PREFIX + "prod";

    private HubProfiles() {
    }
}
