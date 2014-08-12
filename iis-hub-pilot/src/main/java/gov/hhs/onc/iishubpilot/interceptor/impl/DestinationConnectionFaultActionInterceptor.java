package gov.hhs.onc.iishubpilot.interceptor.impl;

import gov.hhs.onc.iishubpilot.ws.HubHttpHeaders;
import gov.hhs.onc.iishubpilot.ws.hub.DestinationConnectionFault;
import gov.hhs.onc.iishubpilot.ws.hub.impl.DestinationConnectionFaultTypeImpl;
import java.util.List;
import java.util.Map;
import javax.xml.namespace.QName;
import org.apache.cxf.message.Message;

public class DestinationConnectionFaultActionInterceptor extends AbstractDevFaultActionInterceptor {
    public DestinationConnectionFaultActionInterceptor(String phase, QName opQname) {
        super(phase, opQname, HubHttpHeaders.DEV_ACTION_DEST_CONN_FAULT_VALUE);
    }

    @Override
    protected RuntimeException handleActionFault(Message msg, Map<String, List<String>> msgHttpHeaders, String msgActionValue) throws Exception {
        return new DestinationConnectionFault(String.format("Fault for development IIS action HTTP header value: %s", msgActionValue),
            new DestinationConnectionFaultTypeImpl(this.devDestId, this.devDestUri));
    }
}
