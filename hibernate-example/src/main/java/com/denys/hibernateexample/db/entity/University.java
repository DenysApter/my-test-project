package com.denys.hibernateexample.db.entity;

import lombok.*;

import javax.persistence.*;



@ToString
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
@Entity(name = "university")
public class University {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "university_id", nullable = false)
    private long id;

    @NonNull
    @Column(name = "university_name")
    private String name;
}
