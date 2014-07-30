package gov.hhs.onc.iishubpilot.context.impl;

import gov.hhs.onc.iishubpilot.context.HubProfiles;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.web.context.support.AbstractRefreshableWebApplicationContext;

public class HubWebApplicationContextInitializer implements ApplicationContextInitializer<AbstractRefreshableWebApplicationContext> {
    @Override
    public void initialize(AbstractRefreshableWebApplicationContext appContext) {
        appContext.getEnvironment().addActiveProfile(HubProfiles.WEBAPP_CONTEXT);
    }
}
