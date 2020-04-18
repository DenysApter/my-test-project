package com.denys.hibernateexample.db.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;


@EqualsAndHashCode(of = "name")
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Entity(name = "lesson")
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lesson_id", nullable = false)
    @Setter(AccessLevel.NONE)
    private Long id;

    @NonNull
    @Column(name = "lesson_name", nullable = false)
    private String name;

    @NonNull
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "group_of_lesson",
            joinColumns = @JoinColumn(name = "lesson_id", referencedColumnName = "lesson_id"),
            inverseJoinColumns = @JoinColumn(name = "university_id", referencedColumnName = "university_id"))
    private List<University> university;
}
