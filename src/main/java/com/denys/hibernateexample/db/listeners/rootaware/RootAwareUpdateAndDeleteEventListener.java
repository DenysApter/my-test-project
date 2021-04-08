package com.denys.hibernateexample.db.listeners.rootaware;


import com.denys.hibernateexample.db.listeners.rootaware.annotation.Auditable;
import org.hibernate.HibernateException;

import org.hibernate.engine.spi.EntityEntry;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.engine.spi.Status;
import org.hibernate.event.spi.FlushEntityEvent;
import org.hibernate.event.spi.FlushEntityEventListener;
import org.hibernate.persister.entity.EntityPersister;
import org.springframework.stereotype.Component;


import java.util.Arrays;
import java.util.List;


@Component
public class RootAwareUpdateAndDeleteEventListener extends AbstractRootAwareListener implements FlushEntityEventListener {

    @Override
    public void onFlushEntity(FlushEntityEvent event) throws HibernateException {
        final EntityEntry entry = event.getEntityEntry();
        final Object entity = event.getEntity();
        final boolean mightBeDirty = entry.requiresDirtyCheck(entity);

        if (mightBeDirty && entity.getClass().isAnnotationPresent(Auditable.class)) {
            Auditable auditable = entity.getClass().getAnnotation(Auditable.class);
            List<Auditable.AuditableActions> actionsList = Arrays.asList(auditable.propagationActions());

            if (updated(event) && actionsList.contains(Auditable.AuditableActions.UPDATE)) {
                updateVersion(entity , event, auditable);
            } else if (deleted(event) && actionsList.contains(Auditable.AuditableActions.DELETE)) {
                updateVersion(entity , event, auditable);
            }
        }
    }

    private boolean deleted(FlushEntityEvent event) {
        return event.getEntityEntry().getStatus() == Status.DELETED;
    }

    private boolean updated(FlushEntityEvent event) {
        final EntityEntry entry = event.getEntityEntry();
        final Object entity = event.getEntity();

        int[] dirtyProperties;
        EntityPersister persister = entry.getPersister();
        final Object[] values = event.getPropertyValues();
        SessionImplementor session = event.getSession();

        if (event.hasDatabaseSnapshot()) {
            dirtyProperties = persister.findModified(
                    event.getDatabaseSnapshot(), values, entity, session
            );
        } else {
            dirtyProperties = persister.findDirty(
                    values, entry.getLoadedState(), entity, session
            );
        }

        return dirtyProperties != null;
    }
}
