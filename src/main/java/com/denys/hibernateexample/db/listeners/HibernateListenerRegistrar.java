package com.denys.hibernateexample.db.listeners;

import com.denys.hibernateexample.db.listeners.rootaware.RootAwareInsertEventListener;
import com.denys.hibernateexample.db.listeners.rootaware.RootAwareUpdateAndDeleteEventListener;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;

@Component
@RequiredArgsConstructor
public class HibernateListenerRegistrar implements ApplicationRunner {
    @NonNull
    private EntityManagerFactory entityManagerFactory;
    @NonNull
    private RootAwareUpdateAndDeleteEventListener rootAwareUpdateAndDeleteEventListener;
    @NonNull
    private RootAwareInsertEventListener rootAwareInsertEventListener;

    @Override
    public void run(ApplicationArguments args) {
        SessionFactoryImpl sessionFactory = entityManagerFactory.unwrap(SessionFactoryImpl.class);
        EventListenerRegistry registry = sessionFactory.getServiceRegistry().getService(EventListenerRegistry.class);
        registry.getEventListenerGroup(EventType.PERSIST).appendListener(rootAwareInsertEventListener);
        registry.getEventListenerGroup(EventType.FLUSH_ENTITY).appendListener(rootAwareUpdateAndDeleteEventListener);
    }
}
