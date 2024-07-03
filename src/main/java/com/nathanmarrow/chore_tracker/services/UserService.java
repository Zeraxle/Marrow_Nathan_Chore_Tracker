package com.nathanmarrow.chore_tracker.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nathanmarrow.chore_tracker.models.User;
import com.nathanmarrow.chore_tracker.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;
	
	public User get(String email) {
		return repo.findByEmail(email);
	}
	
	public User get(Long id) {
		return repo.findById(id).orElse(null);
	}
	
	public User create(User user) {
        return repo.save(user);
	}
	
}
