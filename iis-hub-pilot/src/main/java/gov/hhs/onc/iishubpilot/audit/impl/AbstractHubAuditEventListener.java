package gov.hhs.onc.iishubpilot.audit.impl;

import gov.hhs.onc.iishubpilot.audit.HubAuditEventDao;
import gov.hhs.onc.iishubpilot.audit.HubAuditEvent;
import gov.hhs.onc.iishubpilot.audit.HubAuditEventListener;
import gov.hhs.onc.iishubpilot.audit.HubAuditEventService;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Session;

public abstract class AbstractHubAuditEventListener<T extends HubAuditEvent, U extends HubAuditEventDao<T>, V extends HubAuditEventService<T, U>> implements
    HubAuditEventListener<T, U, V> {
    protected Class<T> auditEventClass;
    protected V auditEventService;

    protected AbstractHubAuditEventListener(Class<T> auditEventClass, V auditEventService) {
        this.auditEventClass = auditEventClass;
        this.auditEventService = auditEventService;
    }

    @Override
    public void onMessage(ObjectMessage msg, Session session) throws JMSException {
        Object msgObj = msg.getObject();

        if (this.auditEventClass.isAssignableFrom(msgObj.getClass())) {
            try {
                this.onMessageInternal(this.auditEventClass.cast(msgObj), msg, session);
            } catch (JMSException e) {
                throw e;
            } catch (Exception e) {
                throw new JMSException(String.format("Unable to process Hub audit message (id=%s): %s", msg.getJMSMessageID(), e.getMessage()));
            }
        }
    }

    protected abstract void onMessageInternal(T auditEvent, ObjectMessage msg, Session session) throws Exception;
}
