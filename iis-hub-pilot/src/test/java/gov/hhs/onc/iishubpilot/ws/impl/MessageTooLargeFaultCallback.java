package gov.hhs.onc.iishubpilot.ws.impl;

import gov.hhs.onc.iishubpilot.ws.MessageTooLargeFault;
import gov.hhs.onc.iishubpilot.ws.MessageTooLargeFaultType;
import gov.hhs.onc.iishubpilot.ws.SubmitSingleMessageRequestType;
import gov.hhs.onc.iishubpilot.ws.SubmitSingleMessageResponseType;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.helpers.HttpHeaderHelper;
import org.apache.cxf.jaxws.context.WrappedMessageContext;

public class MessageTooLargeFaultCallback extends
    AbstractTestHubOperationFaultCallback<SubmitSingleMessageRequestType, SubmitSingleMessageResponseType, MessageTooLargeFaultType, MessageTooLargeFault> {
    private BigInteger maxSize;

    public MessageTooLargeFaultCallback(BigInteger maxSize) {
        super(SubmitSingleMessageRequestType.class, SubmitSingleMessageResponseType.class, MessageTooLargeFaultType.class, MessageTooLargeFault.class);

        this.maxSize = maxSize;
    }

    @Override
    protected MessageTooLargeFault createFault(WrappedMessageContext msgContext, Pair<Map<String, List<String>>, Map<String, List<String>>> httpHeaders,
        Pair<SoapMessage, SoapMessage> msgs, SubmitSingleMessageRequestType reqParams, SubmitSingleMessageResponseType respParams) {
        return new MessageTooLargeFault(StringUtils.EMPTY, new MessageTooLargeFaultTypeImpl(new BigInteger(HttpHeaderHelper.getHeader(httpHeaders.getLeft(),
            HttpHeaderHelper.CONTENT_LENGTH).get(0)), this.maxSize));
    }
}
