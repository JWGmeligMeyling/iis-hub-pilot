package gov.hhs.onc.iishubpilot.ws;

public final class TestHubHttpHeaders {
    private final static String PREFIX = HubHttpHeaders.PREFIX + "Test-";
    private final static String SOAPUI_PREFIX = PREFIX + "SoapUI-";
    private final static String SOAPUI_TEST_PREFIX = SOAPUI_PREFIX + "Test";

    public final static String SOAPUI_PROJECT = SOAPUI_PREFIX + "Project";
    public final static String SOAPUI_TEST_SUITE = SOAPUI_TEST_PREFIX + "Suite";
    public final static String SOAPUI_TEST_CASE = SOAPUI_TEST_PREFIX + "Case";
    public final static String SOAPUI_TEST_STEP = SOAPUI_TEST_PREFIX + "Step";
    public final static String SOAPUI_REQ = SOAPUI_PREFIX + "Request";

    private TestHubHttpHeaders() {
    }
}
