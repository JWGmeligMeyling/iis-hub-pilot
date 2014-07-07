package gov.hhs.onc.iishubpilot.ws.hub;

import gov.hhs.onc.iishubpilot.ws.IisService;
import gov.hhs.onc.iishubpilot.ws.MessageTooLargeFault;
import gov.hhs.onc.iishubpilot.ws.SecurityFault;
import gov.hhs.onc.iishubpilot.ws.SubmitSingleMessageRequestType;
import gov.hhs.onc.iishubpilot.ws.SubmitSingleMessageResponseType;

public interface IisHubService extends IisService {
    public SubmitSingleMessageResponseType submitSingleMessage(SubmitSingleMessageRequestType parameters) throws DestinationConnectionFault, HubClientFault,
        MessageTooLargeFault, SecurityFault, UnknownDestinationFault;
}
