package com.denys.hibernateexample.db.service;

import com.denys.hibernateexample.db.entity.Person;
import com.denys.hibernateexample.db.entity.Professor;
import com.denys.hibernateexample.db.entity.QProfessor;
import com.denys.hibernateexample.db.repo.ProfessorRepo;
import com.denys.hibernateexample.db.repo.readonlyrepos.ProfessorReadOnlyRepo;

import com.querydsl.jpa.impl.JPAQueryFactory;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import java.util.Optional;



@Service
public class ProfessorService {

    private ProfessorRepo professorRepo;

    private ProfessorReadOnlyRepo professorReadOnlyRepo;

    private EntityManager entityManager;

    private JPAQueryFactory queryFactory;


    public ProfessorService(ProfessorRepo professorRepo, ProfessorReadOnlyRepo professorReadOnlyRepo, EntityManager entityManager) {
        this.professorRepo = professorRepo;
        this.professorReadOnlyRepo = professorReadOnlyRepo;
        this.entityManager = entityManager;
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Transactional
    public List<Professor> test() {
        List<Professor> engProfessors = professorRepo.findAllEnglishTeachers();
        return engProfessors;
    }

    @Transactional
    public List<Professor> testPage() {
        Pageable pageable = PageRequest.of(0, 2, Sort.by("person.name").descending());
        List<Professor> engProfessors = professorRepo.findAllEnglishTeachersPage(pageable);
        return engProfessors;
    }

    @Transactional  // Professor(id=4, version=0, person=Person(id=20, name=Zahar, age=26))
    public List<Professor> testFindByExample() {
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnoreCase();
        Example<Professor> example = Example.of(new Professor(4L, new Person(20, "zahar", null)), exampleMatcher);
        Optional<Professor> res = professorRepo.findOne(example);
        return res.map(List::of).orElseThrow(IllegalArgumentException::new);
    }

    public List<Professor> queryDSLExample() {
        QProfessor professor = QProfessor.professor;
        List<Professor> prof = queryFactory.selectFrom(professor)
                .where(professor.person.name.eq("Zahar").and(professor.id.notIn(100)))
                .groupBy(professor.id)
                .having(professor.person.age.notIn(26))
                .fetch();
        return prof;
    }
}

 