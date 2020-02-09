package com.in28minutes.jpa.hibernate.demo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.in28minutes.jpa.hibernate.demo.entity.Course;
import com.in28minutes.jpa.hibernate.demo.entity.FullTimeEmployee;
import com.in28minutes.jpa.hibernate.demo.entity.PartTimeEmployee;
import com.in28minutes.jpa.hibernate.demo.entity.Review;
import com.in28minutes.jpa.hibernate.demo.entity.Student;
import com.in28minutes.jpa.hibernate.demo.repository.CourseRepository;
import com.in28minutes.jpa.hibernate.demo.repository.EmployeeRepository;
import com.in28minutes.jpa.hibernate.demo.repository.StudentRepository;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner{
	
	private Logger logger = LoggerFactory.getLogger(DemoApplication.class);

	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	StudentRepository studentRepository ;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
//		List<Review> reviews=new ArrayList<>();
//		
//		Review review1 = new Review("Great Course", "5");
//		Review review2 = new Review("Hatsoff !!!", "5");
//		reviews.add(review1);
//		reviews.add(review2);
		
		// repository.playWithEntityManager();
//		studentRepository.saveStudentWithPassport();
//		courseRepository.addReviewsForCourse();
//		courseRepository.addReviewsForCourse(10003L, reviews);
		
//		studentRepository.insertHardcodedStudentAndCourse();
//		studentRepository.insertStudentAndCourse(new Student("JacK"), new Course("PYTHON in 100 Steps"));
		
		employeeRepository.insert(new FullTimeEmployee("Jack", new BigDecimal("10000")));
		employeeRepository.insert(new PartTimeEmployee("Jill", new BigDecimal("50")));
		logger.info("All employees ---> {} ", employeeRepository.retrieveAllEmployees());
	}
	
}
