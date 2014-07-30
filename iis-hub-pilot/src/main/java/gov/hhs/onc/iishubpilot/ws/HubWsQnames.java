package gov.hhs.onc.iishubpilot.ws;

import gov.hhs.onc.iishubpilot.xml.HubXmlNs;
import javax.xml.namespace.QName;
import org.apache.cxf.interceptor.Fault;

public final class HubWsQnames {
    public final static QName CXF_FAULT_ROOT_STACK_TRACE = new QName(Fault.STACKTRACE_NAMESPACE, HubWsNames.CXF_FAULT_ROOT_STACK_TRACE,
        HubXmlNs.CXF_FAULT_PREFIX);

    public final static QName PORT_TYPE = new QName(HubXmlNs.IIS, HubWsNames.PORT_TYPE, HubXmlNs.IIS_PREFIX);
    public final static QName PORT_TYPE_HUB = new QName(HubXmlNs.IIS_HUB, HubWsNames.PORT_TYPE_HUB, HubXmlNs.IIS_HUB_PREFIX);

    public final static QName CONN_TEST_OP = new QName(HubXmlNs.IIS, HubWsNames.CONN_TEST_OP, HubXmlNs.IIS_PREFIX);
    public final static QName CONN_TEST_OP_HUB = new QName(HubXmlNs.IIS_HUB, HubWsNames.CONN_TEST_OP, HubXmlNs.IIS_HUB_PREFIX);

    public final static QName CONN_TEST_REQ = new QName(HubXmlNs.IIS, HubWsNames.CONN_TEST_REQ, HubXmlNs.IIS_PREFIX);
    public final static QName CONN_TEST_REQ_MSG = new QName(HubXmlNs.IIS, HubWsNames.CONN_TEST_REQ_MSG, HubXmlNs.IIS_PREFIX);
    public final static QName CONN_TEST_REQ_MSG_HUB = new QName(HubXmlNs.IIS_HUB, HubWsNames.CONN_TEST_REQ_MSG, HubXmlNs.IIS_HUB_PREFIX);
    public final static QName CONN_TEST_RESP = new QName(HubXmlNs.IIS, HubWsNames.CONN_TEST_RESP, HubXmlNs.IIS_PREFIX);
    public final static QName CONN_TEST_RESP_MSG = new QName(HubXmlNs.IIS, HubWsNames.CONN_TEST_RESP_MSG, HubXmlNs.IIS_PREFIX);
    public final static QName CONN_TEST_RESP_MSG_HUB = new QName(HubXmlNs.IIS_HUB, HubWsNames.CONN_TEST_RESP_MSG, HubXmlNs.IIS_HUB_PREFIX);

    public final static QName SUBMIT_SINGLE_MSG_OP = new QName(HubXmlNs.IIS, HubWsNames.SUBMIT_SINGLE_MSG_OP, HubXmlNs.IIS_PREFIX);
    public final static QName SUBMIT_SINGLE_MSG_OP_HUB = new QName(HubXmlNs.IIS_HUB, HubWsNames.SUBMIT_SINGLE_MSG_OP, HubXmlNs.IIS_HUB_PREFIX);

    public final static QName SUBMIT_SINGLE_MSG_REQ = new QName(HubXmlNs.IIS, HubWsNames.SUBMIT_SINGLE_MSG_REQ, HubXmlNs.IIS_PREFIX);
    public final static QName SUBMIT_SINGLE_MSG_REQ_MSG = new QName(HubXmlNs.IIS, HubWsNames.SUBMIT_SINGLE_MSG_REQ_MSG, HubXmlNs.IIS_PREFIX);
    public final static QName SUBMIT_SINGLE_MSG_REQ_MSG_HUB = new QName(HubXmlNs.IIS_HUB, HubWsNames.SUBMIT_SINGLE_MSG_REQ_MSG, HubXmlNs.IIS_HUB_PREFIX);
    public final static QName SUBMIT_SINGLE_MSG_RESP = new QName(HubXmlNs.IIS, HubWsNames.SUBMIT_SINGLE_MSG_RESP, HubXmlNs.IIS_PREFIX);
    public final static QName SUBMIT_SINGLE_MSG_RESP_MSG = new QName(HubXmlNs.IIS, HubWsNames.SUBMIT_SINGLE_MSG_RESP_MSG, HubXmlNs.IIS_PREFIX);
    public final static QName SUBMIT_SINGLE_MSG_RESP_MSG_HUB = new QName(HubXmlNs.IIS_HUB, HubWsNames.SUBMIT_SINGLE_MSG_RESP_MSG, HubXmlNs.IIS_HUB_PREFIX);

    public final static QName HUB_REQ_HEADER = new QName(HubXmlNs.IIS_HUB, HubWsNames.HUB_REQ_HEADER, HubXmlNs.IIS_HUB_PREFIX);
    public final static QName HUB_RESP_HEADER = new QName(HubXmlNs.IIS_HUB, HubWsNames.HUB_RESP_HEADER, HubXmlNs.IIS_HUB_PREFIX);

    public final static QName MSG_TOO_LARGE_FAULT = new QName(HubXmlNs.IIS, HubWsNames.MSG_TOO_LARGE_FAULT, HubXmlNs.IIS_PREFIX);
    public final static QName MSG_TOO_LARGE_FAULT_MSG = new QName(HubXmlNs.IIS, HubWsNames.MSG_TOO_LARGE_FAULT_MSG, HubXmlNs.IIS_PREFIX);
    public final static QName MSG_TOO_LARGE_FAULT_MSG_HUB = new QName(HubXmlNs.IIS_HUB, HubWsNames.MSG_TOO_LARGE_FAULT_MSG, HubXmlNs.IIS_HUB_PREFIX);
    public final static QName SEC_FAULT = new QName(HubXmlNs.IIS, HubWsNames.SEC_FAULT, HubXmlNs.IIS_PREFIX);
    public final static QName SEC_FAULT_MSG = new QName(HubXmlNs.IIS, HubWsNames.SEC_FAULT_MSG, HubXmlNs.IIS_PREFIX);
    public final static QName SEC_FAULT_MSG_HUB = new QName(HubXmlNs.IIS_HUB, HubWsNames.SEC_FAULT_MSG, HubXmlNs.IIS_HUB_PREFIX);
    public final static QName UNSUPPORTED_OP_FAULT = new QName(HubXmlNs.IIS, HubWsNames.UNSUPPORTED_OP_FAULT, HubXmlNs.IIS_PREFIX);
    public final static QName UNSUPPORTED_OP_FAULT_MSG = new QName(HubXmlNs.IIS, HubWsNames.UNSUPPORTED_OP_FAULT_MSG, HubXmlNs.IIS_PREFIX);
    public final static QName UNSUPPORTED_OP_FAULT_MSG_HUB = new QName(HubXmlNs.IIS_HUB, HubWsNames.UNSUPPORTED_OP_FAULT_MSG, HubXmlNs.IIS_HUB_PREFIX);

    public final static QName DEST_CONN_FAULT = new QName(HubXmlNs.IIS_HUB, HubWsNames.DEST_CONN_FAULT, HubXmlNs.IIS_HUB_PREFIX);
    public final static QName DEST_CONN_FAULT_MSG_HUB = new QName(HubXmlNs.IIS_HUB, HubWsNames.DEST_CONN_FAULT_MSG, HubXmlNs.IIS_HUB_PREFIX);
    public final static QName HUB_CLIENT_FAULT = new QName(HubXmlNs.IIS_HUB, HubWsNames.HUB_CLIENT_FAULT, HubXmlNs.IIS_HUB_PREFIX);
    public final static QName HUB_CLIENT_FAULT_MSG_HUB = new QName(HubXmlNs.IIS_HUB, HubWsNames.HUB_CLIENT_FAULT_MSG, HubXmlNs.IIS_HUB_PREFIX);
    public final static QName UNKNOWN_DEST_FAULT = new QName(HubXmlNs.IIS_HUB, HubWsNames.UNKNOWN_DEST_FAULT, HubXmlNs.IIS_HUB_PREFIX);
    public final static QName UNKNOWN_DEST_FAULT_MSG_HUB = new QName(HubXmlNs.IIS_HUB, HubWsNames.UNKNOWN_DEST_FAULT_MSG, HubXmlNs.IIS_HUB_PREFIX);

    public final static QName BINDING = new QName(HubXmlNs.IIS, HubWsNames.BINDING, HubXmlNs.IIS_PREFIX);
    public final static QName BINDING_HUB = new QName(HubXmlNs.IIS_HUB, HubWsNames.BINDING_HUB, HubXmlNs.IIS_HUB_PREFIX);

    public final static QName SERVICE = new QName(HubXmlNs.IIS, HubWsNames.SERVICE, HubXmlNs.IIS_PREFIX);
    public final static QName SERVICE_HUB = new QName(HubXmlNs.IIS_HUB, HubWsNames.SERVICE_HUB, HubXmlNs.IIS_HUB_PREFIX);

    public final static QName PORT = new QName(HubXmlNs.IIS, HubWsNames.PORT, HubXmlNs.IIS_PREFIX);
    public final static QName PORT_HUB = new QName(HubXmlNs.IIS_HUB, HubWsNames.PORT_HUB, HubXmlNs.IIS_HUB_PREFIX);

    private HubWsQnames() {
    }
}
