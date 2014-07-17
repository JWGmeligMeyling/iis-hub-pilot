package gov.hhs.onc.iishubpilot.ws;

import java.util.List;
import java.util.Map.Entry;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.lang3.StringUtils;

public final class HubHttpHeaders {
    public static class HubHttpHeaderPredicate implements Predicate<Entry<String, List<String>>> {
        public final static HubHttpHeaderPredicate INSTANCE = new HubHttpHeaderPredicate();

        @Override
        public boolean evaluate(Entry<String, List<String>> headerEntry) {
            return StringUtils.startsWithIgnoreCase(headerEntry.getKey(), HubHttpHeaders.PREFIX);
        }
    }

    final static String PREFIX = "X-IIS-Hub-";
    final static String DEST_PREFIX = PREFIX + "Destination-";

    public final static String DEST_ID = DEST_PREFIX + "Id";

    public final static String DEST_URI = DEST_PREFIX + "URI";

    private HubHttpHeaders() {
    }
}
