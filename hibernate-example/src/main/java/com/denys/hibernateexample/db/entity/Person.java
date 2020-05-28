package com.denys.hibernateexample.db.entity;

import lombok.*;

import javax.persistence.*;

@ToString
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
@Entity(name = "person")
@AllArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id", nullable = false)
    private long id;


    @Column(name = "person_name")
    @NonNull private String name;


     @Column(name = "person_age")
     private Integer age;
}
