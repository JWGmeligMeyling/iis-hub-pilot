package gov.hhs.onc.iishubpilot.interceptor;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.PhaseInterceptor;

@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface Intercept {
    String phase();

    String[] before() default {};

    Class<? extends PhaseInterceptor<? extends Message>>[] beforeClasses() default {};

    String[] after() default {};

    Class<? extends PhaseInterceptor<? extends Message>>[] afterClasses() default {};
}
