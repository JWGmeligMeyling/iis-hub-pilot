package gov.hhs.onc.iishubpilot.audit;

import gov.hhs.onc.iishubpilot.data.HubEntity;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Nonnegative;
import javax.annotation.Nullable;

public interface HubAuditEvent extends HubEntity<BigInteger>, Serializable {
    public String getRequestAuthType();

    public void setRequestAuthType(String reqAuthType);

    public String getRequestContextPath();

    public void setRequestContextPath(String reqContextPath);

    public String getRequestHeaders();

    public void setRequestHeaders(String reqHeaders);

    public String getRequestLocalName();

    public void setRequestLocalName(String reqLocalName);

    @Nonnegative
    public int getRequestLocalPort();

    public void setRequestLocalPort(@Nonnegative int reqLocalPort);

    public boolean hasRequestMessageId();

    @Nullable
    public String getRequestMessageId();

    public void setRequestMessageId(@Nullable String reqMsgId);

    public String getRequestMethod();

    public void setRequestMethod(String reqMethod);

    public String getRequestProtocol();

    public void setRequestProtocol(String reqProtocol);

    public boolean hasRequestQuery();

    @Nullable
    public String getRequestQuery();

    public void setRequestQuery(@Nullable String reqQuery);

    public String getRequestRemoteName();

    public void setRequestRemoteName(String reqRemoteName);

    @Nonnegative
    public int getRequestRemotePort();

    public void setRequestRemotePort(@Nonnegative int reqRemotePort);

    public String getRequestServerName();

    public void setRequestServerName(String reqServerName);

    @Nonnegative
    public int getRequestServerPort();

    public void setRequestServerPort(@Nonnegative int reqServerPort);

    public String getRequestScheme();

    public void setRequestScheme(String reqScheme);

    public String getRequestServletPath();

    public void setRequestServletPath(String reqServletPath);

    public Date getRequestTimestamp();

    public void setRequestTimestamp(Date reqTimestamp);

    public String getRequestUserPrincipal();

    public void setRequestUserPrincipal(String reqUserPrincipal);

    @Nonnegative
    public int getResponseCode();

    public void setResponseCode(@Nonnegative int respCode);

    public String getResponseHeaders();

    public void setResponseHeaders(String respHeaders);
}
