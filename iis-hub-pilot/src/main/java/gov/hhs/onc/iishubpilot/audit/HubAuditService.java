package gov.hhs.onc.iishubpilot.audit;

import gov.hhs.onc.iishubpilot.data.HubDataService;
import java.math.BigInteger;

public interface HubAuditService<T extends HubAuditEvent, U extends HubAuditDao<T>> extends HubDataService<BigInteger, T, U> {
}
