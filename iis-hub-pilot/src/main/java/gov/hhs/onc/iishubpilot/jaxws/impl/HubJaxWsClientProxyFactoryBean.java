package gov.hhs.onc.iishubpilot.jaxws.impl;

import org.apache.cxf.jaxws.spring.JaxWsProxyFactoryBeanDefinitionParser.JAXWSSpringClientProxyFactoryBean;
import org.springframework.beans.factory.SmartFactoryBean;

public class HubJaxWsClientProxyFactoryBean extends JAXWSSpringClientProxyFactoryBean implements SmartFactoryBean<Object> {
    public HubJaxWsClientProxyFactoryBean() {
        super();
    }

    @Override
    public boolean isEagerInit() {
        return false;
    }

    @Override
    public boolean isPrototype() {
        return true;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
