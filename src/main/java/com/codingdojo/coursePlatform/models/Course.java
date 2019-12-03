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
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="courses")
public class Course {
	
	
	
//----------------------------------------------------------------
//			  		Attributes
//---------------------------------------------------------------- 
	
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Size(min=3,message="Name must be more than 2 characters!")
    private String name;
    @Size(min=4,message="Name must be more than 3 characters!")
    private String instructor;
    @Min(1)
    private Integer capacity;
    private Integer count = 0;
    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "users_courses", 
            joinColumns = @JoinColumn(name = "course_id"), 
            inverseJoinColumns = @JoinColumn(name = "user_id")
        )     
        private List<User> students;

	
	
//----------------------------------------------------------------
//  		  		Constructors
//---------------------------------------------------------------- 
    
	public Course() {
	}

	public Course(Long id, String name,
			String instructor, Integer count,
			Integer capacity, Date createdAt, Date updatedAt, List<User> students) {
		this.id = id;
		this.name = name;
		this.instructor = instructor;
		this.capacity = capacity;
		this.count = count;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.students = students;
	}

	
	
//----------------------------------------------------------------
//			  		Getters and Setters
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

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
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

	public List<User> getStudents() {
		return students;
	}

	public void setStudents(List<User> students) {
		this.students = students;
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
