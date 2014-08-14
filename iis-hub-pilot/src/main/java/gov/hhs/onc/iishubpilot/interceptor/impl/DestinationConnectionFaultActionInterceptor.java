package gov.hhs.onc.iishubpilot.interceptor.impl;

import gov.hhs.onc.iishubpilot.ws.HubHttpHeaders;
import gov.hhs.onc.iishubpilot.ws.hub.DestinationConnectionFault;
import gov.hhs.onc.iishubpilot.ws.hub.impl.DestinationConnectionFaultTypeImpl;
import org.apache.cxf.message.Message;

public class DestinationConnectionFaultActionInterceptor extends AbstractDevFaultActionInterceptor<DestinationConnectionFault> {
    public DestinationConnectionFaultActionInterceptor(String phase) {
        super(phase, DestinationConnectionFault.class);

        this.actionValues.add(HubHttpHeaders.DEV_ACTION_DEST_CONN_FAULT_VALUE);
    }

    @Override
    protected DestinationConnectionFault createActionFault(Message msg, String msgActionValue) {
        return new DestinationConnectionFault(String.format("Fault for development IIS action HTTP header value: %s", msgActionValue),
            new DestinationConnectionFaultTypeImpl(this.devDestId, this.devDestUri));
    }
}
