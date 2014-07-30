package gov.hhs.onc.iishubpilot.ws.hub;

import gov.hhs.onc.iishubpilot.destination.HubDestinationRegistry;
import gov.hhs.onc.iishubpilot.ws.IisService;
import gov.hhs.onc.iishubpilot.ws.MessageTooLargeFault;
import gov.hhs.onc.iishubpilot.ws.SecurityFault;
import gov.hhs.onc.iishubpilot.ws.SubmitSingleMessageRequestType;
import gov.hhs.onc.iishubpilot.ws.SubmitSingleMessageResponseType;
import gov.hhs.onc.iishubpilot.ws.hub.impl.ObjectFactory;
import javax.xml.ws.Holder;

public interface IisHubService extends IisService {
    public void submitSingleMessage(SubmitSingleMessageRequestType reqParams, HubRequestHeaderType hubReqHeader,
        Holder<SubmitSingleMessageResponseType> respParams, Holder<HubResponseHeaderType> hubRespHeader) throws DestinationConnectionFault, HubClientFault,
        MessageTooLargeFault, SecurityFault, UnknownDestinationFault;

    public HubDestinationRegistry getDestinationRegistry();

    public void setDestinationRegistry(HubDestinationRegistry destReg);

    public ObjectFactory getHubObjectFactory();

    public void setHubObjectFactory(ObjectFactory hubObjFactory);
}
