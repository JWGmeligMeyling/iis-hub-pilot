package gov.hhs.onc.iishubpilot.crypto;

import org.springframework.util.ResourceUtils;

public final class HubSecureRandomSeedSources {
    private final static String DEVICE_PREFIX = ResourceUtils.FILE_URL_PREFIX + "/dev/";

    public final static String URAND_DEVICE = DEVICE_PREFIX + "urandom";

    private HubSecureRandomSeedSources() {
    }
}
