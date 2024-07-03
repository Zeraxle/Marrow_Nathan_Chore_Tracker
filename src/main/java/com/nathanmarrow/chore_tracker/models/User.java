package com.nathanmarrow.chore_tracker.models;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "First Name must not be blank")
	@Size(min=2, max=255, message = "First Name must be at least 2 characters")
	private String firstName;
	
	@NotEmpty(message = "Last Name must not be blank")
	@Size(min=2, max=255, message = "Last Name must be at least 2 characters")
	private String lastName;
	
	@NotEmpty(message = "Email must not be blank")
	@Email(message = "Email must be valid")
	private String email;
	
	@NotEmpty(message = "Password must not be blank")
	@Size(min=8, max=255, message = "Password must be at least 8 characters")
	private String password;
	
	@Transient
	@NotEmpty(message = "Confirm Password must not be blank")
	@Size(min=8, max=255, message = "Confirm Password must be at least 8 characters")
	private String confirmPassword;
	
	@OneToOne(mappedBy="userWithLicense", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private Worker licensedWorker;
	
	@OneToMany(mappedBy="jobOwner", fetch=FetchType.LAZY )
	private List<Job> postedJobs;
	
	@Column(updatable=false)
	@DateTimeFormat(pattern = "yyyy-DD-mm")
	private Date createdAt;
	
	@DateTimeFormat(pattern = "yyyy-DD-mm")
	private Date updatedAt;
	
	@PrePersist
	public void onCreate() {
		this.createdAt = new Date();
	}
	
	@PreUpdate
	public void onUpdate() {
		this.updatedAt = new Date();
	}
	
	public User() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

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

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public List<Job> getPostedJobs() {
		return postedJobs;
	}

	public void setPostedJobs(List<Job> postedJobs) {
		this.postedJobs = postedJobs;
	}

	public Worker getLicensedWorker() {
		return licensedWorker;
	}

	public void setLicensedWorker(Worker licensedWorker) {
		this.licensedWorker = licensedWorker;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	
}
