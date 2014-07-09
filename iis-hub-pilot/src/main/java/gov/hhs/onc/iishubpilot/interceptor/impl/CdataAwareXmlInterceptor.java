package gov.hhs.onc.iishubpilot.interceptor.impl;

import gov.hhs.onc.iishubpilot.interceptor.Intercept;
import gov.hhs.onc.iishubpilot.interceptor.utils.HubInterceptorUtils;
import java.io.OutputStream;
import java.util.Set;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import org.apache.cxf.binding.soap.interceptor.Soap12FaultOutInterceptor;
import org.apache.cxf.interceptor.AbstractOutDatabindingInterceptor;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.staxutils.DelegatingXMLStreamWriter;
import org.apache.cxf.staxutils.StaxUtils;

@Intercept(phase = Phase.PREPARE_SEND, afterClasses = { Soap12FaultOutInterceptor.class })
public class CdataAwareXmlInterceptor extends AbstractPhaseInterceptor<Message> {
    private class CdataAwareXmlStreamWriter extends DelegatingXMLStreamWriter {
        private QName elemQname;

        public CdataAwareXmlStreamWriter(XMLStreamWriter xmlStreamWriter) {
            super(xmlStreamWriter);
        }

        @Override
        public void writeCharacters(String str) throws XMLStreamException {
            if (CdataAwareXmlInterceptor.this.cdataElemQnames.contains(this.elemQname)) {
                this.writeCData(str);
            } else {
                super.writeCharacters(str);
            }
        }

        @Override
        public void writeStartElement(String elemPrefix, String elemLocalName, String elemNsUri) throws XMLStreamException {
            this.elemQname = new QName(elemNsUri, elemLocalName, elemPrefix);

            super.writeStartElement(elemPrefix, elemLocalName, elemNsUri);
        }
    }

    private Set<QName> cdataElemQnames;

    public CdataAwareXmlInterceptor(Set<QName> cdataElemQnames) {
        super(HubInterceptorUtils.getPhase(CdataAwareXmlInterceptor.class));

        this.cdataElemQnames = cdataElemQnames;
    }

    @Override
    public void handleMessage(Message msg) throws Fault {
        msg.put(AbstractOutDatabindingInterceptor.DISABLE_OUTPUTSTREAM_OPTIMIZATION, true);

        msg.setContent(XMLStreamWriter.class, new CdataAwareXmlStreamWriter(StaxUtils.createXMLStreamWriter(msg.getContent(OutputStream.class))));
    }
}
