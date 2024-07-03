package com.nathanmarrow.chore_tracker.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nathanmarrow.chore_tracker.models.Worker;

@Repository
public interface WorkerRepository extends CrudRepository<Worker, Long>{
	
}
