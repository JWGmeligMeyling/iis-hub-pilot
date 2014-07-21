package gov.hhs.onc.iishubpilot.feature.impl;

import org.apache.cxf.annotations.SchemaValidation.SchemaValidationType;
import org.apache.cxf.feature.validation.SchemaValidationTypeProvider;
import org.apache.cxf.service.model.OperationInfo;
import org.springframework.stereotype.Component;

@Component("provSchemaValidationType")
public class HubSchemaValidationTypeProvider implements SchemaValidationTypeProvider {
    @Override
    public SchemaValidationType getSchemaValidationType(OperationInfo opInfo) {
        return SchemaValidationType.BOTH;
    }
}
