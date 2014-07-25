package gov.hhs.onc.iishubpilot.crypto;

import java.io.IOException;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;
import org.bouncycastle.operator.OperatorException;

public interface HubCertificateGenerator extends HubCryptoGenerator {
    public HubCertificateInfo generate(HubKeyPairInfo keyPairInfo, HubCertificateConfig certConfig) throws GeneralSecurityException, IOException,
        OperatorException;

    public HubCertificateInfo generate(@Nullable HubKeyPairInfo issuerKeyPairInfo, @Nullable HubCertificateInfo issuerCertInfo, HubKeyPairInfo keyPairInfo,
        HubCertificateConfig certConfig) throws GeneralSecurityException, IOException, OperatorException;
}
