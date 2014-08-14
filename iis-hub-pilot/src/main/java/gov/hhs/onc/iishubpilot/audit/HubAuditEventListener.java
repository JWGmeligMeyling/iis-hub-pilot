package gov.hhs.onc.iishubpilot.audit;

import javax.jms.ObjectMessage;
import org.springframework.jms.listener.SessionAwareMessageListener;

public interface HubAuditEventListener<T extends HubAuditEvent, U extends HubAuditDao<T>, V extends HubAuditService<T, U>> extends
    SessionAwareMessageListener<ObjectMessage> {
}
