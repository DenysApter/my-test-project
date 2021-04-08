package com.denys.hibernateexample.db.listeners.rootaware;

import com.denys.hibernateexample.db.listeners.rootaware.annotation.Auditable;
import lombok.SneakyThrows;
import org.hibernate.LockMode;
import org.hibernate.event.spi.AbstractEvent;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractRootAwareListener {

    protected void updateVersion(final Object entity , AbstractEvent event, Auditable auditable) {
        List<Object> roots = findRoot(entity, auditable);
        roots.forEach(root ->  incrementRootVersion(event, root));
    }

    private List<Object> findRoot(Object entity, Auditable auditable) {
        List<String> fieldsList = Arrays.asList(auditable.propagationFields());
        List<Field> fields = getFields(entity);
        List<Object> roots = fields.stream()
                .filter(f -> fieldsList.contains(f.getName()))
                .map(field -> getRoot(field, entity)).collect(Collectors.toList());
        return roots;
    }

    @SneakyThrows
    private Object getRoot(Field field,  Object entity) {
        field.setAccessible(true);
        return field.get(entity);
    }

    private <T> List<Field> getFields(T t) {
        List<Field> fields = new ArrayList<>();
        Class clazz = t.getClass();
        while (clazz != Object.class) {
            fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
            clazz = clazz.getSuperclass();
        }
        return fields;
    }

    private void incrementRootVersion(AbstractEvent event, Object root) {
        event.getSession().lock(root, LockMode.OPTIMISTIC_FORCE_INCREMENT);
    }
}
