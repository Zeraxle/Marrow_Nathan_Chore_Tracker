package com.nathanmarrow.chore_tracker.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nathanmarrow.chore_tracker.models.Job;
import com.nathanmarrow.chore_tracker.repositories.JobRepository;

@Service
public class JobService {

	@Autowired
	private JobRepository repo;

	public Job get(String title) {
		return repo.findByTitle(title);
	}
	
	public Job get(Long id) {
		return repo.findById(id).orElse(null);
	}

	public List<Job> getAll() {
		return repo.findAll();
	}

	public Job create(Job job) {
		return repo.save(job);
	}

	public Job update(Job job) {
		return repo.save(job);
	}

	public void destroy(Long id) {
		repo.deleteById(id);
	}
	
}
