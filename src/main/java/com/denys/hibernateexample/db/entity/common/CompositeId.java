package com.denys.hibernateexample.db.entity.common;

import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@EqualsAndHashCode
@Embeddable
public class CompositeId implements Serializable {

    @Column(name = "lesson_id")
    private Long lesson;

    @Column(name = "student_id")
    private Long student;

    @Column(name = "professor_id")
    private Long professor;
}
