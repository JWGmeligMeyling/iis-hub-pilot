package gov.hhs.onc.iishubpilot.ws;

import gov.hhs.onc.iishubpilot.audit.HubAuditor;
import gov.hhs.onc.iishubpilot.ws.impl.ObjectFactory;
import org.springframework.context.ApplicationContextAware;

public interface IisService extends ApplicationContextAware {
    public ConnectivityTestResponseType connectivityTest(ConnectivityTestRequestType reqParams) throws UnsupportedOperationFault;

    public HubAuditor getAuditor();

    public void setAuditor(HubAuditor auditor);

    public ObjectFactory getObjectFactory();

    public void setObjectFactory(ObjectFactory objFactory);
}
