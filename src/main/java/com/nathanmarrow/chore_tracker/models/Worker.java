package com.nathanmarrow.chore_tracker.models;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "workers")
public class Worker {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	@Size(min=2, max=255, message="First Name must be at least two characters")
	private String firstName;
	
	@NotEmpty
	@Size(min=2, max=255, message="Last Name must be at least two characters")
	private String lastName;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User userWithLicense;
	
	@OneToMany(mappedBy="jobTaker", fetch = FetchType.LAZY)
	private List<Job> takenJobs;
	
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
	
	public Worker() {}

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

	public List<Job> getTakenJobs() {
		return takenJobs;
	}

	public void setTakenJobs(List<Job> takenJobs) {
		this.takenJobs = takenJobs;
	}
	
	public User getUserWithLicense() {
		return userWithLicense;
	}

	public void setUserWithLicense(User userWithLicense) {
		this.userWithLicense = userWithLicense;
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
	};
	
	
	
	
	
}
