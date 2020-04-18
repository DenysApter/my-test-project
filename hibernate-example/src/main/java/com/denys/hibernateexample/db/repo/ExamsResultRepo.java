package com.denys.hibernateexample.db.repo;

import com.denys.hibernateexample.db.entity.common.CompositeId;
import com.denys.hibernateexample.db.entity.ExamsResult;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamsResultRepo extends CrudRepository<ExamsResult, CompositeId> {
}
