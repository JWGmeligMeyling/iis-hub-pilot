package gov.hhs.onc.iishubpilot.interceptor.fault.impl;

import gov.hhs.onc.iishubpilot.ws.HubMessageContextProperties;
import gov.hhs.onc.iishubpilot.interceptor.Intercept;
import gov.hhs.onc.iishubpilot.interceptor.utils.HubInterceptorUtils;
import gov.hhs.onc.iishubpilot.xml.HubXmlQnames;
import gov.hhs.onc.iishubpilot.xml.utils.HubXmlQnameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.binding.soap.interceptor.Soap12FaultOutInterceptor;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.MessageUtils;
import org.apache.cxf.phase.Phase;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

@Component("interceptorFaultStacktrace")
@Intercept(phase = Phase.PREPARE_SEND, beforeClasses = { Soap12FaultOutInterceptor.class })
public class StacktraceFaultOutInterceptor extends AbstractSoapInterceptor {
    public StacktraceFaultOutInterceptor() {
        super(HubInterceptorUtils.getPhase(StacktraceFaultOutInterceptor.class));
    }

    @Override
    public void handleMessage(SoapMessage msg) throws Fault {
        // noinspection ThrowableResultOfMethodCallIgnored
        Fault fault = ((Fault) msg.getContent(Exception.class));
        Throwable faultCause;

        if (!MessageUtils.getContextualBoolean(msg, HubMessageContextProperties.FAULT_STACKTRACE_ENABLED, false) || ((faultCause = fault.getCause()) == null)) {
            return;
        }

        Element faultDetailElem = fault.getOrCreateDetail();
        Document faultDetailDoc = faultDetailElem.getOwnerDocument();

        Element faultDetailStacktraceElem =
            faultDetailDoc.createElementNS(HubXmlQnames.CXF_FAULT_STACKTRACE.getNamespaceURI(),
                HubXmlQnameUtils.toReferenceString(HubXmlQnames.CXF_FAULT_STACKTRACE));
        faultDetailStacktraceElem.setTextContent(StringUtils.LF + StringUtils.join(ExceptionUtils.getRootCauseStackTrace(faultCause), StringUtils.LF)
            + StringUtils.LF);
        faultDetailElem.appendChild(faultDetailStacktraceElem);
    }
}
