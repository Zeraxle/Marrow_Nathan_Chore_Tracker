package com.nathanmarrow.chore_tracker.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nathanmarrow.chore_tracker.models.Job;

@Repository
public interface JobRepository extends CrudRepository<Job, Long>{
	
	public Job findByTitle(String title);
	
	public List<Job> findAll();
	
	
	
}
