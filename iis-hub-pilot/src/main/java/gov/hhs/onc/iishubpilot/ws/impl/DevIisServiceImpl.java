package gov.hhs.onc.iishubpilot.ws.impl;

import gov.hhs.onc.iishubpilot.ws.DevIisService;
import gov.hhs.onc.iishubpilot.ws.HubWsNames;
import gov.hhs.onc.iishubpilot.ws.IisPortType;
import gov.hhs.onc.iishubpilot.ws.MessageTooLargeFault;
import gov.hhs.onc.iishubpilot.ws.SecurityFault;
import gov.hhs.onc.iishubpilot.ws.SubmitSingleMessageRequestType;
import gov.hhs.onc.iishubpilot.ws.SubmitSingleMessageResponseType;
import gov.hhs.onc.iishubpilot.xml.HubXmlNs;
import javax.jws.WebService;

@WebService(portName = HubWsNames.PORT, serviceName = HubWsNames.SERVICE, targetNamespace = HubXmlNs.IIS)
public class DevIisServiceImpl extends AbstractIisService implements DevIisService, IisPortType {
    @Override
    public SubmitSingleMessageResponseType submitSingleMessage(SubmitSingleMessageRequestType reqParams) throws MessageTooLargeFault, SecurityFault {
        return new SubmitSingleMessageResponseTypeImpl(reqParams.getHl7Message());
    }
}
