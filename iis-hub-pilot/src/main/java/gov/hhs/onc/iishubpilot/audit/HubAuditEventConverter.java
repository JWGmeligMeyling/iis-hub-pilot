package gov.hhs.onc.iishubpilot.audit;

import org.springframework.jms.support.converter.MessageConverter;

public interface HubAuditEventConverter<T extends HubAuditEvent> extends MessageConverter {
}
