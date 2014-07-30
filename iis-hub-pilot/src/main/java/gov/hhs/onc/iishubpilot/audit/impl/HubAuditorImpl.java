package gov.hhs.onc.iishubpilot.audit.impl;

import gov.hhs.onc.iishubpilot.audit.HubAuditor;
import gov.hhs.onc.iishubpilot.ws.ConnectivityTestRequestType;
import gov.hhs.onc.iishubpilot.ws.ConnectivityTestResponseType;
import gov.hhs.onc.iishubpilot.ws.SubmitSingleMessageRequestType;
import gov.hhs.onc.iishubpilot.ws.SubmitSingleMessageResponseType;
import gov.hhs.onc.iishubpilot.ws.hub.HubRequestHeaderType;
import gov.hhs.onc.iishubpilot.ws.hub.HubResponseHeaderType;
import javax.xml.ws.WebServiceContext;

public class HubAuditorImpl implements HubAuditor {
    @Override
    public void auditSubmitSingleMessage(WebServiceContext wsContext, SubmitSingleMessageRequestType reqParams, HubRequestHeaderType hubReqHeader,
        SubmitSingleMessageResponseType respParams, HubResponseHeaderType hubRespHeader) {
        // TODO: implement
    }

    @Override
    public void auditConnectivityTest(WebServiceContext wsContext, ConnectivityTestRequestType reqParams, ConnectivityTestResponseType respParams) {
        // TODO: implement
    }
}
