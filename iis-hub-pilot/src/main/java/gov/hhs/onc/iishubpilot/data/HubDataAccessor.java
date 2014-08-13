package gov.hhs.onc.iishubpilot.data;

import java.io.Serializable;
import java.util.List;
import javax.annotation.Nullable;
import org.hibernate.criterion.Criterion;

public interface HubDataAccessor<T extends Serializable, U extends HubEntity<T>> {
    public List<U> findAll();

    public List<U> findByCriteria(Criterion ... criterions);

    @Nullable
    public U findById(T id);
}
