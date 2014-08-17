package gov.hhs.onc.iishubpilot.audit.impl;

import gov.hhs.onc.iishubpilot.audit.HubAuditEvent;
import gov.hhs.onc.iishubpilot.data.impl.AbstractHubEntity;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Nonnegative;
import javax.annotation.Nullable;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public abstract class AbstractHubAuditEvent extends AbstractHubEntity<BigInteger> implements HubAuditEvent {
    protected String reqAuthType;
    protected String reqContextPath;
    protected String reqHeaders;
    protected String reqLocalName;
    protected int reqLocalPort;
    protected String reqMethod;
    protected String reqMsgId;
    protected String reqPath;
    protected String reqProtocol;
    protected String reqQuery;
    protected String reqRemoteName;
    protected int reqRemotePort;
    protected String reqServerName;
    protected int reqServerPort;
    protected String reqScheme;
    protected String reqServletPath;
    protected String reqUserPrincipal;
    protected Date reqTimestamp;
    protected int respCode;
    protected String respHeaders;

    private final static long serialVersionUID = 0L;

    @Column(name = "req_auth_type", nullable = false)
    @Override
    public String getRequestAuthType() {
        return this.reqAuthType;
    }

    @Override
    public void setRequestAuthType(String reqAuthType) {
        this.reqAuthType = reqAuthType;
    }

    @Column(name = "event_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Override
    public BigInteger getId() {
        return super.getId();
    }

    @Column(name = "req_context_path", nullable = false)
    @Override
    public String getRequestContextPath() {
        return this.reqContextPath;
    }

    @Override
    public void setRequestContextPath(String reqContextPath) {
        this.reqContextPath = reqContextPath;
    }

    @Column(name = "req_headers", nullable = false)
    @Override
    public String getRequestHeaders() {
        return this.reqHeaders;
    }

    @Override
    public void setRequestHeaders(String reqHeaders) {
        this.reqHeaders = reqHeaders;
    }

    @Column(name = "req_local_name", nullable = false)
    @Override
    public String getRequestLocalName() {
        return this.reqLocalName;
    }

    @Override
    public void setRequestLocalName(String reqLocalName) {
        this.reqLocalName = reqLocalName;
    }

    @Column(name = "req_local_port", nullable = false)
    @Nonnegative
    @Override
    public int getRequestLocalPort() {
        return this.reqLocalPort;
    }

    @Override
    public void setRequestLocalPort(@Nonnegative int reqLocalPort) {
        this.reqLocalPort = reqLocalPort;
    }

    @Override
    public boolean hasRequestMessageId() {
        return (this.reqMsgId != null);
    }

    @Column(name = "req_msg_id")
    @Nullable
    @Override
    public String getRequestMessageId() {
        return this.reqMsgId;
    }

    @Override
    public void setRequestMessageId(@Nullable String reqMsgId) {
        this.reqMsgId = reqMsgId;
    }

    @Column(name = "req_method", nullable = false)
    @Override
    public String getRequestMethod() {
        return this.reqMethod;
    }

    @Override
    public void setRequestMethod(String reqMethod) {
        this.reqMethod = reqMethod;
    }

    @Override
    public boolean hasRequestPath() {
        return (this.reqPath != null);
    }

    @Column(name = "req_path")
    @Nullable
    @Override
    public String getRequestPath() {
        return this.reqPath;
    }

    @Override
    public void setRequestPath(@Nullable String reqPath) {
        this.reqPath = reqPath;
    }

    @Column(name = "req_protocol", nullable = false)
    @Override
    public String getRequestProtocol() {
        return this.reqProtocol;
    }

    @Override
    public void setRequestProtocol(String reqProtocol) {
        this.reqProtocol = reqProtocol;
    }

    @Override
    public boolean hasRequestQuery() {
        return (this.reqQuery != null);
    }

    @Column(name = "req_query")
    @Nullable
    @Override
    public String getRequestQuery() {
        return this.reqQuery;
    }

    @Override
    public void setRequestQuery(@Nullable String reqQuery) {
        this.reqQuery = reqQuery;
    }

    @Column(name = "req_remote_name", nullable = false)
    @Override
    public String getRequestRemoteName() {
        return this.reqRemoteName;
    }

    @Override
    public void setRequestRemoteName(String reqRemoteName) {
        this.reqRemoteName = reqRemoteName;
    }

    @Column(name = "req_remote_port", nullable = false)
    @Nonnegative
    @Override
    public int getRequestRemotePort() {
        return this.reqRemotePort;
    }

    @Override
    public void setRequestRemotePort(@Nonnegative int reqRemotePort) {
        this.reqRemotePort = reqRemotePort;
    }

    @Column(name = "req_server_name", nullable = false)
    @Override
    public String getRequestServerName() {
        return this.reqServerName;
    }

    @Override
    public void setRequestServerName(String reqServerName) {
        this.reqServerName = reqServerName;
    }

    @Column(name = "req_server_port", nullable = false)
    @Nonnegative
    @Override
    public int getRequestServerPort() {
        return this.reqServerPort;
    }

    @Override
    public void setRequestServerPort(@Nonnegative int reqServerPort) {
        this.reqServerPort = reqServerPort;
    }

    @Column(name = "req_scheme", nullable = false)
    @Override
    public String getRequestScheme() {
        return this.reqScheme;
    }

    @Override
    public void setRequestScheme(String reqScheme) {
        this.reqScheme = reqScheme;
    }

    @Column(name = "req_servlet_path", nullable = false)
    @Override
    public String getRequestServletPath() {
        return this.reqServletPath;
    }

    @Override
    public void setRequestServletPath(String reqServletPath) {
        this.reqServletPath = reqServletPath;
    }

    @Column(name = "req_timestamp", nullable = false)
    @Override
    @Temporal(TemporalType.TIMESTAMP)
    public Date getRequestTimestamp() {
        return this.reqTimestamp;
    }

    @Override
    public void setRequestTimestamp(Date reqTimestamp) {
        this.reqTimestamp = reqTimestamp;
    }

    @Column(name = "req_user_principal", nullable = false)
    @Override
    public String getRequestUserPrincipal() {
        return this.reqUserPrincipal;
    }

    @Override
    public void setRequestUserPrincipal(String reqUserPrincipal) {
        this.reqUserPrincipal = reqUserPrincipal;
    }

    @Column(name = "resp_code", nullable = false)
    @Nonnegative
    @Override
    public int getResponseCode() {
        return this.respCode;
    }

    @Override
    public void setResponseCode(@Nonnegative int respCode) {
        this.respCode = respCode;
    }

    @Column(name = "resp_headers", nullable = false)
    @Override
    public String getResponseHeaders() {
        return this.respHeaders;
    }

    @Override
    public void setResponseHeaders(String respHeaders) {
        this.respHeaders = respHeaders;
    }
}
