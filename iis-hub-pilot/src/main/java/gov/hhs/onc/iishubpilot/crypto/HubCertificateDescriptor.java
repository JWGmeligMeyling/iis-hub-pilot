package gov.hhs.onc.iishubpilot.crypto;

import java.math.BigInteger;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.Date;
import javax.annotation.Nonnegative;
import javax.annotation.Nullable;
import org.apache.commons.lang3.tuple.Pair;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.BasicConstraints;
import org.bouncycastle.asn1.x509.ExtendedKeyUsage;
import org.bouncycastle.asn1.x509.GeneralNames;

public interface HubCertificateDescriptor extends HubCryptoDescriptor<X509Certificate> {
    public BasicConstraints getBasicConstraints() throws CertificateEncodingException;

    public boolean hasExtendedKeyUsage();

    @Nullable
    public ExtendedKeyUsage getExtendedKeyUsage() throws CertificateEncodingException;

    @Nonnegative
    public BigInteger getSerialNumber();

    public X500Name getSubjectDn();

    public boolean hasSubjectAltNames();

    @Nullable
    public GeneralNames getSubjectAltNames() throws CertificateEncodingException;

    public Pair<Date, Date> getValidInterval();
}
