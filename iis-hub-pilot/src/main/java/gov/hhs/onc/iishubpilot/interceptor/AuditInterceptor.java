package gov.hhs.onc.iishubpilot.interceptor;

import gov.hhs.onc.iishubpilot.audit.HubAuditEvent;
import org.apache.cxf.binding.soap.SoapMessage;

public interface AuditInterceptor<T extends HubAuditEvent> extends HubOperationInterceptor<SoapMessage> {
}
