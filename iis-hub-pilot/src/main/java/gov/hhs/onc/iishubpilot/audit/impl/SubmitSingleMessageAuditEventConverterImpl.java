package gov.hhs.onc.iishubpilot.audit.impl;

import gov.hhs.onc.iishubpilot.audit.SubmitSingleMessageAuditEvent;
import gov.hhs.onc.iishubpilot.audit.SubmitSingleMessageAuditEventConverter;
import gov.hhs.onc.iishubpilot.ws.hub.HubRequestHeaderType;
import gov.hhs.onc.iishubpilot.ws.hub.HubResponseHeaderType;
import gov.hhs.onc.iishubpilot.ws.utils.HubWsUtils;
import javax.jms.JMSException;
import javax.jms.Session;
import org.apache.cxf.binding.soap.SoapMessage;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.stereotype.Component;

@Component("auditEventConvSubmitSingleMsg")
public class SubmitSingleMessageAuditEventConverterImpl extends AbstractHubAuditEventConverter<SubmitSingleMessageAuditEvent> implements
    SubmitSingleMessageAuditEventConverter {
    public SubmitSingleMessageAuditEventConverterImpl() {
        super(SubmitSingleMessageAuditEvent.class);
    }

    @Override
    protected SubmitSingleMessageAuditEvent buildAuditEvent(SoapMessage soapMsg, Session jmsSession) throws JMSException, MessageConversionException {
        SubmitSingleMessageAuditEvent auditEvent = super.buildAuditEvent(soapMsg, jmsSession);

        HubRequestHeaderType hubReqHeader =
            HubWsUtils.getMessageContentPart((soapMsg = ((SoapMessage) soapMsg.getExchange().getInMessage())), HubRequestHeaderType.class);

        if (hubReqHeader != null) {
            auditEvent.setRequestDestinationId(hubReqHeader.getDestinationId());
        }

        HubResponseHeaderType hubRespHeader = HubWsUtils.getMessageContentPart(soapMsg, HubResponseHeaderType.class);

        if (hubRespHeader != null) {
            auditEvent.setResponseDestinationId(hubRespHeader.getDestinationId());
            auditEvent.setResponseDestinationUri(hubRespHeader.getDestinationUri());
        }

        return auditEvent;
    }

    @Override
    protected SubmitSingleMessageAuditEvent createAuditEvent() {
        return new SubmitSingleMessageAuditEventImpl();
    }
}
