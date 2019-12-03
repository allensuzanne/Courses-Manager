package com.codingdojo.coursePlatform.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.coursePlatform.models.UserCourse;

@Repository
public interface UserCourseRepository extends CrudRepository <UserCourse, Long>{

}
