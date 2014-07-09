package gov.hhs.onc.iishubpilot.xml;

import javax.xml.namespace.QName;
import org.apache.cxf.interceptor.Fault;

public final class HubXmlQnames {
    public final static QName CXF_FAULT_STACKTRACE = new QName(Fault.STACKTRACE_NAMESPACE, Fault.STACKTRACE, HubXmlNs.CXF_FAULT_PREFIX);

    private HubXmlQnames() {
    }
}
