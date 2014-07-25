package gov.hhs.onc.iishubpilot.crypto.utils;

import org.bouncycastle.operator.DefaultDigestAlgorithmIdentifierFinder;
import org.bouncycastle.operator.DefaultSignatureAlgorithmIdentifierFinder;
import org.bouncycastle.operator.DigestAlgorithmIdentifierFinder;
import org.bouncycastle.operator.SignatureAlgorithmIdentifierFinder;

public final class HubCryptoAlgorithmUtils {
    public final static DigestAlgorithmIdentifierFinder DIGEST_ALG_ID_FINDER = new DefaultDigestAlgorithmIdentifierFinder();

    public final static SignatureAlgorithmIdentifierFinder SIG_ALG_ID_FINDER = new DefaultSignatureAlgorithmIdentifierFinder();

    private HubCryptoAlgorithmUtils() {
    }
}
