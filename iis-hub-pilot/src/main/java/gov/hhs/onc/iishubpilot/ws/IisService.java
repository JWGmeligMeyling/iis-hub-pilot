package gov.hhs.onc.iishubpilot.ws;

import org.springframework.context.ApplicationContextAware;

public interface IisService extends ApplicationContextAware {
    public ConnectivityTestResponseType connectivityTest(ConnectivityTestRequestType reqParams) throws UnsupportedOperationFault;
}
