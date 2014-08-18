package gov.hhs.onc.iishubpilot.audit;

public interface SubmitSingleMessageAuditEventListener
    extends
    HubAuditEventListener<SubmitSingleMessageAuditEvent, SubmitSingleMessageAuditEventConverter, SubmitSingleMessageAuditEventDao, SubmitSingleMessageAuditEventService> {
}
