package gov.hhs.onc.iishubpilot.interceptor.impl;

import gov.hhs.onc.iishubpilot.interceptor.DevFaultActionInterceptor;
import org.apache.cxf.message.Message;
import org.apache.cxf.message.MessageUtils;

public abstract class AbstractDevFaultActionInterceptor<T extends RuntimeException> extends AbstractDevActionInterceptor implements
    DevFaultActionInterceptor<T> {
    protected Class<T> faultClass;

    protected AbstractDevFaultActionInterceptor(String phase, Class<T> faultClass) {
        super(phase);

        this.faultClass = faultClass;
    }

    @Override
    protected void handleActionMessage(Message msg, String msgActionValue) throws Exception {
        throw this.createActionFault(msg, msgActionValue);
    }

    protected abstract T createActionFault(Message msg, String msgActionValue);

    @Override
    protected boolean canHandleMessage(Message msg) {
        return (!MessageUtils.isFault(msg) && super.canHandleMessage(msg));
    }
}
