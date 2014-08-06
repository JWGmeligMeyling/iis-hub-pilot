package gov.hhs.onc.iishubpilot.data;

import java.io.Serializable;

public interface HubDataService<T extends Serializable, U extends HubEntity<T>, V extends HubDao<T, U>> extends HubDataAccessor<T, U> {
}
