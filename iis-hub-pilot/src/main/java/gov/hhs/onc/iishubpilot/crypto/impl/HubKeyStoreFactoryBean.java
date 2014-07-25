package gov.hhs.onc.iishubpilot.crypto.impl;

import gov.hhs.onc.iishubpilot.crypto.utils.HubKeyStoreUtils;
import java.security.KeyStore;
import java.security.Provider;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.SmartFactoryBean;
import org.springframework.core.io.Resource;

public class HubKeyStoreFactoryBean implements SmartFactoryBean<KeyStore> {
    private String type;
    private Provider prov;
    private Resource resource;
    private String pass;

    public HubKeyStoreFactoryBean(String type, Provider prov, Resource resource) {
        this(type, prov, resource, StringUtils.EMPTY);
    }

    public HubKeyStoreFactoryBean(String type, Provider prov, Resource resource, String pass) {
        this.type = type;
        this.prov = prov;
        this.resource = resource;
        this.pass = pass;
    }

    @Override
    public KeyStore getObject() throws Exception {
        return (this.resource.exists() ? HubKeyStoreUtils.readKeyStore(this.type, this.prov, this.resource.getInputStream(), this.pass) : HubKeyStoreUtils
            .createKeyStore(this.type, this.prov));
    }

    @Override
    public boolean isEagerInit() {
        return false;
    }

    @Override
    public Class<?> getObjectType() {
        return KeyStore.class;
    }

    public String getPassword() {
        return this.pass;
    }

    @Override
    public boolean isPrototype() {
        return false;
    }

    public Provider getProvider() {
        return this.prov;
    }

    public Resource getResource() {
        return this.resource;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public String getType() {
        return this.type;
    }
}
