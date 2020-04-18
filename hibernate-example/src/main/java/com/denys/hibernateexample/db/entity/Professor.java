package com.denys.hibernateexample.db.entity;

import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Map;


@Entity(name = "professor")
@Getter @Setter @ToString(of = {"id", "version", "person"})
@RequiredArgsConstructor @NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "professor_id", nullable = false)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Version
    @Transient
    @Setter(AccessLevel.PRIVATE)
    private long version;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id", nullable = false)
    @NonNull
    private Person person;

   // @Where(clause = "group_of_lesson_id NOT IN (3)")
    @BatchSize(size = 1)
    @MapKey(name = "name")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "professor")
    private Map<String, GroupOfLesson> groupOfLessons;
}
