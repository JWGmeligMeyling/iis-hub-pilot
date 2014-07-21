package gov.hhs.onc.iishubpilot.utils;

import org.springframework.util.StringUtils;

public final class HubStringUtils {
    public final static String PROP_VALUE_TOKEN_DELIMS = ",; \t\n";

    public static String[] tokenizePropertyValue(String str) {
        return StringUtils.tokenizeToStringArray(str, PROP_VALUE_TOKEN_DELIMS);
    }

    private HubStringUtils() {
    }
}
