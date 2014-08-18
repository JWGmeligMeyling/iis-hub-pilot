package gov.hhs.onc.iishubpilot.audit.impl;

import gov.hhs.onc.iishubpilot.audit.HubAuditEventConverter;
import gov.hhs.onc.iishubpilot.audit.HubAuditEventDao;
import gov.hhs.onc.iishubpilot.audit.HubAuditEvent;
import gov.hhs.onc.iishubpilot.audit.HubAuditEventListener;
import gov.hhs.onc.iishubpilot.audit.HubAuditEventService;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Session;

public abstract class AbstractHubAuditEventListener<T extends HubAuditEvent, U extends HubAuditEventConverter<T>, V extends HubAuditEventDao<T>, W extends HubAuditEventService<T, V>>
    implements HubAuditEventListener<T, U, V, W> {
    protected Class<T> auditEventClass;
    protected U auditEventConv;
    protected W auditEventService;

    protected AbstractHubAuditEventListener(Class<T> auditEventClass, U auditEventConv, W auditEventService) {
        this.auditEventClass = auditEventClass;
        this.auditEventConv = auditEventConv;
        this.auditEventService = auditEventService;
    }

    @Override
    public void onMessage(ObjectMessage msg, Session session) throws JMSException {
        this.auditEventService.save(this.auditEventClass.cast(this.auditEventConv.fromMessage(msg)));
    }
}
