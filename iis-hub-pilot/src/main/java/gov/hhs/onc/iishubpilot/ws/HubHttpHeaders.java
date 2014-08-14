package gov.hhs.onc.iishubpilot.ws;

import org.apache.commons.lang3.StringUtils;

public final class HubHttpHeaders {
    private final static String PREFIX = "X-IIS-Hub-";
    private final static String DEV_PREFIX = PREFIX + "Dev-";

    public final static String DELIM = StringUtils.CR + StringUtils.LF;
    public final static String ENTRY_DELIM = ": ";

    public final static String DEV_ACTION_NAME = DEV_PREFIX + "Action";

    public final static String DEV_ACTION_MSG_TOO_LARGE_FAULT_VALUE = HubWsNames.MSG_TOO_LARGE_FAULT;
    public final static String DEV_ACTION_SEC_FAULT_VALUE = HubWsNames.SEC_FAULT;
    public final static String DEV_ACTION_DEST_CONN_FAULT_VALUE = HubWsNames.DEST_CONN_FAULT;
    public final static String DEV_ACTION_HUB_CLIENT_FAULT_VALUE = HubWsNames.HUB_CLIENT_FAULT;
    public final static String DEV_ACTION_UNKNOWN_DEST_FAULT_VALUE = HubWsNames.UNKNOWN_DEST_FAULT;

    private HubHttpHeaders() {
    }
}
