package gov.hhs.onc.iishubpilot.interceptor;

import java.util.Collection;
import java.util.Set;
import javax.xml.namespace.QName;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.PhaseInterceptor;

public interface HubOperationInterceptor<T extends Message> extends PhaseInterceptor<T> {
    public Set<QName> getOperationQnames();

    public void setOperationQnames(Collection<QName> opQnames);
}
