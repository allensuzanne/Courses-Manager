package com.codingdojo.coursePlatform.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="users")
public class User {
	
	
//----------------------------------------------------------------
//		  		Attributes
//---------------------------------------------------------------- 
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Size(min=3,message="Name must be more than 2 characters")
    private String name;
    @Email(message="Email must be valid")
    private String email;
    @Size(min=8, message="Password must be greater than 7 characters")
    private String password;
    @Transient
    private String passwordConfirmation;
    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
    		name="users_courses",
    		joinColumns = @JoinColumn(name = "user_id"),
    		inverseJoinColumns = @JoinColumn(name = "course_id")
    		)
    private List<Course> registered_courses;

	
//----------------------------------------------------------------
//  	  		Constructors
//---------------------------------------------------------------- 
    
	public User() {
	}

	public User(Long id, String name,
			String email,
			String password,
			String passwordConfirmation, Date createdAt, Date updatedAt, List<Course> registered_courses) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.passwordConfirmation = passwordConfirmation;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.registered_courses = registered_courses;
	}

	
	
//----------------------------------------------------------------
//		  		Getters and Setters
//---------------------------------------------------------------- 
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
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

	public List<Course> getRegistered_courses() {
		return registered_courses;
	}

	public void setRegistered_courses(List<Course> registered_courses) {
		this.registered_courses = registered_courses;
	}
    


	@PrePersist
	protected void onCreate(){
		this.createdAt = new Date();
	}
	
	@PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
	

}
