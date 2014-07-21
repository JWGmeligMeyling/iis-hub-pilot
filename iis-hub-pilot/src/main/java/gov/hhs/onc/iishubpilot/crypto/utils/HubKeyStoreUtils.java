package gov.hhs.onc.iishubpilot.crypto.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.Provider;
import javax.annotation.Nullable;
import org.apache.commons.lang3.ArrayUtils;

public final class HubKeyStoreUtils {
    private HubKeyStoreUtils() {
    }

    public static void writeKeyStore(KeyStore keyStore, OutputStream outStream) throws GeneralSecurityException, IOException {
        writeKeyStore(keyStore, outStream, null);
    }

    public static void writeKeyStore(KeyStore keyStore, OutputStream outStream, @Nullable String pass) throws GeneralSecurityException, IOException {
        keyStore.store(outStream, ((pass != null) ? pass.toCharArray() : ArrayUtils.EMPTY_CHAR_ARRAY));

        outStream.flush();
    }

    public static KeyStore readKeyStore(String type, Provider prov, InputStream inStream) throws GeneralSecurityException, IOException {
        return readKeyStore(type, prov, inStream, null);
    }

    public static KeyStore readKeyStore(String type, Provider prov, InputStream inStream, @Nullable String pass) throws GeneralSecurityException, IOException {
        return loadKeyStore(KeyStore.getInstance(type, prov), inStream, pass);
    }

    public static KeyStore createKeyStore(String type, Provider prov) throws GeneralSecurityException, IOException {
        return loadKeyStore(KeyStore.getInstance(type, prov));
    }

    public static KeyStore loadKeyStore(KeyStore keyStore) throws GeneralSecurityException, IOException {
        return loadKeyStore(keyStore, null);
    }

    public static KeyStore loadKeyStore(KeyStore keyStore, @Nullable InputStream inStream) throws GeneralSecurityException, IOException {
        return loadKeyStore(keyStore, inStream, null);
    }

    public static KeyStore loadKeyStore(KeyStore keyStore, @Nullable InputStream inStream, @Nullable String pass) throws GeneralSecurityException, IOException {
        keyStore.load(inStream, ((pass != null) ? pass.toCharArray() : ArrayUtils.EMPTY_CHAR_ARRAY));

        return keyStore;
    }
}
