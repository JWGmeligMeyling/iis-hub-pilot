package gov.hhs.onc.iishubpilot.interceptor.impl;

import gov.hhs.onc.iishubpilot.ws.HubHttpHeaders;
import gov.hhs.onc.iishubpilot.ws.MessageTooLargeFault;
import gov.hhs.onc.iishubpilot.ws.impl.MessageTooLargeFaultTypeImpl;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import javax.xml.namespace.QName;
import org.apache.cxf.message.Message;

public class MessageTooLargeDevFaultActionInterceptor extends AbstractDevFaultActionInterceptor {
    public MessageTooLargeDevFaultActionInterceptor(String phase, QName opQname) {
        super(phase, opQname, HubHttpHeaders.DEV_ACTION_MSG_TOO_LARGE_FAULT_VALUE);
    }

    @Override
    protected RuntimeException handleActionFault(Message msg, Map<String, List<String>> msgHttpHeaders, String msgActionValue) throws Exception {
        return new MessageTooLargeFault(String.format("Fault for development IIS action HTTP header value: %s", msgActionValue),
            new MessageTooLargeFaultTypeImpl(BigInteger.ZERO, BigInteger.ZERO));
    }
}
