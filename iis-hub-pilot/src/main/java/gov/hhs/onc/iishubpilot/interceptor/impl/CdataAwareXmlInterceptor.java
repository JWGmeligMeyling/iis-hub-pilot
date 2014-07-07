package gov.hhs.onc.iishubpilot.interceptor.impl;

import java.io.OutputStream;
import java.util.Set;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import org.apache.cxf.interceptor.AbstractOutDatabindingInterceptor;
import org.apache.cxf.interceptor.AttachmentOutInterceptor;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.staxutils.DelegatingXMLStreamWriter;
import org.apache.cxf.staxutils.StaxUtils;

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
        super(Phase.PRE_STREAM);

        this.cdataElemQnames = cdataElemQnames;

        this.addAfter(AttachmentOutInterceptor.class.getName());
    }

    @Override
    public void handleMessage(Message msg) throws Fault {
        msg.put(AbstractOutDatabindingInterceptor.DISABLE_OUTPUTSTREAM_OPTIMIZATION, true);

        msg.setContent(XMLStreamWriter.class, new CdataAwareXmlStreamWriter(StaxUtils.createXMLStreamWriter(msg.getContent(OutputStream.class))));
    }
}
