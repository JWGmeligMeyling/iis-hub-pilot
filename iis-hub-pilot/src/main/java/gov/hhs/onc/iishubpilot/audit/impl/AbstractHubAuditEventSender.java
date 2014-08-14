package gov.hhs.onc.iishubpilot.audit.impl;

import gov.hhs.onc.iishubpilot.audit.HubAuditEvent;
import gov.hhs.onc.iishubpilot.audit.HubAuditEventSender;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Session;
import org.springframework.jms.core.JmsTemplate;

public abstract class AbstractHubAuditEventSender<T extends HubAuditEvent> implements HubAuditEventSender<T> {
    protected JmsTemplate jmsTemplate;
    protected Destination dest;
    protected Class<T> auditEventClass;

    public AbstractHubAuditEventSender(JmsTemplate jmsTemplate, Destination dest, Class<T> auditEventClass) {
        this.jmsTemplate = jmsTemplate;
        this.dest = dest;
        this.auditEventClass = auditEventClass;
    }

    @Override
    public void send(T auditEvent) throws JMSException {
        this.jmsTemplate.send(this.dest, ((Session session) -> session.createObjectMessage(auditEvent)));
    }
}
