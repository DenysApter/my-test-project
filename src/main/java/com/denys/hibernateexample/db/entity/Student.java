package com.denys.hibernateexample.db.entity;

import lombok.NonNull;

import javax.persistence.*;
import java.util.List;

@Entity(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id", nullable = false)
    private long id;

    @NonNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;


    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "student")
    private List<GroupOfLesson> groupOfLessons;
}
