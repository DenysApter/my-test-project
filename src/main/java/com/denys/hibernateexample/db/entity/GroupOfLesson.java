package com.denys.hibernateexample.db.entity;

import com.denys.hibernateexample.db.listeners.rootaware.annotation.Auditable;
import lombok.*;

import javax.persistence.*;
import java.util.List;


@Entity(name = "group_of_lesson")
@ToString
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Auditable(propagationActions = {Auditable.AuditableActions.UPDATE, Auditable.AuditableActions.INSERT}, propagationFields = "professor")
public class GroupOfLesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_of_lesson_id", nullable = false)
    @Setter(AccessLevel.NONE)
    private Long id;

    @NonNull
    @JoinColumn(name = "lesson_id", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Lesson lesson;

    @NonNull
    @Column(name = "group_name", nullable = false)
    private String name;

    @NonNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "university_id")
    private University university;


    @NonNull
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "group_of_lesson_student",
            joinColumns = @JoinColumn(name = "group_of_lesson_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<Student> student;

    @NonNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "group_of_lesson_professor",
            joinColumns = @JoinColumn(name = "group_of_lesson_id"),
            inverseJoinColumns = @JoinColumn(name = "professor_id"))
    private Professor professor;
}
