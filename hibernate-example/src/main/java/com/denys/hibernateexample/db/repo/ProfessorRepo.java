package com.denys.hibernateexample.db.repo;

import com.denys.hibernateexample.db.entity.Professor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfessorRepo extends CrudRepository<Professor, Long> {

    @Query(value = "select p from professor p join p.groupOfLessons g join g.lesson l where l.name like '%ENGLISH%'")
    List<Professor> findAllEnglishTeachers();
}
