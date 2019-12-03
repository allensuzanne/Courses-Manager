package com.codingdojo.coursePlatform.models;

import java.security.Timestamp;
import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.hibernate.annotations.GenerationTime;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="users_courses")
public class UserCourse {

	
	
//----------------------------------------------------------------
//		  		Attributes
//---------------------------------------------------------------- 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User student;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="course_id")
    private Course registered_course;
    
	
	
//----------------------------------------------------------------
//  	  		Constructors
//---------------------------------------------------------------- 

	public UserCourse() {
	}

	public UserCourse(Long id, Date createdAt, Date updatedAt, User student, Course registered_course) {
		this.id = id;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.student = student;
		this.registered_course = registered_course;
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

	public User getStudent() {
		return student;
	}

	public void setStudent(User student) {
		this.student = student;
	}

	public Course getRegistered_course() {
		return registered_course;
	}

	public void setRegistered_course(Course registered_course) {
		this.registered_course = registered_course;
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
