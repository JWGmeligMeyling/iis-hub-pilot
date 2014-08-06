package gov.hhs.onc.iishubpilot.destination.impl;

import gov.hhs.onc.iishubpilot.data.impl.AbstractHubDataService;
import gov.hhs.onc.iishubpilot.destination.HubDestination;
import gov.hhs.onc.iishubpilot.destination.HubDestinationRegistry;
import gov.hhs.onc.iishubpilot.destination.HubDestinationRegistryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("destReg")
public class HubDestinationRegistryImpl extends AbstractHubDataService<String, HubDestination, HubDestinationRegistryDao> implements HubDestinationRegistry {
    @Autowired
    public HubDestinationRegistryImpl(HubDestinationRegistryDao dao) {
        super(String.class, HubDestination.class, dao);
    }
}
