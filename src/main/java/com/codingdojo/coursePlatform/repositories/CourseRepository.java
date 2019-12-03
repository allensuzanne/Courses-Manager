package com.codingdojo.coursePlatform.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.coursePlatform.models.Course;

@Repository
public interface CourseRepository extends CrudRepository <Course, Long> {
	
	List<Course> findAll();

	Course findCourseById(Long course_id);

	List<Course> findByOrderByCountAsc();

	List<Course> findByOrderByCountDesc();

	Course findByIdOrderByCreatedAtAsc(Long course_id);

	Course findByIdOrderByCreatedAtDesc(Long course_id);

}
