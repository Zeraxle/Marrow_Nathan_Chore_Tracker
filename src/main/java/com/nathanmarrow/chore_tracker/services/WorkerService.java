package com.nathanmarrow.chore_tracker.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nathanmarrow.chore_tracker.models.Worker;
import com.nathanmarrow.chore_tracker.repositories.WorkerRepository;

@Service
public class WorkerService {
	
	@Autowired
	WorkerRepository repo;
	
	public Worker get(Long id) {
		return repo.findById(id).orElse(null);
	}
	
	public Worker create(Worker worker) {
        return repo.save(worker);
	}
	
}
