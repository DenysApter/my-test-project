package com.denys.hibernateexample.db.repo.readonlyrepos;

import com.denys.hibernateexample.db.entity.Professor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.util.Optional;

@NoRepositoryBean
public interface ReadOnlyRepo extends Repository<Professor, Long> {
    Optional<Professor> findById(Long aLong);
    Iterable<Professor> findAll();
}
