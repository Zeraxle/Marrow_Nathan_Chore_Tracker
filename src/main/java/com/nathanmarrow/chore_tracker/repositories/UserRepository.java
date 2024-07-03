package com.nathanmarrow.chore_tracker.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nathanmarrow.chore_tracker.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

	public User findByEmail(String email);
	
	
	
}
