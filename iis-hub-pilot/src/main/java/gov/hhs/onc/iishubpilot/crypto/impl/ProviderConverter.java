package gov.hhs.onc.iishubpilot.crypto.impl;

import java.security.Provider;
import java.security.Security;
import javax.annotation.Nullable;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component("convProv")
public class ProviderConverter implements Converter<String, Provider> {
    @Nullable
    @Override
    public Provider convert(String provName) {
        return Security.getProvider(provName);
    }
}
