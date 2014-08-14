package gov.hhs.onc.iishubpilot.interceptor.impl;

import gov.hhs.onc.iishubpilot.interceptor.HubOperationInterceptor;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.xml.namespace.QName;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.service.model.BindingOperationInfo;

public abstract class AbstractHubOperationInterceptor<T extends Message> extends AbstractPhaseInterceptor<T> implements HubOperationInterceptor<T> {
    protected Set<QName> opQnames = new LinkedHashSet<>();

    protected AbstractHubOperationInterceptor(String phase) {
        super(phase);
    }

    @Override
    public void handleMessage(T msg) throws Fault {
        if (this.canHandleMessage(msg)) {
            try {
                this.handleMessageInternal(msg);
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e) {
                throw new RuntimeException("Unable to handle message.", e);
            }
        }
    }

    protected abstract void handleMessageInternal(T msg) throws Exception;

    protected boolean canHandleMessage(T msg) {
        BindingOperationInfo bindingOpInfo = msg.getExchange().getBindingOperationInfo();

        return ((bindingOpInfo != null) && this.opQnames.contains(bindingOpInfo.getOperationInfo().getName()));
    }

    @Override
    public Set<QName> getOperationQnames() {
        return this.opQnames;
    }

    @Override
    public void setOperationQnames(Collection<QName> opQnames) {
        this.opQnames.clear();
        this.opQnames.addAll(opQnames);
    }
}
