package gov.hhs.onc.iishubpilot.destination.impl;

import gov.hhs.onc.iishubpilot.data.impl.AbstractHubDao;
import gov.hhs.onc.iishubpilot.destination.HubDestination;
import gov.hhs.onc.iishubpilot.destination.HubDestinationRegistryDao;
import org.springframework.stereotype.Repository;

@Repository("destRegDao")
public class HubDestinationRegistryDaoImpl extends AbstractHubDao<String, HubDestination> implements HubDestinationRegistryDao {
    public HubDestinationRegistryDaoImpl() {
        super(String.class, HubDestination.class, HubDestinationImpl.class);
    }
}
