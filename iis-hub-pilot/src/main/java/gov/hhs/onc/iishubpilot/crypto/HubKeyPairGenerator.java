package gov.hhs.onc.iishubpilot.crypto;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;

public interface HubKeyPairGenerator extends HubCryptoGenerator {
    public HubKeyPairInfo generate(HubKeyPairConfig keyConfig) throws GeneralSecurityException, IOException;

    public SecureRandom getRandom();
}
