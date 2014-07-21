package gov.hhs.onc.iishubpilot.crypto.utils;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.Provider;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

public final class HubTrustManagerUtils {
    private HubTrustManagerUtils() {
    }

    public static TrustManager[] buildTrustManagers(String type, Provider prov, KeyStore keyStore) throws GeneralSecurityException, IOException {
        TrustManagerFactory trustMgrFactory = TrustManagerFactory.getInstance(type, prov);
        trustMgrFactory.init(keyStore);

        return trustMgrFactory.getTrustManagers();
    }
}
