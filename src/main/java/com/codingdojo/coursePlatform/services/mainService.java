package com.codingdojo.coursePlatform.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.coursePlatform.models.Course;
import com.codingdojo.coursePlatform.models.User;
import com.codingdojo.coursePlatform.repositories.CourseRepository;
import com.codingdojo.coursePlatform.repositories.UserRepository;

@Service
@Transactional
public class mainService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private CourseRepository courseRepo;


	public User registerUser(@Valid User user) {
        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashed);
        return userRepo.save(user);
    }

	public boolean authenticateUser(String email, String password) {
        User user = userRepo.findByEmail(email);
        if(user == null) {
            return false;
        } else {
            if(BCrypt.checkpw(password, user.getPassword())) {
                return true;
            } else {
                return false;
            }
        }
	}

	public User findByEmail(String email) {
        return userRepo.findByEmail(email);
	}

	public User findUserById(Long userId) {
		Optional<User> optionalUser = userRepo.findById(userId);
		if (optionalUser.isPresent()) {
			return optionalUser.get();
		}else {
		return null;
	}}

	public List<Course> findAllCourses() {
		return courseRepo.findAll();
	}

	public Course createCourse(Course course) {
		return courseRepo.save(course);
		
	}

	public Course findCourseById(Long course_id) {
		return courseRepo.findCourseById(course_id);
	}

	public void updateUser(User student) {
		userRepo.save(student);
		
	}

	public void deleteCourse(Long course_id) {
		courseRepo.deleteById(course_id);
	}

	public Course updateCourse(Course course) {
		return this.courseRepo.save(course);
	}

	public List<Course> findAllCoursesOrderByCountAsc() {
		return courseRepo.findByOrderByCountAsc();
	}

	public List<Course> findAllCoursesOrderByCountDesc() {
		return courseRepo.findByOrderByCountDesc();
	}

	
}
