package gov.hhs.onc.iishubpilot.context.impl;

import gov.hhs.onc.iishubpilot.HubMode;
import gov.hhs.onc.iishubpilot.HubProperties;
import java.util.EnumSet;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.web.context.support.XmlWebApplicationContext;

public class HubApplicationContextInitializer implements ApplicationContextInitializer<XmlWebApplicationContext> {
    @Override
    public void initialize(XmlWebApplicationContext appContext) {
        ConfigurableEnvironment env = appContext.getEnvironment();
        HubMode activeMode = HubMode.valueOf(env.getProperty(HubProperties.MODE, HubMode.PROD.name()).toUpperCase());

        env.setActiveProfiles(activeMode.getProfile());

        System.setProperty(activeMode.getActivePropertyName(), Boolean.toString(true));

        for (HubMode inactiveMode : EnumSet.complementOf(EnumSet.of(activeMode))) {
            System.setProperty(inactiveMode.getActivePropertyName(), Boolean.toString(false));
        }
    }
}
