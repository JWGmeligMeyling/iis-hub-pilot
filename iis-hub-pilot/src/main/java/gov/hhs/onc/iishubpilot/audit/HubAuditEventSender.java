package gov.hhs.onc.iishubpilot.audit;

import javax.jms.JMSException;

public interface HubAuditEventSender<T extends HubAuditEvent> {
    public void send(T auditEvent) throws JMSException;
}
