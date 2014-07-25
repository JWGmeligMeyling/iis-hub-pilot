package gov.hhs.onc.iishubpilot.crypto.impl;

import java.security.KeyStore;
import java.security.Provider;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

public class HubTrustManagerFactoryBean extends AbstractHubCryptoManagerFactoryBean<TrustManager> {
    public HubTrustManagerFactoryBean(String alg, Provider prov, KeyStore keyStore) {
        super(TrustManager.class, alg, prov, keyStore);
    }

    @Override
    public TrustManager getObject() throws Exception {
        TrustManagerFactory trustMgrFactory = TrustManagerFactory.getInstance(this.alg, this.prov);
        trustMgrFactory.init(this.keyStore);

        return trustMgrFactory.getTrustManagers()[0];
    }
}
