package com.denys.hibernateexample.db.repo;

import com.denys.hibernateexample.db.entity.University;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniversityRepo extends CrudRepository<University, Long> {
}
