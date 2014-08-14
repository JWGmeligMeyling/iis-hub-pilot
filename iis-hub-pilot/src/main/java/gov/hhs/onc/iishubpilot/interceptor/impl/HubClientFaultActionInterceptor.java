package gov.hhs.onc.iishubpilot.interceptor.impl;

import gov.hhs.onc.iishubpilot.ws.HubHttpHeaders;
import gov.hhs.onc.iishubpilot.ws.hub.HubClientFault;
import gov.hhs.onc.iishubpilot.ws.hub.impl.HubClientFaultTypeImpl;
import org.apache.cxf.message.Message;

public class HubClientFaultActionInterceptor extends AbstractDevFaultActionInterceptor<HubClientFault> {
    public HubClientFaultActionInterceptor(String phase) {
        super(phase, HubClientFault.class);

        this.actionValues.add(HubHttpHeaders.DEV_ACTION_HUB_CLIENT_FAULT_VALUE);
    }

    @Override
    protected HubClientFault createActionFault(Message msg, String msgActionValue) {
        return new HubClientFault(String.format("Fault for development IIS action HTTP header value: %s", msgActionValue), new HubClientFaultTypeImpl(
            this.devDestId, this.devDestUri));
    }
}
