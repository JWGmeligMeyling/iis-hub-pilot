package gov.hhs.onc.iishubpilot.crypto.impl;

import gov.hhs.onc.iishubpilot.crypto.HubCertificateConfig;
import gov.hhs.onc.iishubpilot.crypto.utils.HubCryptoAlgorithmUtils;
import java.math.BigInteger;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.Date;
import javax.annotation.Nonnegative;
import javax.annotation.Nullable;
import org.apache.commons.lang3.tuple.Pair;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.BasicConstraints;
import org.bouncycastle.asn1.x509.ExtendedKeyUsage;
import org.bouncycastle.asn1.x509.GeneralNames;

public class HubCertificateConfigImpl extends AbstractHubCryptoConfig<X509Certificate> implements HubCertificateConfig {
    private BasicConstraints basicConstraints;
    private ExtendedKeyUsage extKeyUsage;
    private BigInteger serialNum;
    private String sigAlgName;
    private X500Name subjDn;
    private GeneralNames subjAltNames;
    private Pair<Date, Date> validInterval;

    @Override
    public BasicConstraints getBasicConstraints() throws CertificateEncodingException {
        return this.basicConstraints;
    }

    @Override
    public void setBasicConstraints(BasicConstraints basicConstraints) {
        this.basicConstraints = basicConstraints;
    }

    @Override
    public boolean hasExtendedKeyUsage() {
        return (this.extKeyUsage != null);
    }

    @Nullable
    @Override
    public ExtendedKeyUsage getExtendedKeyUsage() throws CertificateEncodingException {
        return this.extKeyUsage;
    }

    @Override
    public void setExtendedKeyUsage(@Nullable ExtendedKeyUsage extKeyUsage) {
        this.extKeyUsage = extKeyUsage;
    }

    @Nonnegative
    @Override
    public BigInteger getSerialNumber() {
        return this.serialNum;
    }

    @Override
    public void setSerialNumber(@Nonnegative BigInteger serialNum) {
        this.serialNum = serialNum;
    }

    @Override
    public AlgorithmIdentifier getSignatureAlgorithmId() {
        return HubCryptoAlgorithmUtils.SIG_ALG_ID_FINDER.find(this.sigAlgName);
    }

    @Override
    public String getSignatureAlgorithmName() {
        return this.sigAlgName;
    }

    @Override
    public void setSignatureAlgorithmName(String sigAlgName) {
        this.sigAlgName = sigAlgName;
    }

    @Override
    public AlgorithmIdentifier getSignatureDigestAlgorithmId() {
        return HubCryptoAlgorithmUtils.DIGEST_ALG_ID_FINDER.find(this.getSignatureAlgorithmId());
    }

    @Override
    public X500Name getSubjectDn() {
        return this.subjDn;
    }

    @Override
    public void setSubjectDn(X500Name subjDn) {
        this.subjDn = subjDn;
    }

    @Override
    public boolean hasSubjectAltNames() {
        return (this.subjAltNames != null);
    }

    @Nullable
    @Override
    public GeneralNames getSubjectAltNames() {
        return this.subjAltNames;
    }

    @Override
    public void setSubjectAltNames(@Nullable GeneralNames subjAltNames) {
        this.subjAltNames = subjAltNames;
    }

    @Override
    public Pair<Date, Date> getValidInterval() {
        return this.validInterval;
    }

    @Override
    public void setValidInterval(Pair<Date, Date> validInterval) {
        this.validInterval = validInterval;
    }
}
