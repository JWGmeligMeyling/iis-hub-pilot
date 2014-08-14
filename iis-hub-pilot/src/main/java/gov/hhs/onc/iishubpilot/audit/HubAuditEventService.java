package gov.hhs.onc.iishubpilot.audit;

import gov.hhs.onc.iishubpilot.data.HubDataService;
import java.math.BigInteger;

public interface HubAuditEventService<T extends HubAuditEvent, U extends HubAuditEventDao<T>> extends HubDataService<BigInteger, T, U> {
}
