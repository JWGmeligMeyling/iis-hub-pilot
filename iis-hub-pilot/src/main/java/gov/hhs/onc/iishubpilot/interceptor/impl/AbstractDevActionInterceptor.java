package gov.hhs.onc.iishubpilot.interceptor.impl;

import gov.hhs.onc.iishubpilot.ws.HubHttpHeaders;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.xml.namespace.QName;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.service.model.BindingOperationInfo;
import org.apache.cxf.transport.http.Headers;
import org.springframework.beans.factory.annotation.Value;

public abstract class AbstractDevActionInterceptor extends AbstractPhaseInterceptor<Message> {
    @Value("${hub.data.dest.iis.dev.id}")
    protected String devDestId;

    @Value("${hub.data.dest.iis.dev.uri}")
    protected String devDestUri;

    protected QName opQname;
    protected Set<String> actionValues = new LinkedHashSet<>();

    protected AbstractDevActionInterceptor(String phase, QName opQname, String ... actionValues) {
        this(phase, opQname, Arrays.asList(actionValues));
    }

    protected AbstractDevActionInterceptor(String phase, QName opQname, Collection<String> actionValues) {
        super(phase);

        this.opQname = opQname;
        this.actionValues.addAll(actionValues);
    }

    @Override
    public void handleMessage(Message msg) throws Fault {
        BindingOperationInfo bindingOpInfo = msg.getExchange().getBindingOperationInfo();

        if ((bindingOpInfo == null) || !bindingOpInfo.getOperationInfo().getName().equals(this.opQname)) {
            return;
        }

        Map<String, List<String>> msgHttpHeaders = Headers.getSetProtocolHeaders(msg);
        List<String> msgActionValues;
        String msgActionValue;

        if (!msgHttpHeaders.containsKey(HubHttpHeaders.DEV_ACTION_NAME) || (msgActionValues = msgHttpHeaders.get(HubHttpHeaders.DEV_ACTION_NAME)).isEmpty()
            || !this.actionValues.contains((msgActionValue = msgActionValues.get(0)))) {
            return;
        }

        try {
            this.handleAction(msg, msgHttpHeaders, msgActionValue);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Unable to handle message action.", e);
        }
    }

    protected abstract void handleAction(Message msg, Map<String, List<String>> msgHttpHeaders, String msgActionValue) throws Exception;
}
