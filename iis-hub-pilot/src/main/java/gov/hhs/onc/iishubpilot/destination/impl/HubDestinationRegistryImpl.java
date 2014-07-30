package gov.hhs.onc.iishubpilot.destination.impl;

import gov.hhs.onc.iishubpilot.destination.HubDestination;
import gov.hhs.onc.iishubpilot.destination.HubDestinationRegistry;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class HubDestinationRegistryImpl implements HubDestinationRegistry {
    private Set<HubDestination> dests = new TreeSet<>(Comparator.comparing(HubDestination::getId).thenComparing(HubDestination::getUri));

    @Override
    public Set<HubDestination> getDestinations() {
        return this.dests;
    }

    @Override
    public void setDestinations(Set<HubDestination> dests) {
        this.dests.clear();
        this.dests.addAll(dests);
    }
}
