package gov.hhs.onc.iishubpilot.context.impl;

import gov.hhs.onc.iishubpilot.HubMode;
import gov.hhs.onc.iishubpilot.HubProperties;
import java.util.EnumSet;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.web.context.WebApplicationContext;

public class HubApplicationContextInitializer implements ApplicationContextInitializer<AbstractApplicationContext> {
    @Override
    public void initialize(AbstractApplicationContext appContext) {
        ConfigurableEnvironment env = appContext.getEnvironment();

        if (appContext instanceof WebApplicationContext) {
            env.addActiveProfile(HubProperties.CONTEXT_WEBAPP);
        }

        HubMode activeMode = HubMode.valueOf(env.getProperty(HubProperties.MODE, HubMode.PROD.name()).toUpperCase());
        env.addActiveProfile(activeMode.getProfile());
        System.setProperty(activeMode.getActivePropertyName(), Boolean.toString(true));

        for (HubMode inactiveMode : EnumSet.complementOf(EnumSet.of(activeMode))) {
            System.setProperty(inactiveMode.getActivePropertyName(), Boolean.toString(false));
        }
    }
}
