package gov.hhs.onc.iishubpilot.interceptor.impl;

import gov.hhs.onc.iishubpilot.interceptor.Intercept;
import java.util.Arrays;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.PhaseInterceptor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

@Component("beanPostProcInterceptorOrder")
public class InterceptorOrderBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (!ClassUtils.isAssignable(bean.getClass(), AbstractPhaseInterceptor.class)) {
            return bean;
        }

        Intercept interceptAnno = AnnotationUtils.findAnnotation(bean.getClass(), Intercept.class);

        if (interceptAnno != null) {
            AbstractPhaseInterceptor<? extends Message> interceptor = ((AbstractPhaseInterceptor<? extends Message>) bean);
            Class<? extends PhaseInterceptor<? extends Message>>[] interceptorClasses;

            for (String beforeInterceptorId : ArrayUtils.addAll(
                interceptAnno.before(),
                ClassUtils.convertClassesToClassNames(Arrays.asList(((Class<?>[]) (interceptorClasses = interceptAnno.beforeClasses())))).toArray(
                    new String[interceptorClasses.length]))) {
                interceptor.addBefore(beforeInterceptorId);
            }

            for (String afterInterceptorId : ArrayUtils.addAll(
                interceptAnno.after(),
                ClassUtils.convertClassesToClassNames(Arrays.asList(((Class<?>[]) (interceptorClasses = interceptAnno.afterClasses())))).toArray(
                    new String[interceptorClasses.length]))) {
                interceptor.addAfter(afterInterceptorId);
            }
        }

        return bean;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
