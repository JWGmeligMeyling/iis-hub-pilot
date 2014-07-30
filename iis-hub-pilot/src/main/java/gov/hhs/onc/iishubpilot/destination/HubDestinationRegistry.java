package gov.hhs.onc.iishubpilot.destination;

import java.util.Set;

public interface HubDestinationRegistry {
    public Set<HubDestination> getDestinations();

    public void setDestinations(Set<HubDestination> dests);
}
