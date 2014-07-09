package gov.hhs.onc.iishubpilot.servlet.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import org.apache.commons.lang3.ClassUtils;
import org.apache.cxf.Bus;
import org.apache.cxf.transport.servlet.CXFNonSpringServlet;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationContextException;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.util.StringUtils;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.support.XmlWebApplicationContext;

public class HubCxfServlet extends CXFNonSpringServlet implements ApplicationListener<ContextRefreshedEvent> {
    public final static String INIT_PARAM_VALUE_DELIMS = ",; \t\n";

    private final static long serialVersionUID = 0L;

    private XmlWebApplicationContext appContext;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        this.destroy();
        this.setBus(null);

        try {
            this.init(this.getServletConfig());
        } catch (ServletException e) {
            throw new ApplicationContextException(String.format("Unable to re-initialize Hub CXF Spring application context."), e);
        }
    }

    @Override
    public void destroyBus() {
        if (this.appContext != null) {
            this.appContext.close();
        }
    }

    @Override
    @SuppressWarnings({ "unchecked" })
    protected void loadBus(ServletConfig servletConfig) {
        WebApplicationContext parentAppContext = WebApplicationContextUtils.getWebApplicationContext(servletConfig.getServletContext());

        this.appContext = new XmlWebApplicationContext();
        this.appContext.setParent(parentAppContext);
        this.appContext.setServletConfig(servletConfig);
        this.appContext.setConfigLocations(StringUtils.tokenizeToStringArray(servletConfig.getInitParameter(ContextLoader.CONFIG_LOCATION_PARAM),
            INIT_PARAM_VALUE_DELIMS));

        List<Class<?>> appContextInitClasses =
            ClassUtils.convertClassNamesToClasses(Arrays.asList(StringUtils.tokenizeToStringArray(
                servletConfig.getInitParameter(ContextLoader.CONTEXT_INITIALIZER_CLASSES_PARAM), INIT_PARAM_VALUE_DELIMS)));

        if (appContextInitClasses != null) {
            Set<ApplicationContextInitializer<ConfigurableApplicationContext>> appContextInits = new TreeSet<>(AnnotationAwareOrderComparator.INSTANCE);

            for (Class<?> appContextInitClass : appContextInitClasses) {
                appContextInits.add(((ApplicationContextInitializer<ConfigurableApplicationContext>) BeanUtils.instantiateClass(appContextInitClass)));
            }

            for (ApplicationContextInitializer<ConfigurableApplicationContext> appContextInit : appContextInits) {
                appContextInit.initialize(this.appContext);
            }
        }

        this.appContext.refresh();

        if (parentAppContext instanceof AbstractApplicationContext) {
            ((AbstractApplicationContext) parentAppContext).addApplicationListener(this);
        }

        this.bus = this.appContext.getBean(Bus.class);
    }
}
