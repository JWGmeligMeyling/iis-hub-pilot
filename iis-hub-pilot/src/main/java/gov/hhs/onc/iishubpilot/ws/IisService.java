package gov.hhs.onc.iishubpilot.ws;

import gov.hhs.onc.iishubpilot.ws.impl.ObjectFactory;
import org.springframework.context.ApplicationContextAware;

public interface IisService extends ApplicationContextAware {
    public ConnectivityTestResponseType connectivityTest(ConnectivityTestRequestType reqParams) throws UnsupportedOperationFault;

    public ObjectFactory getObjectFactory();

    public void setObjectFactory(ObjectFactory objFactory);
}
