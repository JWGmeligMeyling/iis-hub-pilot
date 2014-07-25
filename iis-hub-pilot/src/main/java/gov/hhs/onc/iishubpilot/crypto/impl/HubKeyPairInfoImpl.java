package gov.hhs.onc.iishubpilot.crypto.impl;

import gov.hhs.onc.iishubpilot.crypto.HubKeyPairInfo;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAKey;
import javax.annotation.Nonnegative;
import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.AuthorityKeyIdentifier;
import org.bouncycastle.asn1.x509.SubjectKeyIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;

public class HubKeyPairInfoImpl extends AbstractHubCryptoInfo<KeyPair> implements HubKeyPairInfo {
    public HubKeyPairInfoImpl(KeyPair obj) {
        super(obj);
    }

    @Override
    public String getAlgorithmName() {
        return this.obj.getPublic().getAlgorithm();
    }

    @Override
    public ASN1ObjectIdentifier getAlgorithmOid() {
        return this.getSubjectPublicKeyInfo().getAlgorithm().getAlgorithm();
    }

    @Override
    public AuthorityKeyIdentifier getAuthorityKeyId() {
        return new AuthorityKeyIdentifier(this.getSubjectPublicKeyInfo());
    }

    @Override
    public PublicKey getPublicKey() {
        return this.obj.getPublic();
    }

    @Override
    public PrivateKey getPrivateKey() {
        return this.obj.getPrivate();
    }

    @Override
    public PrivateKeyInfo getPrivateKeyInfo() {
        return PrivateKeyInfo.getInstance(this.getPrivateKey().getEncoded());
    }

    @Nonnegative
    @Override
    public int getSize() {
        return ((RSAKey) this.getPublicKey()).getModulus().bitLength();
    }

    @Override
    public SubjectKeyIdentifier getSubjectKeyId() {
        return new SubjectKeyIdentifier(DigestUtils.getSha1Digest().digest(this.getPublicKey().getEncoded()));
    }

    @Override
    public SubjectPublicKeyInfo getSubjectPublicKeyInfo() {
        return SubjectPublicKeyInfo.getInstance(this.getPublicKey().getEncoded());
    }
}
