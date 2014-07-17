package gov.hhs.onc.iishubpilot.ws.impl;

import gov.hhs.onc.iishubpilot.ws.TestHubOperationFaultCallback;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.jaxws.context.WrappedMessageContext;

public abstract class AbstractTestHubOperationFaultCallback<T, U, V, W extends RuntimeException> extends AbstractTestHubOperationCallback<T, U> implements
    TestHubOperationFaultCallback<T, U, V, W> {
    protected Class<V> faultTypeClass;
    protected Class<W> faultClass;

    protected AbstractTestHubOperationFaultCallback(Class<T> reqParamsClass, Class<U> respParamsClass, Class<V> faultTypeClass, Class<W> faultClass) {
        super(reqParamsClass, respParamsClass);

        this.faultTypeClass = faultTypeClass;
        this.faultClass = faultClass;
    }

    @Override
    protected U invokeInternal(WrappedMessageContext msgContext, Pair<Map<String, List<String>>, Map<String, List<String>>> httpHeaders,
        Pair<SoapMessage, SoapMessage> msgs, T reqParams, U respParams) {
        throw this.createFault(msgContext, httpHeaders, msgs, reqParams, respParams);
    }

    protected abstract W createFault(WrappedMessageContext msgContext, Pair<Map<String, List<String>>, Map<String, List<String>>> httpHeaders,
        Pair<SoapMessage, SoapMessage> msgs, T reqParams, U respParams);

    @Override
    public Class<W> getFaultClass() {
        return this.faultClass;
    }

    @Override
    public Class<V> getFaultTypeClass() {
        return this.faultTypeClass;
    }
}
