package gov.hhs.onc.iishubpilot.ws;

import org.apache.cxf.message.Message;

public final class HubMessageContextProperties {
    private final static String HUB_SUFFIX = "Hub";

    public final static String FAULT_STACKTRACE_ENABLED = Message.FAULT_STACKTRACE_ENABLED + HUB_SUFFIX;

    private HubMessageContextProperties() {
    }
}
