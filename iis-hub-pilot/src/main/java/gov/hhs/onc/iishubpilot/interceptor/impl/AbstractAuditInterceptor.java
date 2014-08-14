package gov.hhs.onc.iishubpilot.interceptor.impl;

import gov.hhs.onc.iishubpilot.audit.HubAuditEvent;
import gov.hhs.onc.iishubpilot.audit.HubAuditEventSender;
import gov.hhs.onc.iishubpilot.interceptor.AuditInterceptor;
import gov.hhs.onc.iishubpilot.ws.HubHttpHeaders;
import gov.hhs.onc.iishubpilot.ws.utils.HubWsUtils;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.text.StrBuilder;
import org.apache.cxf.binding.soap.SoapMessage;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;

public abstract class AbstractAuditInterceptor<T extends HubAuditEvent, U extends HubAuditEventSender<T>> extends AbstractHubOperationInterceptor<SoapMessage>
    implements AuditInterceptor<T, U> {
    protected U auditEventSender;

    protected AbstractAuditInterceptor(String phase, U auditEventSender) {
        super(phase);

        this.auditEventSender = auditEventSender;
    }

    protected static String buildHeadersValue(Map<String, String> headersMap) {
        StrBuilder headersValueBuilder = new StrBuilder();

        for (Entry<String, String> headerEntry : headersMap.entrySet()) {
            headersValueBuilder.appendSeparator(HubHttpHeaders.DELIM);
            headersValueBuilder.append(headerEntry.getKey());
            headersValueBuilder.appendSeparator(HubHttpHeaders.ENTRY_DELIM);
            headersValueBuilder.append(headerEntry.getValue());
        }

        return headersValueBuilder.build();
    }

    @Override
    protected void handleMessageInternal(SoapMessage msg) throws Exception {
        T auditEvent = this.createAuditEvent((msg = ((SoapMessage) msg.getExchange().getInMessage())));
        auditEvent.setRequestMessageId(msg.getId());
        auditEvent.setRequestTimestamp(new Date());

        HttpServletRequest httpServletReq = HubWsUtils.getHttpServletRequest(msg);
        // noinspection ConstantConditions
        auditEvent.setRequestAuthType(httpServletReq.getAuthType());
        auditEvent.setRequestContextPath(httpServletReq.getContextPath());
        auditEvent.setRequestHeaders(buildHeadersValue(new ServletServerHttpRequest(httpServletReq).getHeaders().toSingleValueMap()));
        auditEvent.setRequestLocalName(httpServletReq.getLocalName());
        auditEvent.setRequestLocalPort(httpServletReq.getLocalPort());
        auditEvent.setRequestMethod(httpServletReq.getMethod());
        auditEvent.setRequestProtocol(httpServletReq.getProtocol());
        auditEvent.setRequestQuery(httpServletReq.getQueryString());
        auditEvent.setRequestRemoteName(httpServletReq.getRemoteAddr());
        auditEvent.setRequestRemotePort(httpServletReq.getRemotePort());
        auditEvent.setRequestScheme(httpServletReq.getScheme());
        auditEvent.setRequestServerName(httpServletReq.getServerName());
        auditEvent.setRequestServerPort(httpServletReq.getServerPort());
        auditEvent.setRequestServletPath(httpServletReq.getServletPath());
        auditEvent.setRequestUserPrincipal(httpServletReq.getUserPrincipal().toString());

        HttpServletResponse httpServletResp = HubWsUtils.getHttpServletResponse(msg);
        // noinspection ConstantConditions
        auditEvent.setResponseCode(httpServletResp.getStatus());
        auditEvent.setResponseHeaders(buildHeadersValue(new ServletServerHttpResponse(httpServletResp).getHeaders().toSingleValueMap()));

        this.auditEventSender.send(auditEvent);
    }

    protected abstract T createAuditEvent(SoapMessage msg) throws Exception;
}
