package gov.hhs.onc.iishubpilot.audit.impl;

import gov.hhs.onc.iishubpilot.audit.HubAuditEvent;
import gov.hhs.onc.iishubpilot.audit.HubAuditEventConverter;
import gov.hhs.onc.iishubpilot.ws.HubHttpHeaders;
import gov.hhs.onc.iishubpilot.ws.utils.HubWsUtils;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.text.StrBuilder;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.ws.addressing.AddressingProperties;
import org.apache.cxf.ws.addressing.AttributedURIType;
import org.apache.cxf.ws.addressing.ContextUtils;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.jms.support.converter.MessageConversionException;

public abstract class AbstractHubAuditEventConverter<T extends HubAuditEvent> implements HubAuditEventConverter<T> {
    protected Class<T> auditEventClass;

    protected AbstractHubAuditEventConverter(Class<T> auditEventClass) {
        this.auditEventClass = auditEventClass;
    }

    @Override
    public Message toMessage(Object jmsObj, Session jmsSession) throws JMSException, MessageConversionException {
        Class<?> jmsObjClass = jmsObj.getClass();
        T auditEvent = null;

        if (SoapMessage.class.isAssignableFrom(jmsObjClass)) {
            auditEvent = this.buildAuditEvent(((SoapMessage) jmsObj), jmsSession);
        } else if (!this.auditEventClass.isAssignableFrom(jmsObjClass)) {
            throw new MessageConversionException(String.format("Hub audit JMS message object (class=%s) is not an audit event (class=%s).",
                jmsObjClass.getName(), this.auditEventClass.getName()));
        }

        return jmsSession.createObjectMessage(((auditEvent != null) ? auditEvent : this.auditEventClass.cast(jmsObj)));
    }

    @Override
    public Object fromMessage(Message jmsMsg) throws JMSException, MessageConversionException {
        Object jmsObj = ((ObjectMessage) jmsMsg).getObject();
        Class<?> jmsObjClass = jmsObj.getClass();

        if (!this.auditEventClass.isAssignableFrom(jmsObjClass)) {
            throw new MessageConversionException(String.format("Hub audit JMS message object (class=%s) is not an audit event (class=%s).",
                jmsObjClass.getName(), this.auditEventClass.getName()));
        }

        return jmsObj;
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

    protected T buildAuditEvent(SoapMessage soapMsg, Session jmsSession) throws JMSException, MessageConversionException {
        AddressingProperties addressingProps = ContextUtils.retrieveMAPs((soapMsg = ((SoapMessage) soapMsg.getExchange().getInMessage())), false, false);
        AttributedURIType addressingMsgIdAttr;
        String addressingMsgId;

        T auditEvent = this.createAuditEvent();
        auditEvent.setRequestMessageId((((addressingProps != null) && ((addressingMsgIdAttr = addressingProps.getMessageID()) != null) && ((addressingMsgId =
            addressingMsgIdAttr.getValue()) != null)) ? addressingMsgId : soapMsg.getId()));
        auditEvent.setRequestTimestamp(new Date());

        HttpServletRequest httpServletReq = HubWsUtils.getHttpServletRequest(soapMsg);
        // noinspection ConstantConditions
        auditEvent.setRequestAuthType(httpServletReq.getAuthType());
        auditEvent.setRequestContextPath(httpServletReq.getContextPath());
        auditEvent.setRequestHeaders(buildHeadersValue(new ServletServerHttpRequest(httpServletReq).getHeaders().toSingleValueMap()));
        auditEvent.setRequestLocalName(httpServletReq.getLocalName());
        auditEvent.setRequestLocalPort(httpServletReq.getLocalPort());
        auditEvent.setRequestMethod(httpServletReq.getMethod());
        auditEvent.setRequestPath(httpServletReq.getPathInfo());
        auditEvent.setRequestProtocol(httpServletReq.getProtocol());
        auditEvent.setRequestQuery(httpServletReq.getQueryString());
        auditEvent.setRequestRemoteName(httpServletReq.getRemoteAddr());
        auditEvent.setRequestRemotePort(httpServletReq.getRemotePort());
        auditEvent.setRequestScheme(httpServletReq.getScheme());
        auditEvent.setRequestServerName(httpServletReq.getServerName());
        auditEvent.setRequestServerPort(httpServletReq.getServerPort());
        auditEvent.setRequestServletPath(httpServletReq.getServletPath());
        auditEvent.setRequestUserPrincipal(httpServletReq.getUserPrincipal().toString());

        HttpServletResponse httpServletResp = HubWsUtils.getHttpServletResponse(soapMsg);
        // noinspection ConstantConditions
        auditEvent.setResponseCode(httpServletResp.getStatus());
        auditEvent.setResponseHeaders(buildHeadersValue(new ServletServerHttpResponse(httpServletResp).getHeaders().toSingleValueMap()));

        return auditEvent;
    }

    protected abstract T createAuditEvent();
}
