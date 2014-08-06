package gov.hhs.onc.iishubpilot.data;

import java.io.Serializable;

public interface HubDao<T extends Serializable, U extends HubEntity<T>> extends HubDataAccessor<T, U> {
}
