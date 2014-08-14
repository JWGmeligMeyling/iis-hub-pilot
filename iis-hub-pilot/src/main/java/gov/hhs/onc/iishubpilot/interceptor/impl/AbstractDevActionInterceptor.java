package gov.hhs.onc.iishubpilot.interceptor.impl;

import gov.hhs.onc.iishubpilot.interceptor.DevActionInterceptor;
import gov.hhs.onc.iishubpilot.ws.HubHttpHeaders;
import gov.hhs.onc.iishubpilot.ws.hub.HubRequestHeaderType;
import gov.hhs.onc.iishubpilot.ws.utils.HubWsUtils;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import org.apache.cxf.message.Message;
import org.apache.cxf.transport.http.Headers;
import org.springframework.beans.factory.annotation.Value;

public abstract class AbstractDevActionInterceptor extends AbstractHubOperationInterceptor<Message> implements DevActionInterceptor {
    @Value("${hub.data.dest.iis.dev.id}")
    protected String devDestId;

    @Value("${hub.data.dest.iis.dev.uri}")
    protected String devDestUri;

    protected Set<String> actionValues = new LinkedHashSet<>();

    protected AbstractDevActionInterceptor(String phase) {
        super(phase);
    }

    @Override
    protected void handleMessageInternal(Message msg) throws Exception {
        this.handleActionMessage(msg, ((String) msg.get(HubHttpHeaders.DEV_ACTION_NAME)));
    }

    protected abstract void handleActionMessage(Message msg, String msgActionValue) throws Exception;

    @Override
    protected boolean canHandleMessage(Message msg) {
        HubRequestHeaderType hubReqHeader;
        Map<String, List<String>> msgHttpHeaders;
        List<String> msgActionValues;
        String msgActionValue;

        if (!super.canHandleMessage(msg) || ((hubReqHeader = HubWsUtils.getMessageContentPart(msg, HubRequestHeaderType.class)) == null)
            || !Objects.equals(hubReqHeader.getDestinationId(), this.devDestId)
            || !(msgHttpHeaders = Headers.getSetProtocolHeaders(msg)).containsKey(HubHttpHeaders.DEV_ACTION_NAME)
            || (msgActionValues = msgHttpHeaders.get(HubHttpHeaders.DEV_ACTION_NAME)).isEmpty()
            || !this.actionValues.contains((msgActionValue = msgActionValues.get(0)))) {
            return false;
        } else {
            msg.put(HubHttpHeaders.DEV_ACTION_NAME, msgActionValue);

            return true;
        }
    }

    @Override
    public Set<String> getActionValues() {
        return this.actionValues;
    }

    @Override
    public void setActionValues(Collection<String> actionValues) {
        this.actionValues.clear();
        this.actionValues.addAll(actionValues);
    }
}
