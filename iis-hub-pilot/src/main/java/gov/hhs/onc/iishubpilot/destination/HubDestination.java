package gov.hhs.onc.iishubpilot.destination;

import gov.hhs.onc.iishubpilot.data.HubEntity;
import java.net.URI;

public interface HubDestination extends HubEntity<String> {
    public URI getUri();

    public void setUri(URI uri);
}
