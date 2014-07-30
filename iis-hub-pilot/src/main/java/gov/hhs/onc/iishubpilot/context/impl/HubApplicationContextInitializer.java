package gov.hhs.onc.iishubpilot.context.impl;

import gov.hhs.onc.iishubpilot.context.HubProfiles;
import gov.hhs.onc.iishubpilot.context.HubProperties;
import org.springframework.context.ApplicationContextException;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

public class HubApplicationContextInitializer implements ApplicationContextInitializer<AbstractApplicationContext> {
    @Override
    public void initialize(AbstractApplicationContext appContext) {
        ConfigurableEnvironment env = appContext.getEnvironment();
        String modeValue = env.getProperty(HubProperties.MODE_NAME, HubProperties.PROD_MODE_VALUE), modeProfile;

        if (modeValue.equalsIgnoreCase(HubProperties.DEV_MODE_VALUE)) {
            modeProfile = HubProfiles.DEV_MODE;
        } else if (modeValue.equalsIgnoreCase(HubProperties.PROD_MODE_VALUE)) {
            modeProfile = HubProfiles.PROD_MODE;
        } else {
            throw new ApplicationContextException(String.format("Unknown Hub mode: %s", modeValue));
        }

        env.addActiveProfile(modeProfile);
    }
}
