package com.denys.hibernateexample.db.listeners.rootaware.annotation;

import java.lang.annotation.*;

@Inherited
@Target(value = ElementType.TYPE)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Auditable {

    String[] propagationFields() default {};
    AuditableActions[] propagationActions() default {};

    enum AuditableActions {
        INSERT,
        UPDATE,
        DELETE
    }
}
