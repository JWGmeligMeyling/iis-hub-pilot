package gov.hhs.onc.iishubpilot.ws.impl;

import gov.hhs.onc.iishubpilot.ws.ConnectivityTestRequestType;
import gov.hhs.onc.iishubpilot.ws.ConnectivityTestResponseType;
import gov.hhs.onc.iishubpilot.ws.HubWsNames;
import gov.hhs.onc.iishubpilot.ws.IisPortType;
import gov.hhs.onc.iishubpilot.ws.MessageTooLargeFault;
import gov.hhs.onc.iishubpilot.ws.SecurityFault;
import gov.hhs.onc.iishubpilot.ws.SubmitSingleMessageRequestType;
import gov.hhs.onc.iishubpilot.ws.SubmitSingleMessageResponseType;
import gov.hhs.onc.iishubpilot.ws.TestHubOperationCallback;
import gov.hhs.onc.iishubpilot.ws.TestIisService;
import gov.hhs.onc.iishubpilot.xml.HubXmlNs;
import java.util.List;
import javax.annotation.Nullable;
import javax.jws.WebService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.PredicateUtils;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.lang3.ClassUtils;

@WebService(portName = HubWsNames.PORT, serviceName = HubWsNames.SERVICE, targetNamespace = HubXmlNs.IIS)
public class TestIisServiceImpl extends AbstractIisService implements IisPortType, TestIisService {
    private static class AssignableTestHubOperationCallbackTransformer<T, U> implements
        Transformer<TestHubOperationCallback<?, ?>, TestHubOperationCallback<T, U>> {
        public final static AssignableTestHubOperationCallbackTransformer<ConnectivityTestRequestType, ConnectivityTestResponseType> CONN_TEST_INSTANCE =
            new AssignableTestHubOperationCallbackTransformer<>(ConnectivityTestRequestType.class, ConnectivityTestResponseType.class);
        public final static AssignableTestHubOperationCallbackTransformer<SubmitSingleMessageRequestType, SubmitSingleMessageResponseType> SUBMIT_SINGLE_MSG_INSTANCE =
            new AssignableTestHubOperationCallbackTransformer<>(SubmitSingleMessageRequestType.class, SubmitSingleMessageResponseType.class);

        private Class<T> reqParamsClass;
        private Class<U> respParamsClass;

        public AssignableTestHubOperationCallbackTransformer(Class<T> reqParamsClass, Class<U> respParamsClass) {
            this.reqParamsClass = reqParamsClass;
            this.respParamsClass = respParamsClass;
        }

        @Nullable
        @Override
        @SuppressWarnings({ "unchecked" })
        public TestHubOperationCallback<T, U> transform(TestHubOperationCallback<?, ?> callback) {
            return ((ClassUtils.isAssignable(callback.getRequestParametersClass(), this.reqParamsClass) && ClassUtils.isAssignable(
                callback.getResponseParametersClass(), this.respParamsClass)) ? ((TestHubOperationCallback<T, U>) callback) : null);
        }
    }

    private List<TestHubOperationCallback<?, ?>> callbacks;

    public TestIisServiceImpl(List<TestHubOperationCallback<?, ?>> callbacks) {
        this.callbacks = callbacks;
    }

    @Override
    public SubmitSingleMessageResponseType submitSingleMessage(SubmitSingleMessageRequestType reqParams) throws MessageTooLargeFault, SecurityFault {
        return this.submitSingleMessageInternal(reqParams);
    }

    private SubmitSingleMessageResponseType submitSingleMessageInternal(SubmitSingleMessageRequestType reqParams) {
        SubmitSingleMessageResponseType respParams = this.objFactoryIis.createSubmitSingleMessageResponseType();
        respParams.setHl7Message(reqParams.getHl7Message());

        for (TestHubOperationCallback<SubmitSingleMessageRequestType, SubmitSingleMessageResponseType> callback : CollectionUtils.select(
            CollectionUtils.collect(this.callbacks, AssignableTestHubOperationCallbackTransformer.SUBMIT_SINGLE_MSG_INSTANCE),
            PredicateUtils.notNullPredicate())) {
            respParams = callback.invoke(this.getMessageContext(), this.getHttpHeaders(), this.getMessages(), reqParams, respParams);
        }

        return respParams;
    }

    @Override
    public List<TestHubOperationCallback<?, ?>> getCallbacks() {
        return this.callbacks;
    }
}
