package gov.hhs.onc.iishubpilot.ws;

import gov.hhs.onc.iishubpilot.xml.HubXmlNs;
import javax.xml.namespace.QName;

public final class HubWsQnames {
    public final static QName PORT_TYPE = new QName(HubXmlNs.IIS, HubWsNames.PORT_TYPE, HubXmlNs.IIS_PREFIX);
    public final static QName PORT_TYPE_HUB = new QName(HubXmlNs.IIS_HUB, HubWsNames.PORT_TYPE_HUB, HubXmlNs.IIS_HUB_PREFIX);

    public final static QName CONN_TEST_OP = new QName(HubXmlNs.IIS, HubWsNames.CONN_TEST_OP, HubXmlNs.IIS_PREFIX);
    public final static QName CONN_TEST_OP_HUB = new QName(HubXmlNs.IIS_HUB, HubWsNames.CONN_TEST_OP, HubXmlNs.IIS_HUB_PREFIX);

    public final static QName CONN_TEST_REQ_MSG = new QName(HubXmlNs.IIS, HubWsNames.CONN_TEST_REQ_MSG, HubXmlNs.IIS_PREFIX);
    public final static QName CONN_TEST_REQ_MSG_HUB = new QName(HubXmlNs.IIS_HUB, HubWsNames.CONN_TEST_REQ_MSG, HubXmlNs.IIS_HUB_PREFIX);
    public final static QName CONN_TEST_RESP_MSG = new QName(HubXmlNs.IIS, HubWsNames.CONN_TEST_RESP_MSG, HubXmlNs.IIS_PREFIX);
    public final static QName CONN_TEST_RESP_MSG_HUB = new QName(HubXmlNs.IIS_HUB, HubWsNames.CONN_TEST_RESP_MSG, HubXmlNs.IIS_HUB_PREFIX);

    public final static QName SUBMIT_SINGLE_MSG_OP = new QName(HubXmlNs.IIS, HubWsNames.SUBMIT_SINGLE_MSG_OP, HubXmlNs.IIS_PREFIX);
    public final static QName SUBMIT_SINGLE_MSG_OP_HUB = new QName(HubXmlNs.IIS_HUB, HubWsNames.SUBMIT_SINGLE_MSG_OP, HubXmlNs.IIS_HUB_PREFIX);

    public final static QName SUBMIT_SINGLE_MSG_REQ_MSG = new QName(HubXmlNs.IIS, HubWsNames.SUBMIT_SINGLE_MSG_REQ_MSG, HubXmlNs.IIS_PREFIX);
    public final static QName SUBMIT_SINGLE_MSG_REQ_MSG_HUB = new QName(HubXmlNs.IIS_HUB, HubWsNames.SUBMIT_SINGLE_MSG_REQ_MSG, HubXmlNs.IIS_HUB_PREFIX);
    public final static QName SUBMIT_SINGLE_MSG_RESP_MSG = new QName(HubXmlNs.IIS, HubWsNames.SUBMIT_SINGLE_MSG_RESP_MSG, HubXmlNs.IIS_PREFIX);
    public final static QName SUBMIT_SINGLE_MSG_RESP_MSG_HUB = new QName(HubXmlNs.IIS_HUB, HubWsNames.SUBMIT_SINGLE_MSG_RESP_MSG, HubXmlNs.IIS_HUB_PREFIX);

    public final static QName BINDING = new QName(HubXmlNs.IIS, HubWsNames.BINDING, HubXmlNs.IIS_PREFIX);
    public final static QName BINDING_HUB = new QName(HubXmlNs.IIS_HUB, HubWsNames.BINDING_HUB, HubXmlNs.IIS_HUB_PREFIX);

    public final static QName SERVICE = new QName(HubXmlNs.IIS, HubWsNames.SERVICE, HubXmlNs.IIS_PREFIX);
    public final static QName SERVICE_HUB = new QName(HubXmlNs.IIS_HUB, HubWsNames.SERVICE_HUB, HubXmlNs.IIS_HUB_PREFIX);

    public final static QName PORT = new QName(HubXmlNs.IIS, HubWsNames.PORT, HubXmlNs.IIS_PREFIX);
    public final static QName PORT_HUB = new QName(HubXmlNs.IIS_HUB, HubWsNames.PORT_HUB, HubXmlNs.IIS_HUB_PREFIX);

    private HubWsQnames() {
    }
}
