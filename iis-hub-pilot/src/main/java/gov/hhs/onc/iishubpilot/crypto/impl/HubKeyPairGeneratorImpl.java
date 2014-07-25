package gov.hhs.onc.iishubpilot.crypto.impl;

import gov.hhs.onc.iishubpilot.crypto.HubKeyPairConfig;
import gov.hhs.onc.iishubpilot.crypto.HubKeyPairGenerator;
import gov.hhs.onc.iishubpilot.crypto.HubKeyPairInfo;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyPairGenerator;
import java.security.Provider;
import java.security.SecureRandom;

public class HubKeyPairGeneratorImpl extends AbstractHubCryptoGenerator implements HubKeyPairGenerator {
    private SecureRandom rand;

    public HubKeyPairGeneratorImpl(Provider prov, SecureRandom rand) {
        super(prov);

        this.rand = rand;
    }

    @Override
    public HubKeyPairInfo generate(HubKeyPairConfig keyConfig) throws GeneralSecurityException, IOException {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(keyConfig.getAlgorithmName(), this.prov);
        keyPairGen.initialize(keyConfig.getSize(), this.rand);

        return new HubKeyPairInfoImpl(keyPairGen.generateKeyPair());
    }

    @Override
    public SecureRandom getRandom() {
        return this.rand;
    }
}
