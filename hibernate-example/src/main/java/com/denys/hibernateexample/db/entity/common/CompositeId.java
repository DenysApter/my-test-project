package com.denys.hibernateexample.db.entity.common;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class CompositeId implements Serializable {

    @Column(name = "lesson_id")
    private Long lesson;

    @Column(name = "student_id")
    private Long student;

    @Column(name = "professor_id")
    private Long professor;
}
