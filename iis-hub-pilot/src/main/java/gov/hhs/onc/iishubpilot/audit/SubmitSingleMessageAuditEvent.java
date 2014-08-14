package gov.hhs.onc.iishubpilot.audit;

import java.net.URI;
import javax.annotation.Nullable;

public interface SubmitSingleMessageAuditEvent extends HubAuditEvent {
    public boolean hasRequestDestinationId();

    @Nullable
    public String getRequestDestinationId();

    public void setRequestDestinationId(@Nullable String reqDestId);

    public boolean hasResponseDestinationId();

    @Nullable
    public String getResponseDestinationId();

    public void setResponseDestinationId(@Nullable String respDestId);

    public boolean hasResponseDestinationUri();

    @Nullable
    public URI getResponseDestinationUri();

    public void setResponseDestinationUri(@Nullable URI respDestUri);
}
