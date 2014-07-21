package gov.hhs.onc.iishubpilot.crypto.impl;

import gov.hhs.onc.iishubpilot.crypto.utils.HubKeyManagerUtils;
import java.security.KeyStore;
import java.security.Provider;
import javax.annotation.Nullable;
import javax.net.ssl.KeyManager;
import org.springframework.beans.factory.SmartFactoryBean;

public class HubKeyManagersFactoryBean implements SmartFactoryBean<KeyManager[]> {
    private String type;
    private Provider prov;
    private KeyStore keyStore;
    private String entryAlias;
    private String pass;

    public HubKeyManagersFactoryBean(String type, Provider prov, KeyStore keyStore) {
        this(type, prov, keyStore, null);
    }

    public HubKeyManagersFactoryBean(String type, Provider prov, KeyStore keyStore, @Nullable String entryAlias) {
        this(type, prov, keyStore, entryAlias, null);
    }

    public HubKeyManagersFactoryBean(String type, Provider prov, KeyStore keyStore, @Nullable String entryAlias, @Nullable String pass) {
        this.type = type;
        this.prov = prov;
        this.keyStore = keyStore;
        this.entryAlias = entryAlias;
        this.pass = pass;
    }

    @Override
    public KeyManager[] getObject() throws Exception {
        return HubKeyManagerUtils.buildKeyManagers(this.type, this.prov, this.keyStore, this.entryAlias, this.pass);
    }

    @Override
    public boolean isEagerInit() {
        return false;
    }

    @Override
    public Class<?> getObjectType() {
        return KeyManager[].class;
    }

    @Override
    public boolean isPrototype() {
        return false;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
