package gov.hhs.onc.iishubpilot.crypto.impl;

import java.security.KeyStore;
import java.security.Provider;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.SmartFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.AbstractApplicationContext;

public abstract class AbstractHubCryptoManagerFactoryBean<T> implements ApplicationContextAware, SmartFactoryBean<T> {
    protected Class<T> mgrClass;
    protected String alg;
    protected Provider prov;
    protected KeyStore keyStore;
    protected AbstractApplicationContext appContext;

    protected AbstractHubCryptoManagerFactoryBean(Class<T> mgrClass, String alg, Provider prov, KeyStore keyStore) {
        this.mgrClass = mgrClass;
        this.alg = alg;
        this.prov = prov;
        this.keyStore = keyStore;
    }

    @Override
    public void setApplicationContext(ApplicationContext appContext) throws BeansException {
        this.appContext = ((AbstractApplicationContext) appContext);
    }

    @Override
    public boolean isEagerInit() {
        return false;
    }

    @Override
    public Class<?> getObjectType() {
        return this.mgrClass;
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
