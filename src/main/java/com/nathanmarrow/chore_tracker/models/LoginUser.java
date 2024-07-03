package com.nathanmarrow.chore_tracker.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginUser {
	
	@NotBlank(message = "Email must not be blank")
	@Email(message = "Email must be valid")
	private String email;
	
	@NotBlank(message = "Password must not be blank")
	@Size(min=8, max=255, message = "Password must be at least 8 characters")
	private String password;
	
	public LoginUser() {}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
