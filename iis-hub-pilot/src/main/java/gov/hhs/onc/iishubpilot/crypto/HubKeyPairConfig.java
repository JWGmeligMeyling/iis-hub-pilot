package gov.hhs.onc.iishubpilot.crypto;

import java.security.KeyPair;
import javax.annotation.Nonnegative;

public interface HubKeyPairConfig extends HubCryptoConfig<KeyPair>, HubKeyPairDescriptor {
    public void setSize(@Nonnegative int size);
}
