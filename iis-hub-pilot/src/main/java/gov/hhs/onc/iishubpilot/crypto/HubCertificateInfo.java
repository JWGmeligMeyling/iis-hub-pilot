package gov.hhs.onc.iishubpilot.crypto;

import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.cert.X509CertificateHolder;

public interface HubCertificateInfo extends HubCertificateDescriptor, HubCryptoInfo<X509Certificate> {
    public Extensions getExtensions() throws CertificateEncodingException;

    public X509CertificateHolder getHolder() throws CertificateEncodingException;
}
