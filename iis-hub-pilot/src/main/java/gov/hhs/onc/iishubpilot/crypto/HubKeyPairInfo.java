package gov.hhs.onc.iishubpilot.crypto;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.AuthorityKeyIdentifier;
import org.bouncycastle.asn1.x509.SubjectKeyIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;

public interface HubKeyPairInfo extends HubCryptoInfo<KeyPair>, HubKeyPairDescriptor {
    public AuthorityKeyIdentifier getAuthorityKeyId();

    public PublicKey getPublicKey();

    public PrivateKey getPrivateKey();

    public PrivateKeyInfo getPrivateKeyInfo();

    public SubjectKeyIdentifier getSubjectKeyId();

    public SubjectPublicKeyInfo getSubjectPublicKeyInfo();
}
