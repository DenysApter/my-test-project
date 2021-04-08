package com.denys.hibernateexample.db.repo;

import com.denys.hibernateexample.db.entity.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepo extends CrudRepository<Person, Long> {
}
