package gov.hhs.onc.iishubpilot.ws;

public interface IisService {
    public ConnectivityTestResponseType connectivityTest(ConnectivityTestRequestType parameters) throws UnsupportedOperationFault;
}
