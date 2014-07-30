package gov.hhs.onc.iishubpilot.test.impl;

import gov.hhs.onc.iishubpilot.context.impl.HubApplicationContextInitializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.support.GenericXmlContextLoader;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.testng.annotations.Test;

@ContextConfiguration(initializers = { HubApplicationContextInitializer.class }, loader = GenericXmlContextLoader.class, locations = {
    "classpath*:WEB-INF/spring/spring-hub.xml", "classpath*:WEB-INF/spring/spring-hub-*.xml" })
@SuppressWarnings({ "SpringContextConfigurationInspection" })
@Test(groups = { "hub.test.all" })
@TestExecutionListeners(listeners = { DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
    TransactionalTestExecutionListener.class }, inheritListeners = false)
public abstract class AbstractHubTests extends AbstractTestNGSpringContextTests {
}
