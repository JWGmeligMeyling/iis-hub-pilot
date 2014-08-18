package gov.hhs.onc.iishubpilot.net.impl;

import java.net.URI;
import org.apache.commons.collections4.map.MultiValueMap;
import org.springframework.beans.factory.SmartFactoryBean;
import org.springframework.web.util.UriComponentsBuilder;

public class UriFactoryBean implements SmartFactoryBean<URI> {
    private UriComponentsBuilder compsBuilder = UriComponentsBuilder.newInstance();

    @Override
    public URI getObject() throws Exception {
        return compsBuilder.build().toUri();
    }

    @Override
    public boolean isEagerInit() {
        return false;
    }

    public void setHost(String host) {
        this.compsBuilder.host(host);
    }

    @Override
    public Class<?> getObjectType() {
        return URI.class;
    }

    public void setQueryParams(MultiValueMap<String, String> queryParams) {
        queryParams.keySet().stream()
            .forEachOrdered(((String queryParamName) -> this.compsBuilder.queryParam(queryParamName, queryParams.getCollection(queryParamName).toArray())));
    }

    public void setPath(String path) {
        this.compsBuilder.path(path);
    }

    public void setPort(int port) {
        if (port >= 0) {
            this.compsBuilder.port(port);
        }
    }

    @Override
    public boolean isPrototype() {
        return false;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public void setScheme(String scheme) {
        this.compsBuilder.scheme(scheme);
    }
}
