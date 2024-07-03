package com.nathanmarrow.chore_tracker.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;


@Entity
@Table(name="jobs")
public class Job {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message="Job needs a title")
	@Size(min=4, max=255, message = "Title must be at least 4 characters" )
	private String title;
	
	@NotEmpty(message="Job needs a description")
	@Size(min=11, max=255, message = "Description must have at least 11 characters")
	private String description;
	
	@NotEmpty(message="Job needs a location")
	private String location;
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="worker_id")
	private Worker jobTaker;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User jobOwner;
	
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
	
	public Job () {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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

	public User getJobOwner() {
		return jobOwner;
	}

	public void setJobOwner(User jobOwner) {
		this.jobOwner = jobOwner;
	}

	public Worker getJobTaker() {
		return jobTaker;
	}

	public void setJobTaker(Worker jobTaker) {
		this.jobTaker = jobTaker;
	}

	
}
