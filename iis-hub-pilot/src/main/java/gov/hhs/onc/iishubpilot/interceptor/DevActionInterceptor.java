package gov.hhs.onc.iishubpilot.interceptor;

import java.util.Collection;
import java.util.Set;
import org.apache.cxf.message.Message;

public interface DevActionInterceptor extends HubOperationInterceptor<Message> {
    public Set<String> getActionValues();

    public void setActionValues(Collection<String> actionValues);
}
