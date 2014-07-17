package gov.hhs.onc.iishubpilot.ws;


import java.util.List;

public interface TestIisService extends IisService {
    public SubmitSingleMessageResponseType submitSingleMessage(SubmitSingleMessageRequestType reqParams) throws MessageTooLargeFault, SecurityFault;

    public List<TestHubOperationCallback<?, ?>> getCallbacks();
}
