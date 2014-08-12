package gov.hhs.onc.iishubpilot.interceptor.impl;

import gov.hhs.onc.iishubpilot.ws.HubHttpHeaders;
import gov.hhs.onc.iishubpilot.ws.hub.HubClientFault;
import gov.hhs.onc.iishubpilot.ws.hub.impl.HubClientFaultTypeImpl;
import java.util.List;
import java.util.Map;
import javax.xml.namespace.QName;
import org.apache.cxf.message.Message;

public class HubClientFaultActionInterceptor extends AbstractDevFaultActionInterceptor {
    public HubClientFaultActionInterceptor(String phase, QName opQname) {
        super(phase, opQname, HubHttpHeaders.DEV_ACTION_HUB_CLIENT_FAULT_VALUE);
    }

    @Override
    protected RuntimeException handleActionFault(Message msg, Map<String, List<String>> msgHttpHeaders, String msgActionValue) throws Exception {
        return new HubClientFault(String.format("Fault for development IIS action HTTP header value: %s", msgActionValue), new HubClientFaultTypeImpl(
            this.devDestId, this.devDestUri));
    }
}
