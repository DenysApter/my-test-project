package com.denys.hibernateexample.db.entity;

import lombok.*;

import javax.persistence.*;

@ToString
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
@Entity(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id", nullable = false)
    private long id;

    @NonNull
    @Column(name = "person_name")
    private String name;

    @NonNull
    @Column(name = "person_age")
    private int age;
}
