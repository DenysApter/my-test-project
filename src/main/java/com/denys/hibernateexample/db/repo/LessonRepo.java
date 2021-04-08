package com.denys.hibernateexample.db.repo;

import com.denys.hibernateexample.db.entity.Lesson;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepo extends CrudRepository<Lesson, Long> {
}
