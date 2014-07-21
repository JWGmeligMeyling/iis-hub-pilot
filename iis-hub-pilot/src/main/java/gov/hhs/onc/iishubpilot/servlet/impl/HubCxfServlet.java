package gov.hhs.onc.iishubpilot.servlet.impl;

import gov.hhs.onc.iishubpilot.utils.HubStringUtils;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import javax.annotation.Nullable;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.http.MediaType;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.support.XmlWebApplicationContext;

public class HubCxfServlet extends CXFNonSpringServlet implements ApplicationListener<ContextRefreshedEvent> {
    private final static String FAVICON_EXT = "ico";
    private final static String FAVICON_PATH = ("/favicon." + FAVICON_EXT);
    private final static String FAVICON_PATH_STATIC = ("/static/images/iis-hub-pilot-16x16." + FAVICON_EXT);
    private final static String FAVICON_CONTENT_TYPE = new MediaType("image", "x-icon").toString();

    private final static long serialVersionUID = 0L;

    private XmlWebApplicationContext appContext;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        this.destroy();
        this.setBus(null);

        try {
            this.init(this.getServletConfig());
        } catch (ServletException e) {
            throw new ApplicationContextException("Unable to re-initialize Hub CXF Spring application context.", e);
        }
    }

    @Override
    public void destroyBus() {
        if (this.appContext != null) {
            this.appContext.close();
        }
    }

    @Override
    protected void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        if (Objects.equals(req.getPathInfo(), FAVICON_PATH)) {
            this.serveStaticContent(req, resp, FAVICON_PATH_STATIC);
        } else {
            super.handleRequest(req, resp);
        }
    }

    @Nullable
    @Override
    protected String getStaticResourceContentType(String resourceExt) {
        return (!resourceExt.equals(FAVICON_EXT) ? super.getStaticResourceContentType(resourceExt) : FAVICON_CONTENT_TYPE);
    }

    @Override
    @SuppressWarnings({ "unchecked" })
    protected void loadBus(ServletConfig servletConfig) {
        WebApplicationContext parentAppContext = WebApplicationContextUtils.getWebApplicationContext(servletConfig.getServletContext());

        this.appContext = new XmlWebApplicationContext();
        this.appContext.setParent(parentAppContext);
        this.appContext.setServletConfig(servletConfig);
        this.appContext.setConfigLocations(HubStringUtils.tokenizePropertyValue(servletConfig.getInitParameter(ContextLoader.CONFIG_LOCATION_PARAM)));

        List<Class<?>> appContextInitClasses =
            ClassUtils.convertClassNamesToClasses(Arrays.asList(HubStringUtils.tokenizePropertyValue(servletConfig
                .getInitParameter(ContextLoader.CONTEXT_INITIALIZER_CLASSES_PARAM))));

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
