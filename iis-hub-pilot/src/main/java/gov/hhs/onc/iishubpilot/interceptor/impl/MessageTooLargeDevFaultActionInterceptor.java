package gov.hhs.onc.iishubpilot.interceptor.impl;

import gov.hhs.onc.iishubpilot.ws.HubHttpHeaders;
import gov.hhs.onc.iishubpilot.ws.MessageTooLargeFault;
import gov.hhs.onc.iishubpilot.ws.impl.MessageTooLargeFaultTypeImpl;
import java.math.BigInteger;
import org.apache.cxf.message.Message;

public class MessageTooLargeDevFaultActionInterceptor extends AbstractDevFaultActionInterceptor<MessageTooLargeFault> {
    public MessageTooLargeDevFaultActionInterceptor(String phase) {
        super(phase, MessageTooLargeFault.class);

        this.actionValues.add(HubHttpHeaders.DEV_ACTION_MSG_TOO_LARGE_FAULT_VALUE);
    }

    @Override
    protected MessageTooLargeFault createActionFault(Message msg, String msgActionValue) {
        return new MessageTooLargeFault(String.format("Fault for development IIS action HTTP header value: %s", msgActionValue),
            new MessageTooLargeFaultTypeImpl(BigInteger.ZERO, BigInteger.ZERO));
    }
}
