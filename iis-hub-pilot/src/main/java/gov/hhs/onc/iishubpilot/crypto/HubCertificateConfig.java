package gov.hhs.onc.iishubpilot.crypto;

import java.math.BigInteger;
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

public interface HubCertificateConfig extends HubCertificateDescriptor, HubCryptoConfig<X509Certificate> {
    public void setBasicConstraints(BasicConstraints basicConstraints);

    public void setExtendedKeyUsage(@Nullable ExtendedKeyUsage extKeyUsage);

    public void setSerialNumber(@Nonnegative BigInteger serialNum);

    public AlgorithmIdentifier getSignatureAlgorithmId();

    public String getSignatureAlgorithmName();

    public void setSignatureAlgorithmName(String sigAlgName);

    public AlgorithmIdentifier getSignatureDigestAlgorithmId();

    public void setSubjectDn(X500Name subjDn);

    public void setSubjectAltNames(@Nullable GeneralNames subjAltNames);

    public void setValidInterval(Pair<Date, Date> validInterval);
}
