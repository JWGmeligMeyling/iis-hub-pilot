package gov.hhs.onc.iishubpilot.ws.utils;

import java.util.Map;
import javax.annotation.Nullable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import org.apache.cxf.jaxws.context.WrappedMessageContext;
import org.apache.cxf.message.Message;
import org.apache.cxf.message.MessageContentsList;
import org.apache.cxf.transport.http.AbstractHTTPDestination;

public final class HubWsUtils {
    private HubWsUtils() {
    }

    @Nullable
    public static <T> T getMessageContentPart(Message msg, Class<T> msgContentPartClass) {
        MessageContentsList msgContents = MessageContentsList.getContentsList(msg);

        return ((msgContents != null) ? msgContentPartClass.cast(msgContents.stream()
            .filter(((Object msgContentPart) -> (msgContentPartClass.isAssignableFrom(msgContentPart.getClass())))).findFirst().orElse(null)) : null);
    }

    @Nullable
    public static HttpServletResponse getHttpServletResponse(Message msg) {
        return getProperty(msg, AbstractHTTPDestination.HTTP_RESPONSE, HttpServletResponse.class);
    }

    @Nullable
    public static HttpServletResponse getHttpServletResponse(MessageContext msgContext) {
        return getProperty(msgContext, AbstractHTTPDestination.HTTP_RESPONSE, HttpServletResponse.class);
    }

    @Nullable
    public static HttpServletRequest getHttpServletRequest(Message msg) {
        return getProperty(msg, AbstractHTTPDestination.HTTP_REQUEST, HttpServletRequest.class);
    }

    @Nullable
    public static HttpServletRequest getHttpServletRequest(MessageContext msgContext) {
        return getProperty(msgContext, AbstractHTTPDestination.HTTP_REQUEST, HttpServletRequest.class);
    }

    @Nullable
    public static <T> T getProperty(Message msg, String propName, Class<T> propValueClass) {
        return getPropertyInternal(msg, propName, propValueClass);
    }

    @Nullable
    public static <T> T getProperty(MessageContext msgContext, String propName, Class<T> propValueClass) {
        return getPropertyInternal(msgContext, propName, propValueClass);
    }

    public static WrappedMessageContext getMessageContext(WebServiceContext wsContext) {
        return ((WrappedMessageContext) wsContext.getMessageContext());
    }

    @Nullable
    private static <T> T getPropertyInternal(Map<String, Object> props, String propName, Class<T> propValueClass) {
        return propValueClass.cast(props.get(propName));
    }
}
