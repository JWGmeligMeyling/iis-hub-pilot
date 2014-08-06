package gov.hhs.onc.iishubpilot.data.impl;

import java.util.Set;
import javax.annotation.Nullable;
import org.hibernate.SessionFactory;
import org.hibernate.type.AbstractSingleColumnStandardBasicType;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;

public class HubLocalSessionFactoryBean extends LocalSessionFactoryBean {
    private Set<AbstractSingleColumnStandardBasicType<?>> userTypes;

    @Override
    protected SessionFactory buildSessionFactory(LocalSessionFactoryBuilder sessionFactoryBuilder) {
        if (this.userTypes != null) {
            this.userTypes.forEach(sessionFactoryBuilder::registerTypeOverride);
        }

        return super.buildSessionFactory(sessionFactoryBuilder);
    }

    public void setUserTypes(@Nullable Set<AbstractSingleColumnStandardBasicType<?>> userTypes) {
        this.userTypes = userTypes;
    }
}
