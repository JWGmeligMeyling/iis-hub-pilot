package gov.hhs.onc.iishubpilot.jaxws.impl;

import java.net.URL;
import javax.annotation.Nullable;
import org.apache.cxf.jaxws.spring.JaxWsProxyFactoryBeanDefinitionParser.JAXWSSpringClientProxyFactoryBean;
import org.springframework.beans.factory.SmartFactoryBean;

public class HubJaxWsClientProxyFactoryBean extends JAXWSSpringClientProxyFactoryBean implements SmartFactoryBean<Object> {
    public HubJaxWsClientProxyFactoryBean() {
        this(null);
    }
    
    public HubJaxWsClientProxyFactoryBean(@Nullable URL addr) {
        super();

        if (addr != null) {
            this.setAddress(addr.toExternalForm());
        }
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
