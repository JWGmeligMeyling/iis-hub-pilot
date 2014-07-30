package gov.hhs.onc.iishubpilot.destination;

import java.net.URI;

public interface HubDestination {
    public String getId();

    public void setId(String id);

    public URI getUri();

    public void setUri(URI uri);
}
