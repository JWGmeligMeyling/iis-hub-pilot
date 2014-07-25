package gov.hhs.onc.iishubpilot.crypto;

import org.bouncycastle.asn1.ASN1ObjectIdentifier;

public interface HubCryptoConfig<T> extends HubCryptoDescriptor<T> {
    public void setAlgorithmName(String algName);

    public void setAlgorithmOid(ASN1ObjectIdentifier algOid);
}
