package gov.hhs.onc.iishubpilot.ws.impl;

import gov.hhs.onc.iishubpilot.ws.ConnectivityTestRequestType;
import gov.hhs.onc.iishubpilot.ws.ConnectivityTestResponseType;
import gov.hhs.onc.iishubpilot.ws.IisService;
import gov.hhs.onc.iishubpilot.ws.UnsupportedOperationFault;
import javax.annotation.Resource;
import javax.xml.ws.WebServiceContext;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractIisService implements IisService {
    @Autowired
    protected ObjectFactory objFactory;

    @Resource
    @SuppressWarnings({ "SpringJavaAutowiringInspection" })
    protected WebServiceContext wsContext;

    @Override
    public ConnectivityTestResponseType connectivityTest(ConnectivityTestRequestType parameters) throws UnsupportedOperationFault {
        return this.connectivityTestInternal(parameters);
    }

    protected ConnectivityTestResponseType connectivityTestInternal(ConnectivityTestRequestType parameters) {
        return new ConnectivityTestResponseTypeImpl(parameters.getEchoBack());
    }
}
