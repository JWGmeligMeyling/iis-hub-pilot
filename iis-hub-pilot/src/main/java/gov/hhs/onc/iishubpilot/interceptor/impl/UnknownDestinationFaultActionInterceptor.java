package gov.hhs.onc.iishubpilot.interceptor.impl;

import gov.hhs.onc.iishubpilot.ws.HubHttpHeaders;
import gov.hhs.onc.iishubpilot.ws.hub.UnknownDestinationFault;
import gov.hhs.onc.iishubpilot.ws.hub.impl.UnknownDestinationFaultTypeImpl;
import org.apache.cxf.message.Message;

public class UnknownDestinationFaultActionInterceptor extends AbstractDevFaultActionInterceptor<UnknownDestinationFault> {
    public UnknownDestinationFaultActionInterceptor(String phase) {
        super(phase, UnknownDestinationFault.class);

        this.actionValues.add(HubHttpHeaders.DEV_ACTION_UNKNOWN_DEST_FAULT_VALUE);
    }

    @Override
    protected UnknownDestinationFault createActionFault(Message msg, String msgActionValue) {
        return new UnknownDestinationFault(String.format("Fault for development IIS action HTTP header value: %s", msgActionValue),
            new UnknownDestinationFaultTypeImpl(this.devDestId));
    }
}
