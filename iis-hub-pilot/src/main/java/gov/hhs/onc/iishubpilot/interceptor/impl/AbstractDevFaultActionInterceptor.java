package gov.hhs.onc.iishubpilot.interceptor.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.xml.namespace.QName;
import org.apache.cxf.message.Message;
import org.apache.cxf.message.MessageUtils;

public abstract class AbstractDevFaultActionInterceptor extends AbstractDevActionInterceptor {
    protected AbstractDevFaultActionInterceptor(String phase, QName opQname, String ... actionValues) {
        super(phase, opQname, actionValues);
    }

    protected AbstractDevFaultActionInterceptor(String phase, QName opQname, Set<String> actionValues) {
        super(phase, opQname, actionValues);
    }

    @Override
    protected void handleAction(Message msg, Map<String, List<String>> msgHttpHeaders, String msgActionValue) throws Exception {
        if (!MessageUtils.isFault(msg)) {
            throw this.handleActionFault(msg, msgHttpHeaders, msgActionValue);
        }
    }

    protected abstract RuntimeException handleActionFault(Message msg, Map<String, List<String>> msgHttpHeaders, String msgActionValue) throws Exception;
}
