package gov.hhs.onc.iishubpilot.crypto.impl;

import gov.hhs.onc.iishubpilot.HubProperties;
import gov.hhs.onc.iishubpilot.crypto.HubCertificateConfig;
import gov.hhs.onc.iishubpilot.crypto.HubCertificateGenerator;
import gov.hhs.onc.iishubpilot.crypto.HubCertificateInfo;
import gov.hhs.onc.iishubpilot.crypto.HubKeyPairConfig;
import gov.hhs.onc.iishubpilot.crypto.HubKeyPairGenerator;
import gov.hhs.onc.iishubpilot.crypto.HubKeyPairInfo;
import gov.hhs.onc.iishubpilot.crypto.utils.HubKeyStoreUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.PredicateUtils;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.asn1.x500.style.IETFUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration("configCryptoGen")
@Profile({ (HubProperties.MODE + ".dev") })
public class HubCryptoGenerateConfiguration implements InitializingBean {
    @Resource(name = "keyPairGenHub")
    private HubKeyPairGenerator keyPairGen;

    @Resource(name = "keyPairConfigHub")
    private HubKeyPairConfig keyPairConfig;

    @Resource(name = "certGenHub")
    private HubCertificateGenerator certGen;

    @Resource(name = "certConfigHubCa")
    private HubCertificateConfig caCertConfig;

    @Resource
    private List<HubCertificateConfig> certConfigs;

    @Resource(name = "&keyStoreMgrKey")
    private HubKeyStoreFactoryBean keyStoreMgrKeyFactory;

    @Resource(name = "&keyStoreMgrTrust")
    private HubKeyStoreFactoryBean keyStoreMgrTrustFactory;

    @Override
    public void afterPropertiesSet() throws Exception {
        KeyStore keyStoreMgrKey = this.keyStoreMgrKeyFactory.getObject(), keyStoreMgrTrust = this.keyStoreMgrTrustFactory.getObject();

        if ((keyStoreMgrKey.size() > 0) || (keyStoreMgrTrust.size() > 0)) {
            return;
        }

        String keyStoreMgrKeyPass = this.keyStoreMgrKeyFactory.getPassword(), keyStoreMgrTrustPass = this.keyStoreMgrTrustFactory.getPassword();

        HubKeyPairInfo caKeyPairInfo = this.keyPairGen.generate(this.keyPairConfig), keyPairInfo;
        HubCertificateInfo caCertInfo = this.certGen.generate(caKeyPairInfo, this.caCertConfig), certInfo;

        keyStoreMgrTrust.setCertificateEntry(getEntryAlias(caCertInfo.getSubjectDn()), caCertInfo.getObject());

        for (HubCertificateConfig certConfig : CollectionUtils.selectRejected(this.certConfigs, PredicateUtils.identityPredicate(this.caCertConfig))) {
            certInfo = this.certGen.generate(caKeyPairInfo, caCertInfo, (keyPairInfo = this.keyPairGen.generate(this.keyPairConfig)), certConfig);

            keyStoreMgrKey.setKeyEntry(getEntryAlias(certConfig.getSubjectDn()), keyPairInfo.getPrivateKey(), keyStoreMgrKeyPass.toCharArray(),
                new Certificate[] { certInfo.getObject() });
        }

        writeKeyStore(keyStoreMgrKey, this.keyStoreMgrKeyFactory.getResource().getFile(), keyStoreMgrKeyPass);
        writeKeyStore(keyStoreMgrTrust, this.keyStoreMgrTrustFactory.getResource().getFile(), keyStoreMgrTrustPass);
    }

    private static void writeKeyStore(KeyStore keyStore, File keyStoreFile, String keyStorePass) throws GeneralSecurityException, IOException {
        keyStoreFile.getParentFile().mkdirs();

        try (FileOutputStream keyStoreMgrKeyOutStream = new FileOutputStream(keyStoreFile)) {
            HubKeyStoreUtils.writeKeyStore(keyStore, keyStoreMgrKeyOutStream, keyStorePass);
        }
    }

    private static String getEntryAlias(X500Name entryCertSubjDn) {
        return IETFUtils.valueToString(entryCertSubjDn.getRDNs(BCStyle.CN)[0].getFirst().getValue());
    }
}
