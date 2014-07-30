package gov.hhs.onc.iishubpilot.test.soapui.utils

import com.eviware.soapui.support.GroovyUtils
import java.nio.file.Paths

final class HubGroovyUtils {
    private final static DATA_DIR_NAME = "data";
    
    private HubGroovyUtils() {
    }
    
    static readDataFile(context, dataFileName) {
        return Paths.get(new GroovyUtils(context).projectPath, DATA_DIR_NAME, dataFileName).toFile().text
    }
}
