package gov.hhs.onc.iishubpilot.crypto.impl;

import gov.hhs.onc.iishubpilot.crypto.HubCryptoConfig;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;

public abstract class AbstractHubCryptoConfig<T> extends AbstractHubCryptoDescriptor<T> implements HubCryptoConfig<T> {
    protected String algName;
    protected ASN1ObjectIdentifier algOid;

    @Override
    public String getAlgorithmName() {
        return this.algName;
    }

    @Override
    public void setAlgorithmName(String algName) {
        this.algName = algName;
    }

    @Override
    public ASN1ObjectIdentifier getAlgorithmOid() {
        return this.algOid;
    }

    @Override
    public void setAlgorithmOid(ASN1ObjectIdentifier algOid) {
        this.algOid = algOid;
    }
}
