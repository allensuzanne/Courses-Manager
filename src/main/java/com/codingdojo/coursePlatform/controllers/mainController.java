package com.codingdojo.coursePlatform.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.codingdojo.coursePlatform.models.Course;
import com.codingdojo.coursePlatform.models.User;
import com.codingdojo.coursePlatform.services.mainService;
import com.codingdojo.coursePlatform.validator.UserValidator;

@Controller
public class mainController {
	
	@Autowired
	private mainService mainServ;
	
	@Autowired
	private UserValidator userValidator;
	
	//----------------------------------------------------------------
	// LoginRegistration Page - Get Route
	//----------------------------------------------------------------
	
	@GetMapping("/")
	  public String registerForm(@ModelAttribute("user") User user) { 
        return "index.jsp";
    }

	//----------------------------------------------------------------
	// Registration Page - Post Route
	//----------------------------------------------------------------
    @PostMapping(value="/registration")
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
    	userValidator.validate(user, result); 
    	if(result.hasErrors()) {
    		return "index.jsp";
    	}else{
    	User u = mainServ.registerUser(user);
    	session.setAttribute("userId", u.getId());
    	return "redirect:/courses";
    	}
    }
    
	//----------------------------------------------------------------
	// Login Page - Post Route
	//----------------------------------------------------------------
    @PostMapping(value="/login")
    public String loginUser(@RequestParam("email") String email, @ModelAttribute("user") User user, Model model, @RequestParam("password") String password, HttpSession session) {
        boolean isAuthenticated = mainServ.authenticateUser(email, password);
        if(isAuthenticated) {
        	User u =mainServ.findByEmail(email); 
        	session.setAttribute("userId", u.getId() );
        	return "redirect:/courses";
        }else {
        	model.addAttribute("error", "Invalid credentials. Try again!");
        	return "index.jsp";
        }
    }
    
	
	//----------------------------------------------------------------
	// Logout Page - Get Route
	//----------------------------------------------------------------
    @GetMapping("/logout")
    public String logout(HttpSession session) {
    	session.invalidate();
    	return "redirect:/";
    }
    
	//----------------------------------------------------------------
	// Courses - Get Route
	//----------------------------------------------------------------

    @GetMapping("/courses")
    public String courses(HttpSession session, Model model) {
    	Long userId = (Long) session.getAttribute("userId");
    	User u = mainServ.findUserById(userId);
    	model.addAttribute("user", u);
    	List<Course> courses = mainServ.findAllCourses();
    	model.addAttribute("courses", courses); 	
    	return "courses.jsp";

    }
	//----------------------------------------------------------------
	// Courses ASC - Get Route
	//----------------------------------------------------------------

    @GetMapping("/courses/asc")
    public String coursesAsc(HttpSession session, Model model) {
    	Long userId = (Long) session.getAttribute("userId");
    	User u = mainServ.findUserById(userId);
    	model.addAttribute("user", u);
    	List<Course> courses = mainServ.findAllCoursesOrderByCountAsc();
    	model.addAttribute("courses", courses); 	
    	return "courses.jsp";

    }
	//----------------------------------------------------------------
	// Courses Desc - Get Route
	//----------------------------------------------------------------

    @GetMapping("/courses/desc")
    public String coursesDesc(HttpSession session, Model model) {
    	Long userId = (Long) session.getAttribute("userId");
    	User u = mainServ.findUserById(userId);
    	model.addAttribute("user", u);
    	List<Course> courses = mainServ.findAllCoursesOrderByCountDesc();
    	model.addAttribute("courses", courses); 	
    	return "courses.jsp";

    }
    
	//----------------------------------------------------------------
	// Courses New- Get Route
	//----------------------------------------------------------------

    @GetMapping("/courses/new")
    public String coursesNew(HttpSession session, Model model, @ModelAttribute("course") Course course) {	
    	return "coursesNew.jsp";

    }
    
	//----------------------------------------------------------------
	// Courses Create- Post Route
	//----------------------------------------------------------------
    @PostMapping(value="/courses/create")
    public String createCourse(@Valid @ModelAttribute("course") Course course, BindingResult result, Model model, HttpSession session) {
    	if(result.hasErrors()) {
    		return "coursesNew.jsp";
    	}else {
    		mainServ.createCourse(course);
    		return "redirect:/courses/"+course.getId();
    	}
    }
    

    //----------------------------------------------------------------
    //Add Student to Course - Get Route - Add
    //----------------------------------------------------------------
    
    @GetMapping(value="/courses/{course_id}/addstudent")
    public String addStudent(@PathVariable("course_id")Long course_id, HttpSession session) {
    	User student = mainServ.findUserById((Long) session.getAttribute("userId"));
    	System.out.println("this studnet id is"+student.getId());
    	System.out.println("this studnet is"+student);
    	Course registered_course = mainServ.findCourseById(course_id);
    	System.out.println("this course id is"+registered_course.getId());
    	System.out.println("this course is"+registered_course);
		List<User> students = registered_course.getStudents();
		students.add(student);
		registered_course.setStudents(students);
		Integer count = registered_course.getCount();
		registered_course.setCount(count+1);
		mainServ.updateUser(student);
		return "redirect:/courses";
    }
    

    //----------------------------------------------------------------
    //Remove Student from Course - Get Route - Remove
    //----------------------------------------------------------------
    
    @GetMapping(value="/courses/{course_id}/remove")
    public String removeStudent(@PathVariable("course_id")Long course_id, HttpSession session) {
    	User student = mainServ.findUserById((Long) session.getAttribute("userId"));
    	Course registered_course = mainServ.findCourseById(course_id);
		List<User> students = registered_course.getStudents();
		students.remove(student);
		registered_course.setStudents(students);
		Integer count = registered_course.getCount();
		registered_course.setCount(count-1);
		mainServ.updateUser(student);
		return "redirect:/courses/"+registered_course.getId();
    }
    

    //----------------------------------------------------------------
    //Delete Course - Post Route
    //----------------------------------------------------------------
      
      @RequestMapping("/courses/{course_id}/delete")
      public String deleteCourse(@PathVariable("course_id") Long course_id) {
      	mainServ.deleteCourse(course_id);
      	return "redirect:/courses";
      }
    
      
  
      //----------------------------------------------------------------
      //Show Course- Get Route
      //----------------------------------------------------------------
      @GetMapping("/courses/{course_id}")
      public String showCourseAsc(Model model, @PathVariable("course_id")Long course_id, HttpSession session) {
    	  Course course = mainServ.findCourseById(course_id);
    	  model.addAttribute("course", course);
      	  Long userId = (Long) session.getAttribute("userId");
       	  User u = mainServ.findUserById(userId);
      	  model.addAttribute("user", u);
    	  return "coursesId.jsp";
     }

     
      
      
      //----------------------------------------------------------------
      //Edit Course - GET Route
      //----------------------------------------------------------------
      
        @RequestMapping("/courses/{course_id}/edit")
        public String editCourse(@PathVariable("course_id") Long course_id, Model model) {
      	Course course = mainServ.findCourseById(course_id);
        	model.addAttribute("course", course);
        	return "coursesEdit.jsp";
        } 
      
        
        //----------------------------------------------------------------
        //Edit Event - Post Route
        //----------------------------------------------------------------
        
        @PutMapping("/courses/{id}/update")
        public String updateCourse(Model model, @PathVariable("id")Long id, @Valid @ModelAttribute("course") Course course, BindingResult result) {
            if (result.hasErrors()) {
                return "coursesEdit.jsp";
            } else {
                this.mainServ.updateCourse(course);
                return "redirect:/courses/"+course.getId();
            }
           }
        
        
        
}
