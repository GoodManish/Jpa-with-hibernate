package com.in28minutes.jpa.hibernate.demo.repository;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.in28minutes.jpa.hibernate.demo.entity.Course;

@Repository
@Transactional
public class CourseRepository {
	
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
}
