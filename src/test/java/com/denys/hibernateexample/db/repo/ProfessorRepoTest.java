package com.denys.hibernateexample.db.repo;


import com.denys.hibernateexample.db.entity.Person;
import com.denys.hibernateexample.db.entity.Professor;
import com.mysema.commons.lang.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Optional;


@DataJpaTest
class ProfessorRepoTest {
    @Autowired private JdbcTemplate jdbcTemplate;
    @Autowired private TestEntityManager testEntityManager;
    @Autowired private ProfessorRepo professorRepo;


    @BeforeEach
     void init() {
        Person person = testEntityManager.persistAndFlush(new Person("Kolya", 22));
        testEntityManager.persistAndFlush(new Professor(person));
    }

    @Test
    void checkFind() {
        Optional<Professor> professor = professorRepo.findById(1l);
        Assert.isTrue(professor.isPresent(), "");
        Assertions.assertEquals(professor.get().getPerson().getName(), "Kolya");
    }
}