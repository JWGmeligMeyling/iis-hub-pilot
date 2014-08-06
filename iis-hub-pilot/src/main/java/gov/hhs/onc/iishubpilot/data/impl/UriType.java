package gov.hhs.onc.iishubpilot.data.impl;

import java.net.URI;
import javax.annotation.Nullable;
import org.apache.commons.lang3.ClassUtils;
import org.hibernate.dialect.Dialect;
import org.hibernate.type.AbstractSingleColumnStandardBasicType;
import org.hibernate.type.DiscriminatorType;
import org.hibernate.type.StringType;
import org.hibernate.type.descriptor.WrapperOptions;
import org.hibernate.type.descriptor.java.AbstractTypeDescriptor;
import org.hibernate.type.descriptor.sql.VarcharTypeDescriptor;
import org.springframework.stereotype.Component;

@Component("userTypeUri")
public class UriType extends AbstractSingleColumnStandardBasicType<URI> implements DiscriminatorType<URI> {
    public static class UriTypeDescriptor extends AbstractTypeDescriptor<URI> {
        public final static UriTypeDescriptor INSTANCE = new UriTypeDescriptor();

        private final static long serialVersionUID = 0L;

        public UriTypeDescriptor() {
            super(URI.class);
        }

        @Override
        public String toString(URI value) {
            return value.toString();
        }

        @Override
        public URI fromString(String str) {
            return URI.create(str);
        }

        @Nullable
        @Override
        public <X> X unwrap(@Nullable URI value, Class<X> type, WrapperOptions options) {
            if (value == null) {
                return null;
            } else if (ClassUtils.isAssignable(type, String.class)) {
                return type.cast(this.toString(value));
            } else {
                throw unknownUnwrap(type);
            }
        }

        @Nullable
        @Override
        public <X> URI wrap(@Nullable X value, WrapperOptions options) {
            if (value == null) {
                return null;
            } else if (value instanceof String) {
                return this.fromString(((String) value));
            } else {
                throw unknownWrap(value.getClass());
            }
        }
    }

    private final static long serialVersionUID = 0L;

    public UriType() {
        super(VarcharTypeDescriptor.INSTANCE, UriTypeDescriptor.INSTANCE);
    }

    @Override
    public URI stringToObject(String str) throws Exception {
        return UriTypeDescriptor.INSTANCE.fromString(str);
    }

    @Override
    public String objectToSQLString(URI value, Dialect dialect) throws Exception {
        return StringType.INSTANCE.objectToSQLString(this.toString(value), dialect);
    }

    @Override
    public String getName() {
        return UriTypeDescriptor.INSTANCE.getJavaTypeClass().getSimpleName().toLowerCase();
    }

    @Override
    protected boolean registerUnderJavaType() {
        return true;
    }
}
