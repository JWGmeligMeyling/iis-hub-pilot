package gov.hhs.onc.iishubpilot.crypto.impl;

import gov.hhs.onc.iishubpilot.crypto.utils.HubTrustManagerUtils;
import java.security.KeyStore;
import java.security.Provider;
import javax.net.ssl.TrustManager;
import org.springframework.beans.factory.SmartFactoryBean;

public class HubTrustManagersFactoryBean implements SmartFactoryBean<TrustManager[]> {
    private String type;
    private Provider prov;
    private KeyStore keyStore;

    public HubTrustManagersFactoryBean(String type, Provider prov, KeyStore keyStore) {
        this.type = type;
        this.prov = prov;
        this.keyStore = keyStore;
    }

    @Override
    public TrustManager[] getObject() throws Exception {
        return HubTrustManagerUtils.buildTrustManagers(this.type, this.prov, this.keyStore);
    }

    @Override
    public boolean isEagerInit() {
        return false;
    }

    @Override
    public Class<?> getObjectType() {
        return TrustManager[].class;
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
