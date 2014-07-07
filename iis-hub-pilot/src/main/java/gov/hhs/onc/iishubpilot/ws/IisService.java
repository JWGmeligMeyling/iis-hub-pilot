package gov.hhs.onc.iishubpilot.ws;

public interface IisService {
    public ConnectivityTestResponseType connectivityTest(ConnectivityTestRequestType requestParameters) throws UnsupportedOperationFault;
}
