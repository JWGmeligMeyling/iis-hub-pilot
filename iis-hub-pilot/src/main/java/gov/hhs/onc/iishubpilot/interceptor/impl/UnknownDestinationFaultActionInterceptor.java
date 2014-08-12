package gov.hhs.onc.iishubpilot.interceptor.impl;

import gov.hhs.onc.iishubpilot.ws.HubHttpHeaders;
import gov.hhs.onc.iishubpilot.ws.hub.UnknownDestinationFault;
import gov.hhs.onc.iishubpilot.ws.hub.impl.UnknownDestinationFaultTypeImpl;
import java.util.List;
import java.util.Map;
import javax.xml.namespace.QName;
import org.apache.cxf.message.Message;

public class UnknownDestinationFaultActionInterceptor extends AbstractDevFaultActionInterceptor {
    public UnknownDestinationFaultActionInterceptor(String phase, QName opQname) {
        super(phase, opQname, HubHttpHeaders.DEV_ACTION_UNKNOWN_DEST_FAULT_VALUE);
    }

    @Override
    protected RuntimeException handleActionFault(Message msg, Map<String, List<String>> msgHttpHeaders, String msgActionValue) throws Exception {
        return new UnknownDestinationFault(String.format("Fault for development IIS action HTTP header value: %s", msgActionValue),
            new UnknownDestinationFaultTypeImpl(this.devDestId));
    }
}
