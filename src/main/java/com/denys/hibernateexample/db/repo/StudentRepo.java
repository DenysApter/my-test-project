package com.denys.hibernateexample.db.repo;

import com.denys.hibernateexample.db.entity.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends CrudRepository<Student, Long> {
}
