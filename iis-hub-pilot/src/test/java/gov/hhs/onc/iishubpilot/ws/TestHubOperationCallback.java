package gov.hhs.onc.iishubpilot.ws;

import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.jaxws.context.WrappedMessageContext;

public interface TestHubOperationCallback<T, U> {
    public U invoke(WrappedMessageContext msgContext, Pair<Map<String, List<String>>, Map<String, List<String>>> httpHeaders,
        Pair<SoapMessage, SoapMessage> msgs, T reqParams, U respParams);

    public boolean hasHttpRequestHeaders();

    @Nullable
    public Map<String, List<String>> getHttpRequestHeaders();

    public void setHttpRequestHeaders(@Nullable Map<String, List<String>> httpReqHeaders);

    public Class<T> getRequestParametersClass();

    public Class<U> getResponseParametersClass();
}
