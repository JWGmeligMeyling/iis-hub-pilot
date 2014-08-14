package gov.hhs.onc.iishubpilot.interceptor.impl;

import gov.hhs.onc.iishubpilot.audit.SubmitSingleMessageAuditEvent;
import gov.hhs.onc.iishubpilot.audit.SubmitSingleMessageAuditEventSender;
import gov.hhs.onc.iishubpilot.audit.impl.SubmitSingleMessageAuditEventImpl;
import org.apache.cxf.binding.soap.SoapMessage;

public class SubmitSingleMessageAuditInterceptor extends AbstractAuditInterceptor<SubmitSingleMessageAuditEvent, SubmitSingleMessageAuditEventSender> {
    public SubmitSingleMessageAuditInterceptor(String phase, SubmitSingleMessageAuditEventSender auditEventSender) {
        super(phase, auditEventSender);
    }

    @Override
    protected SubmitSingleMessageAuditEvent createAuditEvent(SoapMessage msg) throws Exception {
        return new SubmitSingleMessageAuditEventImpl();
    }
}
