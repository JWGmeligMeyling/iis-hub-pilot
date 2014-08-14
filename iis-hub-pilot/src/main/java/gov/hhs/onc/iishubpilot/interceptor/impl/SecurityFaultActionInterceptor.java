package gov.hhs.onc.iishubpilot.interceptor.impl;

import gov.hhs.onc.iishubpilot.ws.HubHttpHeaders;
import gov.hhs.onc.iishubpilot.ws.SecurityFault;
import gov.hhs.onc.iishubpilot.ws.impl.SecurityFaultTypeImpl;
import org.apache.cxf.message.Message;

public class SecurityFaultActionInterceptor extends AbstractDevFaultActionInterceptor<SecurityFault> {
    public SecurityFaultActionInterceptor(String phase) {
        super(phase, SecurityFault.class);

        this.actionValues.add(HubHttpHeaders.DEV_ACTION_SEC_FAULT_VALUE);
    }

    @Override
    protected SecurityFault createActionFault(Message msg, String msgActionValue) {
        return new SecurityFault(String.format("Fault for development IIS action HTTP header value: %s", msgActionValue), new SecurityFaultTypeImpl());
    }
}
