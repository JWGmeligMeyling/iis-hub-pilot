package gov.hhs.onc.iishubpilot.audit;

import gov.hhs.onc.iishubpilot.ws.ConnectivityTestRequestType;
import gov.hhs.onc.iishubpilot.ws.ConnectivityTestResponseType;
import gov.hhs.onc.iishubpilot.ws.SubmitSingleMessageRequestType;
import gov.hhs.onc.iishubpilot.ws.SubmitSingleMessageResponseType;
import gov.hhs.onc.iishubpilot.ws.hub.HubRequestHeaderType;
import gov.hhs.onc.iishubpilot.ws.hub.HubResponseHeaderType;
import javax.xml.ws.WebServiceContext;

public interface HubAuditor {
    public void auditSubmitSingleMessage(WebServiceContext wsContext, SubmitSingleMessageRequestType reqParams, HubRequestHeaderType hubReqHeader,
        SubmitSingleMessageResponseType respParams, HubResponseHeaderType hubRespHeader);

    public void auditConnectivityTest(WebServiceContext wsContext, ConnectivityTestRequestType reqParams, ConnectivityTestResponseType respParams);
}
