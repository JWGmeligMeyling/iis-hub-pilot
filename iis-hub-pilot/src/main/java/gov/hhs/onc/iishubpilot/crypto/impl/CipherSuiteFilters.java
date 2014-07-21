package gov.hhs.onc.iishubpilot.crypto.impl;

import java.util.List;
import org.apache.cxf.configuration.security.FiltersType;

public class CipherSuiteFilters extends FiltersType {
    public void setExclude(List<String> exclude) {
        this.exclude = exclude;
    }

    public void setInclude(List<String> include) {
        this.include = include;
    }
}
