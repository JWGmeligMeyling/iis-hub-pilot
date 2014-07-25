package gov.hhs.onc.iishubpilot.crypto.impl;

import gov.hhs.onc.iishubpilot.crypto.HubKeyPairConfig;
import java.security.KeyPair;
import javax.annotation.Nonnegative;

public class HubKeyPairConfigImpl extends AbstractHubCryptoConfig<KeyPair> implements HubKeyPairConfig {
    private int size;

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public void setSize(@Nonnegative int size) {
        this.size = size;
    }
}
