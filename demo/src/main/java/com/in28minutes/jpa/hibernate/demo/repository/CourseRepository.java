package com.in28minutes.jpa.hibernate.demo.repository;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.in28minutes.jpa.hibernate.demo.entity.Course;

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
		
		if(course.getId()==null) {
			em.persist(course);
		}else {
			em.merge(course);
		}
		return course;
	}
	//em keep track of entities it bound to, which is in transactional environment 
	public void playWithEntityManager() {
		Course course1 = new Course("WebService in 10 steps");
		em.persist(course1);
		course1.setName(null);
		
//		Course course2 = new Course("ReactJS in 10 steps");
//		em.persist(course2);
//		
//		em.flush();
//		
//		course1.setName("WebService in 10 steps -- updated");
//		course2.setName("ReactJS in 10 steps -- updated");
//		
//		em.refresh(course1);
//		
//		em.flush();
	}
}
