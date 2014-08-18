package gov.hhs.onc.iishubpilot.audit;

import javax.jms.ObjectMessage;
import org.springframework.jms.listener.SessionAwareMessageListener;

public interface HubAuditEventListener<T extends HubAuditEvent, U extends HubAuditEventConverter<T>, V extends HubAuditEventDao<T>, W extends HubAuditEventService<T, V>>
    extends SessionAwareMessageListener<ObjectMessage> {
}
