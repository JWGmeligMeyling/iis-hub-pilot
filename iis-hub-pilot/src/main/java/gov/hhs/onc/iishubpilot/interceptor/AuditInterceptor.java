package gov.hhs.onc.iishubpilot.interceptor;

import gov.hhs.onc.iishubpilot.audit.HubAuditEvent;
import gov.hhs.onc.iishubpilot.audit.HubAuditEventSender;
import org.apache.cxf.binding.soap.SoapMessage;

public interface AuditInterceptor<T extends HubAuditEvent, U extends HubAuditEventSender<T>> extends HubOperationInterceptor<SoapMessage> {
}
