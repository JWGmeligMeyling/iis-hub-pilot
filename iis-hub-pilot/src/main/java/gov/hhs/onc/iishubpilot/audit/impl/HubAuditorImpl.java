package gov.hhs.onc.iishubpilot.audit.impl;

import gov.hhs.onc.iishubpilot.audit.HubAuditor;
import gov.hhs.onc.iishubpilot.ws.ConnectivityTestRequestType;
import gov.hhs.onc.iishubpilot.ws.ConnectivityTestResponseType;
import gov.hhs.onc.iishubpilot.ws.SubmitSingleMessageRequestType;
import gov.hhs.onc.iishubpilot.ws.SubmitSingleMessageResponseType;
import gov.hhs.onc.iishubpilot.ws.hub.HubRequestHeaderType;
import gov.hhs.onc.iishubpilot.ws.hub.HubResponseHeaderType;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.cxf.binding.soap.SoapMessage;

public class HubAuditorImpl implements HubAuditor {
    @Override
    public void auditSubmitSingleMessage(Pair<SoapMessage, SoapMessage> msgs, SubmitSingleMessageRequestType reqParams, HubRequestHeaderType hubReqHeader,
        SubmitSingleMessageResponseType respParams, HubResponseHeaderType hubRespHeader) {
        // TODO: implement
    }

    @Override
    public void auditConnectivityTest(Pair<SoapMessage, SoapMessage> msgs, ConnectivityTestRequestType reqParams, ConnectivityTestResponseType respParams) {
        // TODO: implement
    }
}
