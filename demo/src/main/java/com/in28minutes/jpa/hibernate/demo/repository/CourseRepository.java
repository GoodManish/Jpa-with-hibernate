package com.in28minutes.jpa.hibernate.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.in28minutes.jpa.hibernate.demo.entity.Course;
import com.in28minutes.jpa.hibernate.demo.entity.Review;

@Repository
@Transactional
public class CourseRepository {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;

	public Course findById(Long id) {
		return em.find(Course.class, id);
	}

	public void deleteById(Long id) {
		em.remove(findById(id));
	}

	public Course save(Course course) {

		if (course.getId() == null) {
			em.persist(course);
		} else {
			em.merge(course);
		}
		return course;
	}

	// em keep track of entities it bound to, which is in transactional environment
	public void playWithEntityManager() {
		Course course1 = new Course("WebService in 10 steps");
		em.persist(course1);

		Course course2 = findById(10001L);
		course2.setName("JPA in 50 steps --- updated");

	}

	public void addHardCodedReviewsForCourse() {
		// get course 10003
		Course course = findById(10003L);
		logger.info("course.reviews() -> {}", course.getReviews());

		// add 2 review
		Review review1 = new Review("Great Course", "5");
		Review review2 = new Review("Hatsoff !!!", "5");
		course.addReview(review1);
		review1.setCourse(course);

		course.addReview(review2);
		review2.setCourse(course);

		// save to database
		em.persist(review1);
		em.persist(review2);

	}

	public void addReviewsForCourse(Long courseId, List<Review> reviews) {
		// get course 10003
		Course course = findById(courseId);
		logger.info("course.reviews() -> {}", course.getReviews());

		// add list of review
		for (Review review : reviews) {
			course.addReview(review);
			review.setCourse(course);

			// save to database
			em.persist(review);
		}

	}
}
