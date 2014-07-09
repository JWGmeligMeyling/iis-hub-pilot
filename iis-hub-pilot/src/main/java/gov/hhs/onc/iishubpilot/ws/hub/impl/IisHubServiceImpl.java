package gov.hhs.onc.iishubpilot.ws.hub.impl;

import gov.hhs.onc.iishubpilot.ws.MessageTooLargeFault;
import gov.hhs.onc.iishubpilot.ws.SecurityFault;
import gov.hhs.onc.iishubpilot.ws.SubmitSingleMessageRequestType;
import gov.hhs.onc.iishubpilot.ws.SubmitSingleMessageResponseType;
import gov.hhs.onc.iishubpilot.ws.hub.DestinationConnectionFault;
import gov.hhs.onc.iishubpilot.ws.hub.HubClientFault;
import gov.hhs.onc.iishubpilot.ws.hub.HubRequestHeaderType;
import gov.hhs.onc.iishubpilot.ws.hub.HubResponseHeaderType;
import gov.hhs.onc.iishubpilot.ws.hub.IisHubPortType;
import gov.hhs.onc.iishubpilot.ws.hub.IisHubService;
import gov.hhs.onc.iishubpilot.ws.hub.UnknownDestinationFault;
import gov.hhs.onc.iishubpilot.ws.impl.AbstractIisService;
import gov.hhs.onc.iishubpilot.ws.impl.SubmitSingleMessageResponseTypeImpl;
import gov.hhs.onc.iishubpilot.xml.HubXmlNs;
import javax.jws.WebService;
import javax.xml.ws.Holder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("iisServiceHub")
@WebService(portName = "IISHubPortSoap12", serviceName = "IISHubService", targetNamespace = HubXmlNs.IIS_HUB)
public class IisHubServiceImpl extends AbstractIisService implements IisHubService, IisHubPortType {
    @Autowired
    protected ObjectFactory objFactoryHub;

    @Override
    public void submitSingleMessage(SubmitSingleMessageRequestType requestParameters, HubRequestHeaderType hubRequestHeader,
        Holder<SubmitSingleMessageResponseType> responseParameters, Holder<HubResponseHeaderType> hubResponseHeader) throws DestinationConnectionFault,
        HubClientFault, MessageTooLargeFault, SecurityFault, UnknownDestinationFault {
        responseParameters.value = new SubmitSingleMessageResponseTypeImpl(requestParameters.getHl7Message());
        hubResponseHeader.value = new HubResponseHeaderTypeImpl(hubRequestHeader.getDestinationId(), "https://test.iis-hub-pilot.org/IISService");
    }
}
