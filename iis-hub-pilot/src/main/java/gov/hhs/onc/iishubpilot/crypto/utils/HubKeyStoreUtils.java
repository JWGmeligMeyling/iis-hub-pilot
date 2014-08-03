package gov.hhs.onc.iishubpilot.crypto.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.Provider;

public final class HubKeyStoreUtils {
    private HubKeyStoreUtils() {
    }

    public static void writeKeyStore(KeyStore keyStore, OutputStream outStream, String pass) throws GeneralSecurityException, IOException {
        keyStore.store(outStream, pass.toCharArray());

        outStream.flush();
    }

    public static KeyStore readKeyStore(String type, Provider prov, InputStream inStream, String pass) throws GeneralSecurityException, IOException {
        return loadKeyStore(KeyStore.getInstance(type, prov), inStream, pass);
    }

    private static KeyStore loadKeyStore(KeyStore keyStore, InputStream inStream, String pass) throws GeneralSecurityException, IOException {
        keyStore.load(inStream, pass.toCharArray());

        return keyStore;
    }
}
