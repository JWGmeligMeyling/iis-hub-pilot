package gov.hhs.onc.iishubpilot.interceptor.impl;

import gov.hhs.onc.iishubpilot.ws.HubHttpHeaders;
import gov.hhs.onc.iishubpilot.ws.SecurityFault;
import gov.hhs.onc.iishubpilot.ws.impl.SecurityFaultTypeImpl;
import java.util.List;
import java.util.Map;
import javax.xml.namespace.QName;
import org.apache.cxf.message.Message;

public class SecurityFaultActionInterceptor extends AbstractDevFaultActionInterceptor {
    public SecurityFaultActionInterceptor(String phase, QName opQname) {
        super(phase, opQname, HubHttpHeaders.DEV_ACTION_SEC_FAULT_VALUE);
    }

    @Override
    protected RuntimeException handleActionFault(Message msg, Map<String, List<String>> msgHttpHeaders, String msgActionValue) throws Exception {
        return new SecurityFault(String.format("Fault for development IIS action HTTP header value: %s", msgActionValue), new SecurityFaultTypeImpl());
    }
}
