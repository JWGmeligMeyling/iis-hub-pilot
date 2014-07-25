package gov.hhs.onc.iishubpilot.crypto.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.Provider;
import javax.annotation.Nullable;
import org.apache.commons.lang3.StringUtils;

public final class HubKeyStoreUtils {
    private HubKeyStoreUtils() {
    }

    public static void writeKeyStore(KeyStore keyStore, OutputStream outStream) throws GeneralSecurityException, IOException {
        writeKeyStore(keyStore, outStream, StringUtils.EMPTY);
    }

    public static void writeKeyStore(KeyStore keyStore, OutputStream outStream, String pass) throws GeneralSecurityException, IOException {
        keyStore.store(outStream, pass.toCharArray());

        outStream.flush();
    }

    public static KeyStore readKeyStore(String type, Provider prov, InputStream inStream) throws GeneralSecurityException, IOException {
        return readKeyStore(type, prov, inStream, StringUtils.EMPTY);
    }

    public static KeyStore readKeyStore(String type, Provider prov, InputStream inStream, String pass) throws GeneralSecurityException, IOException {
        return loadKeyStore(KeyStore.getInstance(type, prov), inStream, pass);
    }

    public static KeyStore createKeyStore(String type, Provider prov) throws GeneralSecurityException, IOException {
        return loadKeyStore(KeyStore.getInstance(type, prov));
    }

    private static KeyStore loadKeyStore(KeyStore keyStore) throws GeneralSecurityException, IOException {
        return loadKeyStore(keyStore, null);
    }

    private static KeyStore loadKeyStore(KeyStore keyStore, @Nullable InputStream inStream) throws GeneralSecurityException, IOException {
        return loadKeyStore(keyStore, inStream, StringUtils.EMPTY);
    }

    private static KeyStore loadKeyStore(KeyStore keyStore, @Nullable InputStream inStream, String pass) throws GeneralSecurityException, IOException {
        keyStore.load(inStream, pass.toCharArray());

        return keyStore;
    }
}
