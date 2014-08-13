package gov.hhs.onc.iishubpilot.data.impl;

import com.github.sebhoss.warnings.CompilerWarnings;
import gov.hhs.onc.iishubpilot.data.HubDao;
import gov.hhs.onc.iishubpilot.data.HubEntity;
import java.io.Serializable;
import java.util.List;
import javax.annotation.Nullable;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractHubDao<T extends Serializable, U extends HubEntity<T>> extends AbstractHubDataAccessor<T, U> implements HubDao<T, U> {
    @Autowired
    protected SessionFactory sessionFactory;

    protected Class<? extends U> entityImplClass;

    protected AbstractHubDao(Class<T> idClass, Class<U> entityClass, Class<? extends U> entityImplClass) {
        super(idClass, entityClass);

        this.entityImplClass = entityImplClass;
    }

    @Override
    public List<U> findAll() {
        return this.findByCriteria();
    }

    @Override
    @SuppressWarnings({ CompilerWarnings.UNCHECKED })
    public List<U> findByCriteria(Criterion ... criterions) {
        Criteria criteria = this.getSession().createCriteria(this.entityImplClass);

        for (Criterion criterion : criterions) {
            criteria.add(criterion);
        }

        return ((List<U>) criteria.list());
    }

    @Nullable
    @Override
    public U findById(T id) {
        return this.entityClass.cast(this.getSession().get(this.entityImplClass, id));
    }

    protected Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }
}
