package gov.hhs.onc.iishubpilot.crypto;

import org.bouncycastle.asn1.ASN1ObjectIdentifier;

public interface HubCryptoDescriptor<T> {
    public String getAlgorithmName();

    public ASN1ObjectIdentifier getAlgorithmOid();
}
