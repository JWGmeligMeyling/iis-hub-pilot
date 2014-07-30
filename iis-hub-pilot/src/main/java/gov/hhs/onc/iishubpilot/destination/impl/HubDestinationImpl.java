package gov.hhs.onc.iishubpilot.destination.impl;

import gov.hhs.onc.iishubpilot.destination.HubDestination;
import java.net.URI;

public class HubDestinationImpl implements HubDestination {
    private String id;
    private URI uri;

    public HubDestinationImpl() {
    }

    public HubDestinationImpl(String id, URI uri) {
        this.id = id;
        this.uri = uri;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public URI getUri() {
        return this.uri;
    }

    @Override
    public void setUri(URI uri) {
        this.uri = uri;
    }
}
