package gov.hhs.onc.iishubpilot.crypto.impl;

import gov.hhs.onc.iishubpilot.crypto.HubCertificateInfo;
import java.math.BigInteger;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.Date;
import javax.annotation.Nullable;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.BasicConstraints;
import org.bouncycastle.asn1.x509.ExtendedKeyUsage;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.asn1.x509.GeneralNames;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateHolder;

public class HubCertificateInfoImpl extends AbstractHubCryptoInfo<X509Certificate> implements HubCertificateInfo {
    public HubCertificateInfoImpl(X509Certificate obj) {
        super(obj);
    }

    @Override
    public String getAlgorithmName() {
        return this.obj.getType();
    }

    @Override
    public ASN1ObjectIdentifier getAlgorithmOid() {
        return PKCSObjectIdentifiers.x509Certificate;
    }

    @Override
    public BasicConstraints getBasicConstraints() throws CertificateEncodingException {
        return BasicConstraints.fromExtensions(this.getExtensions());
    }

    @Override
    public boolean hasExtendedKeyUsage() {
        try {
            return !CollectionUtils.isEmpty(this.obj.getExtendedKeyUsage());
        } catch (CertificateParsingException ignored) {
        }

        return false;
    }

    @Nullable
    @Override
    public ExtendedKeyUsage getExtendedKeyUsage() throws CertificateEncodingException {
        return ExtendedKeyUsage.fromExtensions(this.getExtensions());
    }

    @Override
    public Extensions getExtensions() throws CertificateEncodingException {
        return this.getHolder().getExtensions();
    }

    @Override
    public X509CertificateHolder getHolder() throws CertificateEncodingException {
        return new JcaX509CertificateHolder(this.obj);
    }

    @Override
    public BigInteger getSerialNumber() {
        return this.obj.getSerialNumber();
    }

    @Override
    public X500Name getSubjectDn() {
        return new X500Name(this.obj.getSubjectDN().getName());
    }

    @Override
    public boolean hasSubjectAltNames() {
        try {
            return !CollectionUtils.isEmpty(this.obj.getSubjectAlternativeNames());
        } catch (CertificateParsingException ignored) {
        }

        return false;
    }

    @Nullable
    @Override
    public GeneralNames getSubjectAltNames() throws CertificateEncodingException {
        return GeneralNames.fromExtensions(this.getExtensions(), Extension.subjectAlternativeName);
    }

    @Override
    public Pair<Date, Date> getValidInterval() {
        return new ImmutablePair<>(this.obj.getNotBefore(), this.obj.getNotAfter());
    }
}
