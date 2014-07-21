package gov.hhs.onc.iishubpilot.crypto.utils;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.Provider;
import javax.annotation.Nullable;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.X509KeyManager;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.IteratorUtils;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.cxf.transport.https.AliasedX509ExtendedKeyManager;

public final class HubKeyManagerUtils {
    private static class AliasedKeyManagerTransformer implements Transformer<KeyManager, KeyManager> {
        private String entryAlias;

        public AliasedKeyManagerTransformer(@Nullable String entryAlias) {
            this.entryAlias = entryAlias;
        }

        @Override
        public KeyManager transform(KeyManager keyMgr) {
            try {
                return new AliasedX509ExtendedKeyManager(this.entryAlias, ((X509KeyManager) keyMgr));
            } catch (Exception e) {
                throw new IllegalStateException(String.format("Unable to wrap key manager (class=%s) for key store entry alias: %s", keyMgr.getClass()
                    .getName(), this.entryAlias), e);
            }
        }
    }

    private HubKeyManagerUtils() {
    }

    public static KeyManager[] buildKeyManagers(String type, Provider prov, KeyStore keyStore) throws GeneralSecurityException, IOException {
        return buildKeyManagers(type, prov, keyStore, null);
    }

    public static KeyManager[] buildKeyManagers(String type, Provider prov, KeyStore keyStore, @Nullable String entryAlias) throws GeneralSecurityException,
        IOException {
        return buildKeyManagers(type, prov, keyStore, entryAlias, null);
    }

    public static KeyManager[] buildKeyManagers(String type, Provider prov, KeyStore keyStore, @Nullable String entryAlias, @Nullable String pass)
        throws GeneralSecurityException, IOException {
        KeyManagerFactory keyMgrFactory = KeyManagerFactory.getInstance(type, prov);
        keyMgrFactory.init(keyStore, ((pass != null) ? pass.toCharArray() : ArrayUtils.EMPTY_CHAR_ARRAY));

        KeyManager[] keyMgrs = keyMgrFactory.getKeyManagers();

        return CollectionUtils.collect(IteratorUtils.arrayIterator(keyMgrs), new AliasedKeyManagerTransformer(entryAlias)).toArray(keyMgrs);
    }
}
