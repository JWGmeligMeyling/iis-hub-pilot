package gov.hhs.onc.iishubpilot.crypto.impl;

import java.security.KeyStore;
import java.security.Provider;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.X509KeyManager;
import org.apache.cxf.transport.https.AliasedX509ExtendedKeyManager;

public class HubKeyManagerFactoryBean extends AbstractHubCryptoManagerFactoryBean<KeyManager> {
    private String entryAlias;
    private String entryPass;

    public HubKeyManagerFactoryBean(String alg, Provider prov, KeyStore keyStore, String entryAlias, String entryPass) {
        super(KeyManager.class, alg, prov, keyStore);

        this.entryAlias = entryAlias;
        this.entryPass = entryPass;
    }

    @Override
    public KeyManager getObject() throws Exception {
        KeyManagerFactory keyMgrFactory = KeyManagerFactory.getInstance(this.alg, this.prov);
        keyMgrFactory.init(this.keyStore, this.entryPass.toCharArray());

        return new AliasedX509ExtendedKeyManager(this.entryAlias, ((X509KeyManager) keyMgrFactory.getKeyManagers()[0]));
    }
}
