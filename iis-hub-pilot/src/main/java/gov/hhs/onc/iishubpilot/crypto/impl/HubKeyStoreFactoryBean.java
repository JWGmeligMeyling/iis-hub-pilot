package gov.hhs.onc.iishubpilot.crypto.impl;

import gov.hhs.onc.iishubpilot.crypto.utils.HubKeyStoreUtils;
import java.security.KeyStore;
import java.security.Provider;
import javax.annotation.Nullable;
import org.springframework.beans.factory.SmartFactoryBean;
import org.springframework.core.io.Resource;

public class HubKeyStoreFactoryBean implements SmartFactoryBean<KeyStore> {
    private String type;
    private Provider prov;
    private Resource resource;
    private String pass;

    public HubKeyStoreFactoryBean(String type, Provider prov, Resource resource) {
        this(type, prov, resource, null);
    }

    public HubKeyStoreFactoryBean(String type, Provider prov, Resource resource, @Nullable String pass) {
        this.type = type;
        this.prov = prov;
        this.resource = resource;
        this.pass = pass;
    }

    @Override
    public KeyStore getObject() throws Exception {
        return HubKeyStoreUtils.readKeyStore(this.type, this.prov, this.resource.getInputStream(), this.pass);
    }

    @Override
    public boolean isEagerInit() {
        return false;
    }

    @Override
    public Class<?> getObjectType() {
        return KeyStore.class;
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
