package gov.hhs.onc.iishubpilot.ws;

public final class HubWsNames {
    private final static String IIS_PREFIX = "IIS";
    private final static String IIS_HUB_PREFIX = IIS_PREFIX + "Hub";

    private final static String REQ_SUFFIX = "Request";
    private final static String RESP_SUFFIX = "Response";

    private final static String PORT_TYPE_NAME = "PortType";
    private final static String BINDING_NAME = "BindingSoap12";
    private final static String SERVICE_NAME = "Service";
    private final static String PORT_NAME = "PortSoap12";

    public final static String PORT_TYPE = IIS_PREFIX + PORT_TYPE_NAME;
    public final static String PORT_TYPE_HUB = IIS_HUB_PREFIX + PORT_TYPE_NAME;

    public final static String CONN_TEST_OP = "ConnectivityTest";

    public final static String CONN_TEST_REQ_MSG = CONN_TEST_OP + REQ_SUFFIX;
    public final static String CONN_TEST_RESP_MSG = CONN_TEST_OP + RESP_SUFFIX;

    public final static String SUBMIT_SINGLE_MSG_OP = "SubmitSingleMessage";

    public final static String SUBMIT_SINGLE_MSG_REQ_MSG = SUBMIT_SINGLE_MSG_OP + REQ_SUFFIX;
    public final static String SUBMIT_SINGLE_MSG_RESP_MSG = SUBMIT_SINGLE_MSG_OP + RESP_SUFFIX;

    public final static String BINDING = IIS_PREFIX + BINDING_NAME;
    public final static String BINDING_HUB = IIS_HUB_PREFIX + BINDING_NAME;

    public final static String SERVICE = IIS_PREFIX + SERVICE_NAME;
    public final static String SERVICE_HUB = IIS_HUB_PREFIX + SERVICE_NAME;

    public final static String PORT = IIS_PREFIX + PORT_NAME;
    public final static String PORT_HUB = IIS_HUB_PREFIX + PORT_NAME;

    private HubWsNames() {
    }
}
