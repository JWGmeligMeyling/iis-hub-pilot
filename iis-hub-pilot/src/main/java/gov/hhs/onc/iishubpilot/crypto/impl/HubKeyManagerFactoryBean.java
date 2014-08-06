package gov.hhs.onc.iishubpilot.crypto.impl;

import java.net.Socket;
import java.security.KeyStore;
import java.security.KeyStore.Builder;
import java.security.KeyStore.PasswordProtection;
import java.security.KeyStore.PrivateKeyEntry;
import java.security.KeyStore.ProtectionParameter;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.UnrecoverableEntryException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.KeyStoreBuilderParameters;
import javax.net.ssl.X509ExtendedKeyManager;

public class HubKeyManagerFactoryBean extends AbstractHubCryptoManagerFactoryBean<KeyManager> {
    private Map<String, ProtectionParameter> entryMap;

    public HubKeyManagerFactoryBean(String alg, Provider prov, KeyStore keyStore, Map<String, String> entryMap) {
        super(KeyManager.class, alg, prov, keyStore);

        this.entryMap =
            entryMap.entrySet().stream()
                .collect(Collectors.toMap(Entry::getKey, ((Entry<String, String> entry) -> new PasswordProtection(entry.getValue().toCharArray()))));
    }

    @Override
    public KeyManager getObject() throws Exception {
        KeyManagerFactory keyMgrFactory = KeyManagerFactory.getInstance(this.alg, this.prov);
        keyMgrFactory.init(new KeyStoreBuilderParameters(new Builder() {
            @Override
            public ProtectionParameter getProtectionParameter(String entryAlias) throws KeyStoreException {
                return HubKeyManagerFactoryBean.this.entryMap.get(entryAlias);
            }

            @Override
            public KeyStore getKeyStore() throws KeyStoreException {
                return HubKeyManagerFactoryBean.this.keyStore;
            }
        }));

        X509ExtendedKeyManager keyMgr = ((X509ExtendedKeyManager) keyMgrFactory.getKeyManagers()[0]);

        return new X509ExtendedKeyManager() {
            @Nullable
            @Override
            public X509Certificate[] getCertificateChain(String entryAlias) {
                PrivateKeyEntry privateKeyEntry = this.getPrivateKeyEntry(entryAlias);

                return ((privateKeyEntry != null) ? ((X509Certificate[]) privateKeyEntry.getCertificateChain()) : null);
            }

            @Nullable
            @Override
            public PrivateKey getPrivateKey(String entryAlias) {
                PrivateKeyEntry privateKeyEntry = this.getPrivateKeyEntry(entryAlias);

                return ((privateKeyEntry != null) ? privateKeyEntry.getPrivateKey() : null);
            }

            @Nullable
            @Override
            public String chooseClientAlias(String[] keyType, @Nullable Principal[] issuerNames, @Nullable Socket socket) {
                return keyMgr.chooseClientAlias(keyType, issuerNames, socket);
            }

            @Nullable
            @Override
            public String[] getClientAliases(String keyType, @Nullable Principal[] issuerNames) {
                return keyMgr.getClientAliases(keyType, issuerNames);
            }

            @Nullable
            @Override
            public String chooseServerAlias(String keyType, @Nullable Principal[] issuerNames, @Nullable Socket socket) {
                return keyMgr.chooseServerAlias(keyType, issuerNames, socket);
            }

            @Nullable
            @Override
            public String[] getServerAliases(String keyType, @Nullable Principal[] issuerNames) {
                return keyMgr.getServerAliases(keyType, issuerNames);
            }

            @Nullable
            private PrivateKeyEntry getPrivateKeyEntry(String entryAlias) {
                try {
                    return (HubKeyManagerFactoryBean.this.keyStore.isKeyEntry(entryAlias) ? ((PrivateKeyEntry) HubKeyManagerFactoryBean.this.keyStore.getEntry(
                        entryAlias, HubKeyManagerFactoryBean.this.entryMap.get(entryAlias))) : null);
                } catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableEntryException e) {
                    throw new RuntimeException(String.format("Unable to get key manager key store private key entry (alias=%s).", entryAlias), e);
                }
            }
        };
    }
}
