package gov.hhs.onc.iishubpilot.audit.impl;

import gov.hhs.onc.iishubpilot.audit.SubmitSingleMessageAuditEvent;
import javax.annotation.Nullable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "auditEventSubmitSingleMsg")
@Table(name = "audit_events_submit_single_message")
public class SubmitSingleMessageAuditEventImpl extends AbstractHubAuditEvent implements SubmitSingleMessageAuditEvent {
    private final static long serialVersionUID = 0L;

    private String reqDestId;
    private String respDestId;
    private String respDestUri;

    @Override
    public boolean hasRequestDestinationId() {
        return (this.reqDestId != null);
    }

    @Column(name = "req_dest_id")
    @Nullable
    @Override
    public String getRequestDestinationId() {
        return this.reqDestId;
    }

    @Override
    public void setRequestDestinationId(@Nullable String reqDestId) {
        this.reqDestId = reqDestId;
    }

    @Override
    public boolean hasResponseDestinationId() {
        return (this.respDestId != null);
    }

    @Column(name = "resp_dest_id")
    @Nullable
    @Override
    public String getResponseDestinationId() {
        return this.respDestId;
    }

    @Override
    public void setResponseDestinationId(@Nullable String respDestId) {
        this.respDestId = respDestId;
    }

    @Override
    public boolean hasResponseDestinationUri() {
        return (this.respDestUri != null);
    }

    @Column(name = "resp_dest_uri")
    @Nullable
    @Override
    public String getResponseDestinationUri() {
        return this.respDestUri;
    }

    @Override
    public void setResponseDestinationUri(@Nullable String respDestUri) {
        this.respDestUri = respDestUri;
    }
}
