package gov.hhs.onc.iishubpilot.ws.utils;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import org.apache.cxf.headers.Header;
import org.apache.cxf.jaxws.context.WrappedMessageContext;
import org.apache.cxf.message.Message;
import org.apache.cxf.transport.http.AbstractHTTPDestination;

public final class HubWsUtils {
    private HubWsUtils() {
    }

    @SuppressWarnings({ "unchecked" })
    public static List<Header> getSoapHeaders(MessageContext msgContext) {
        return ((List<Header>) msgContext.get(Header.HEADER_LIST));
    }

    @SuppressWarnings({ "unchecked" })
    public static Map<String, List<String>> getHttpHeaders(Message msg) {
        return ((Map<String, List<String>>) msg.get(Message.PROTOCOL_HEADERS));
    }

    public static HttpServletResponse getHttpServletResponse(MessageContext msgContext) {
        return ((HttpServletResponse) msgContext.get(AbstractHTTPDestination.HTTP_RESPONSE));
    }

    public static HttpServletRequest getHttpServletRequest(MessageContext msgContext) {
        return ((HttpServletRequest) msgContext.get(AbstractHTTPDestination.HTTP_REQUEST));
    }

    public static WrappedMessageContext getMessageContext(WebServiceContext wsContext) {
        return ((WrappedMessageContext) wsContext.getMessageContext());
    }
}
