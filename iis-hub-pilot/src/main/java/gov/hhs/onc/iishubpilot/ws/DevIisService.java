package gov.hhs.onc.iishubpilot.ws;

public interface DevIisService extends IisService {
    public SubmitSingleMessageResponseType submitSingleMessage(SubmitSingleMessageRequestType reqParams) throws MessageTooLargeFault, SecurityFault;
}
