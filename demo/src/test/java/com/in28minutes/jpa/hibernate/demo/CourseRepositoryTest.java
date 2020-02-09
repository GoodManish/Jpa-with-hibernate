package com.in28minutes.jpa.hibernate.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.in28minutes.jpa.hibernate.demo.entity.Course;
import com.in28minutes.jpa.hibernate.demo.entity.Review;
import com.in28minutes.jpa.hibernate.demo.repository.CourseRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class CourseRepositoryTest {


	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CourseRepository repository;
	
	@Autowired
	EntityManager em;
	
	@Test
	public void findById_basic() {
		Course course = repository.findById(10001L);
		assertEquals("JPA in 50 steps", course.getName());
	}
	
	@Test
	@DirtiesContext 
	public void deleteById_basic() {
		repository.deleteById(10001L);
		assertNull(repository.findById(10001L));
	}
	
	@Test
	@DirtiesContext
	public void save_basic() {
		Course course = repository.findById(10001L);
		
		course.setName("JPA in 50 steps-UPDATED");
		
		repository.save(course);
		
		Course course1 = repository.findById(10001L);
		assertEquals("JPA in 50 steps-UPDATED", course1.getName());
	}
	
	@Test
	@DirtiesContext
	public void playWithEntityManagerTest() {
		repository.playWithEntityManager();
	}

	
	@Test
	@Transactional
	public void retrieveReviewWithCourseTest() {
		Course course = repository.findById(10001L);
		logger.info("{} -> ", course.getReviews());
	}
	
	@Test
	@Transactional
	public void retrieveCourseWithReviewTest() {
		Review review = em.find(Review.class, 50001L);
		logger.info("{} ", review.getCourse());
	}
}
