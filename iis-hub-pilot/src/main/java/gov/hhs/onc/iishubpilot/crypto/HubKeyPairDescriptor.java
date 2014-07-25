package gov.hhs.onc.iishubpilot.crypto;

import java.security.KeyPair;
import javax.annotation.Nonnegative;

public interface HubKeyPairDescriptor extends HubCryptoDescriptor<KeyPair> {
    @Nonnegative
    public int getSize();
}
