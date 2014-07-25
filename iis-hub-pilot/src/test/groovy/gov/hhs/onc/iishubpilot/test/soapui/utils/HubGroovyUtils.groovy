package gov.hhs.onc.iishubpilot.test.soapui.utils

import com.eviware.soapui.support.GroovyUtils
import gov.hhs.onc.iishubpilot.ws.TestHubHttpHeaders
import java.nio.file.Paths

final class HubGroovyUtils {
    private final static DATA_DIR_NAME = "data";
    
    static readDataFile(context, dataFileName) {
        return Paths.get(new GroovyUtils(context).projectPath, DATA_DIR_NAME, dataFileName).toFile().text
    }
    
    static void addHeaders(project, testSuite, testCase, testStep, req) {
        def reqHeaders = req.requestHeaders
        reqHeaders.put(TestHubHttpHeaders.SOAPUI_PROJECT, project.name)
        reqHeaders.put(TestHubHttpHeaders.SOAPUI_TEST_SUITE, testSuite.name)
        reqHeaders.put(TestHubHttpHeaders.SOAPUI_TEST_CASE, testCase.name)
        reqHeaders.put(TestHubHttpHeaders.SOAPUI_TEST_STEP, testStep.name)
        reqHeaders.put(TestHubHttpHeaders.SOAPUI_REQ, req.name)
        req.requestHeaders = reqHeaders
    }
    
    private HubGroovyUtils() {
    }
}
