package gov.hhs.onc.iishubpilot.interceptor.utils;

import gov.hhs.onc.iishubpilot.interceptor.Intercept;
import org.apache.commons.lang3.StringUtils;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.PhaseInterceptor;
import org.springframework.core.annotation.AnnotationUtils;

public final class HubInterceptorUtils {
    public static String getPhase(Class<? extends PhaseInterceptor<? extends Message>> interceptorClass) {
        Intercept interceptAnno = AnnotationUtils.findAnnotation(interceptorClass, Intercept.class);

        if (interceptAnno == null) {
            throw new IllegalArgumentException(String.format("Interceptor (class=%s) must be annotated with Intercept annotation (class=%s).",
                interceptorClass.getName(), Intercept.class.getName()));
        }

        String phase = interceptAnno.phase();

        if (StringUtils.isBlank(phase)) {
            throw new IllegalArgumentException(String.format("Interceptor (class=%s) phase must not be blank: %s", interceptorClass.getName(), phase));
        }

        return phase;
    }

    private HubInterceptorUtils() {
    }
}
