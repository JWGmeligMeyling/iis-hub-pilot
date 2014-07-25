package gov.hhs.onc.iishubpilot.crypto.impl;

import gov.hhs.onc.iishubpilot.crypto.HubCryptoGenerator;
import java.security.Provider;

public abstract class AbstractHubCryptoGenerator implements HubCryptoGenerator {
    protected Provider prov;

    protected AbstractHubCryptoGenerator(Provider prov) {
        this.prov = prov;
    }

    @Override
    public Provider getProvider() {
        return this.prov;
    }
}
