package gov.hhs.onc.iishubpilot.data.impl;

import gov.hhs.onc.iishubpilot.data.HubDao;
import gov.hhs.onc.iishubpilot.data.HubDataService;
import gov.hhs.onc.iishubpilot.data.HubEntity;
import java.io.Serializable;
import java.util.List;
import javax.annotation.Nullable;
import org.hibernate.criterion.Criterion;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public abstract class AbstractHubDataService<T extends Serializable, U extends HubEntity<T>, V extends HubDao<T, U>> extends AbstractHubDataAccessor<T, U>
    implements HubDataService<T, U, V> {
    protected V dao;

    protected AbstractHubDataService(Class<T> idClass, Class<U> entityClass, V dao) {
        super(idClass, entityClass);

        this.dao = dao;
    }

    @Override
    public void evictCache() {
        this.dao.evictCache();
    }

    @Override
    public List<U> findAll() {
        return this.dao.findAll();
    }

    @Override
    public List<U> findByCriteria(Criterion ... criterions) {
        return this.dao.findByCriteria(criterions);
    }

    @Nullable
    public U findById(T id) {
        return this.dao.findById(id);
    }
}
