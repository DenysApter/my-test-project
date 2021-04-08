package com.denys.hibernateexample.db.repo;

import com.denys.hibernateexample.db.entity.GroupOfLesson;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupOfLessonRepo extends CrudRepository<GroupOfLesson, Long> {
}
