package gov.hhs.onc.iishubpilot.audit;

import gov.hhs.onc.iishubpilot.data.HubDao;
import java.math.BigInteger;

public interface HubAuditDao<T extends HubAuditEvent> extends HubDao<BigInteger, T> {
}
