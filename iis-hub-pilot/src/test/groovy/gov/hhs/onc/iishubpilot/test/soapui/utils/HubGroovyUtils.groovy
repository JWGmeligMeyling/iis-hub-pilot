package gov.hhs.onc.iishubpilot.test.soapui.utils

import com.eviware.soapui.support.GroovyUtils
import gov.hhs.onc.iishubpilot.crypto.HubCryptoProviders
import java.nio.file.Paths
import org.apache.cxf.ws.addressing.JAXWSAConstants
import org.apache.cxf.ws.addressing.Names

final class HubGroovyUtils {
    private final static WSA_XPATH_PREFIX = "declare namespace " + JAXWSAConstants.WSA_PREFIX + "='" + Names.WSA_NAMESPACE_NAME + "'; //" + 
        JAXWSAConstants.WSA_PREFIX + ":"
    
    private final static WSA_MSG_ID_XPATH = WSA_XPATH_PREFIX + Names.WSA_MESSAGEID_NAME
    private final static WSA_RELATES_TO_XPATH = WSA_XPATH_PREFIX + Names.WSA_RELATESTO_NAME
    
    private final static DATA_DIR_NAME = "data"
    
    static {
        HubCryptoProviders.resetProviders()
    }
    
    private HubGroovyUtils() {
    }
    
    static assertAddressingMessageIds(context, msgExchange) {
        def utils = new GroovyUtils(context)
        def reqXmlHolder = utils.getXmlHolder(msgExchange.requestContentAsXml), respXmlHolder = utils.getXmlHolder(msgExchange.responseContentAsXml)
        
        assert (reqXmlHolder.getNodeValue(WSA_MSG_ID_XPATH) == respXmlHolder.getNodeValue(WSA_RELATES_TO_XPATH))
    }
    
    static readDataFile(context, dataFileName) {
        return Paths.get(new GroovyUtils(context).projectPath, DATA_DIR_NAME, dataFileName).toFile().text
    }
}
