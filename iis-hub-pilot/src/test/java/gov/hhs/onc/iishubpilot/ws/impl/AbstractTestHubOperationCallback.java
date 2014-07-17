package gov.hhs.onc.iishubpilot.ws.impl;

import gov.hhs.onc.iishubpilot.ws.TestHubOperationCallback;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;
import javax.annotation.Nullable;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.jaxws.context.WrappedMessageContext;

public abstract class AbstractTestHubOperationCallback<T, U> implements TestHubOperationCallback<T, U> {
    protected Class<T> reqParamsClass;
    protected Class<U> respParamsClass;
    protected Map<String, List<String>> httpReqHeaders;

    protected AbstractTestHubOperationCallback(Class<T> reqParamsClass, Class<U> respParamsClass) {
        this.reqParamsClass = reqParamsClass;
        this.respParamsClass = respParamsClass;
    }

    @Override
    public U invoke(WrappedMessageContext msgContext, Pair<Map<String, List<String>>, Map<String, List<String>>> httpHeaders,
        Pair<SoapMessage, SoapMessage> msgs, T reqParams, U respParams) {
        return (this.canInvoke(msgContext, httpHeaders, msgs, reqParams, respParams)
            ? invokeInternal(msgContext, httpHeaders, msgs, reqParams, respParams) : respParams);
    }

    protected U invokeInternal(WrappedMessageContext msgContext, Pair<Map<String, List<String>>, Map<String, List<String>>> httpHeaders,
        Pair<SoapMessage, SoapMessage> msgs, T reqParams, U respParams) {
        return respParams;
    }

    @SuppressWarnings({ "unchecked" })
    protected boolean canInvoke(WrappedMessageContext msgContext, Pair<Map<String, List<String>>, Map<String, List<String>>> httpHeaders,
        Pair<SoapMessage, SoapMessage> msgs, T reqParams, U respParams) {
        if (!this.hasHttpRequestHeaders()) {
            return false;
        }

        Set<Entry<String, List<String>>> reqHttpHeaderEntries = new TreeSet<>(new Comparator<Entry<String, List<String>>>() {
            @Override
            public int compare(Entry<String, List<String>> headerEntry1, Entry<String, List<String>> headerEntry2) {
                int headerEntryOrder = headerEntry1.getKey().compareToIgnoreCase(headerEntry2.getKey());

                return ((headerEntryOrder != 0) ? headerEntryOrder : (headerEntry1.getValue().containsAll(headerEntry2.getValue()) ? headerEntryOrder : -1));
            }
        });
        reqHttpHeaderEntries.addAll(httpHeaders.getLeft().entrySet());

        return reqHttpHeaderEntries.containsAll(this.httpReqHeaders.entrySet());
    }

    @Override
    public boolean hasHttpRequestHeaders() {
        return !MapUtils.isEmpty(this.httpReqHeaders);
    }

    @Nullable
    @Override
    public Map<String, List<String>> getHttpRequestHeaders() {
        return this.httpReqHeaders;
    }

    @Override
    public void setHttpRequestHeaders(@Nullable Map<String, List<String>> httpReqHeaders) {
        this.httpReqHeaders = httpReqHeaders;
    }

    @Override
    public Class<T> getRequestParametersClass() {
        return this.reqParamsClass;
    }

    @Override
    public Class<U> getResponseParametersClass() {
        return this.respParamsClass;
    }
}
