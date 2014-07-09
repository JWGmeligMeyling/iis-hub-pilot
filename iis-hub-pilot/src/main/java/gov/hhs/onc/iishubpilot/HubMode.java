package gov.hhs.onc.iishubpilot;

public enum HubMode {
    DEV(HubProperties.MODE + ".dev"), PROD(HubProperties.MODE + ".prod");

    private final String profile;
    private final String activePropName;

    private HubMode(String profile) {
        this.profile = profile;
        this.activePropName = (this.profile + HubProperties.ACTIVE_SUFFIX);
    }

    public String getActivePropertyName() {
        return this.activePropName;
    }

    public String getProfile() {
        return this.profile;
    }
}
