package gov.hhs.onc.iishubpilot.crypto.impl;

import gov.hhs.onc.iishubpilot.crypto.HubCertificateConfig;
import gov.hhs.onc.iishubpilot.crypto.HubCertificateGenerator;
import gov.hhs.onc.iishubpilot.crypto.HubCertificateInfo;
import gov.hhs.onc.iishubpilot.crypto.HubKeyPairInfo;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.Provider;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Date;
import javax.annotation.Nullable;
import org.apache.commons.lang3.tuple.Pair;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.BasicConstraints;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.crypto.util.PrivateKeyFactory;
import org.bouncycastle.operator.OperatorException;
import org.bouncycastle.operator.bc.BcRSAContentSignerBuilder;

public class HubCertificateGeneratorImpl extends AbstractHubCryptoGenerator implements HubCertificateGenerator {
    public HubCertificateGeneratorImpl(Provider prov) {
        super(prov);
    }

    @Override
    public HubCertificateInfo generate(HubKeyPairInfo keyPairInfo, HubCertificateConfig certConfig) throws GeneralSecurityException, IOException,
        OperatorException {
        return this.generate(null, null, keyPairInfo, certConfig);
    }

    @Override
    public HubCertificateInfo generate(@Nullable HubKeyPairInfo issuerKeyPairInfo, @Nullable HubCertificateInfo issuerCertInfo, HubKeyPairInfo keyPairInfo,
        HubCertificateConfig certConfig) throws GeneralSecurityException, IOException, OperatorException {
        boolean selfIssued = (issuerCertInfo == null);
        X500Name certSubjDn = certConfig.getSubjectDn();
        Pair<Date, Date> certValidInterval = certConfig.getValidInterval();

        X509v3CertificateBuilder certBuilder =
            new X509v3CertificateBuilder((selfIssued ? certSubjDn : issuerCertInfo.getSubjectDn()), certConfig.getSerialNumber(), certValidInterval.getLeft(),
                certValidInterval.getRight(), certSubjDn, keyPairInfo.getSubjectPublicKeyInfo());
        // noinspection ConstantConditions
        certBuilder.addExtension(Extension.authorityKeyIdentifier, false, (selfIssued ? keyPairInfo : issuerKeyPairInfo).getAuthorityKeyId());
        certBuilder.addExtension(Extension.subjectKeyIdentifier, false, keyPairInfo.getSubjectKeyId());

        BasicConstraints certBasicConstraints = certConfig.getBasicConstraints();
        certBuilder.addExtension(Extension.basicConstraints, false, certBasicConstraints);

        if (certConfig.hasExtendedKeyUsage()) {
            certBuilder.addExtension(Extension.extendedKeyUsage, false, certConfig.getExtendedKeyUsage());
        }

        if (certConfig.hasSubjectAltNames()) {
            certBuilder.addExtension(Extension.subjectAlternativeName, false, certConfig.getSubjectAltNames());
        }

        if (!selfIssued && issuerCertInfo.hasSubjectAltNames()) {
            certBuilder.addExtension(Extension.issuerAlternativeName, false, issuerCertInfo.getSubjectAltNames());
        }

        try (
            InputStream certInStream =
                new ByteArrayInputStream(certBuilder.build(
                    new BcRSAContentSignerBuilder(certConfig.getSignatureAlgorithmId(), certConfig.getSignatureDigestAlgorithmId()).build(PrivateKeyFactory
                        .createKey((selfIssued ? keyPairInfo.getPrivateKeyInfo() : issuerKeyPairInfo.getPrivateKeyInfo())))).getEncoded())) {
            return new HubCertificateInfoImpl(((X509Certificate) CertificateFactory.getInstance(certConfig.getAlgorithmName(), this.prov).generateCertificate(
                certInStream)));
        }
    }
}
