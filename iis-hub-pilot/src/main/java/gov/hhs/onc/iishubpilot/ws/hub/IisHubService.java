package gov.hhs.onc.iishubpilot.ws.hub;

import gov.hhs.onc.iishubpilot.ws.IisService;
import gov.hhs.onc.iishubpilot.ws.MessageTooLargeFault;
import gov.hhs.onc.iishubpilot.ws.SecurityFault;
import gov.hhs.onc.iishubpilot.ws.SubmitSingleMessageRequestType;
import gov.hhs.onc.iishubpilot.ws.SubmitSingleMessageResponseType;
import javax.xml.ws.Holder;

public interface IisHubService extends IisService {
    public void submitSingleMessage(SubmitSingleMessageRequestType requestParameters, HubRequestHeaderType hubRequestHeader,
        Holder<SubmitSingleMessageResponseType> responseParameters, Holder<HubResponseHeaderType> hubResponseHeader) throws DestinationConnectionFault,
        HubClientFault, MessageTooLargeFault, SecurityFault, UnknownDestinationFault;
}
