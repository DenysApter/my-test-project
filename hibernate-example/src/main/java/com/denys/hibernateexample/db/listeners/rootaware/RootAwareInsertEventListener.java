package com.denys.hibernateexample.db.listeners.rootaware;

import com.denys.hibernateexample.db.listeners.rootaware.annotation.Auditable;
import org.hibernate.HibernateException;
import org.hibernate.event.spi.PersistEvent;
import org.hibernate.event.spi.PersistEventListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;

@Component
public class RootAwareInsertEventListener extends AbstractRootAwareListener implements PersistEventListener {

    @Override
    public void onPersist(PersistEvent event) throws HibernateException {
        final Object entity = event.getObject();

        if (entity.getClass().isAnnotationPresent(Auditable.class) &&
                Arrays.asList(entity.getClass().getAnnotation(Auditable.class).propagationActions())
                        .contains(Auditable.AuditableActions.INSERT)) {
            Auditable auditable = entity.getClass().getAnnotation(Auditable.class);
            updateVersion(entity, event, auditable);
        }
    }

    @Override
    public void onPersist(PersistEvent event, Map createdAlready) throws HibernateException {
        onPersist(event);
    }
}
