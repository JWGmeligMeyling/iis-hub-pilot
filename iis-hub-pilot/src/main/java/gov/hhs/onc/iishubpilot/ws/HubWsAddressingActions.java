package gov.hhs.onc.iishubpilot.ws;

import gov.hhs.onc.iishubpilot.xml.HubXmlNs;
import gov.hhs.onc.iishubpilot.xml.utils.HubXmlQnameUtils;

public final class HubWsAddressingActions {
    private final static String IIS_PREFIX = HubXmlNs.IIS + HubXmlQnameUtils.REF_DELIM;
    private final static String IIS_HUB_PREFIX = HubXmlNs.IIS_HUB + HubXmlQnameUtils.REF_DELIM;

    private final static String SUBMIT_SINGLE_MSG_PREFIX = IIS_PREFIX + HubWsNames.PORT_TYPE + HubXmlQnameUtils.REF_DELIM;
    private final static String SUBMIT_SINGLE_MSG_HUB_PREFIX = IIS_HUB_PREFIX + HubWsNames.PORT_TYPE_HUB + HubXmlQnameUtils.REF_DELIM;

    public final static String SUBMIT_SINGLE_MSG_REQ = SUBMIT_SINGLE_MSG_PREFIX + HubWsNames.SUBMIT_SINGLE_MSG_REQ;
    public final static String SUBMIT_SINGLE_MSG_REQ_HUB = SUBMIT_SINGLE_MSG_HUB_PREFIX + HubWsNames.SUBMIT_SINGLE_MSG_REQ;
    public final static String SUBMIT_SINGLE_MSG_RESP = SUBMIT_SINGLE_MSG_PREFIX + HubWsNames.SUBMIT_SINGLE_MSG_RESP;
    public final static String SUBMIT_SINGLE_MSG_RESP_HUB = SUBMIT_SINGLE_MSG_HUB_PREFIX + HubWsNames.SUBMIT_SINGLE_MSG_RESP;

    private HubWsAddressingActions() {
    }
}
