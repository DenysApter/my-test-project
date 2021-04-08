package com.denys.hibernateexample.db.repo;

import com.denys.hibernateexample.db.entity.Professor;


import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfessorRepo extends JpaRepository<Professor, Long> {

    @Query(value = "select p from professor p join p.groupOfLessons g join g.lesson l where l.name like '%ENGLISH%'")
    List<Professor> findAllEnglishTeachers();

    @Query(value = "select p from professor p join p.groupOfLessons g join g.lesson l where l.name like '%ENGLISH%'")
    List<Professor> findAllEnglishTeachersPage(Pageable pageable);
}
