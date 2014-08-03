package gov.hhs.onc.iishubpilot.crypto;

import java.security.Provider;
import java.security.Security;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public final class HubCryptoProviders {
    public final static String BC_NAME = BouncyCastleProvider.PROVIDER_NAME;
    public final static BouncyCastleProvider BC = new BouncyCastleProvider();

    public final static String SUN_NAME = "SUN";
    public final static Provider SUN = Security.getProvider(SUN_NAME);

    public final static String SUN_JSSE_NAME = "SunJSSE";
    public final static Provider SUN_JSSE = Security.getProvider(SUN_JSSE_NAME);

    static {
        Security.removeProvider(SUN_NAME);
        Security.insertProviderAt(SUN, 1);
    }

    private HubCryptoProviders() {
    }
}
