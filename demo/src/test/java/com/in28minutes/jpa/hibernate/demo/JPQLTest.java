package com.in28minutes.jpa.hibernate.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.in28minutes.jpa.hibernate.demo.entity.Course;
import com.in28minutes.jpa.hibernate.demo.repository.CourseRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class JPQLTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EntityManager em;
	
	@Test
	public void jpql_basic() {
		Query query = em.createQuery("select c from Course c");
		List resultList = query.getResultList(); // Lookout for entity name-> ["Course" != "course"]
		logger.info("select c from course c ->{}",resultList);
	}
	
	@Test
	public void jpql_Typed_basic() {
		TypedQuery<Course> typedQuery = em.createQuery("select c from Course c", Course.class);
		List<Course> resultList = typedQuery.getResultList();
		logger.info("Typed --  select c from course c ->{}", resultList);
	}
	
	@Test
	public void jpql_where_basic() {
		TypedQuery<Course> typedQuery = em.createQuery("select c from Course c where name like '%10 steps'", Course.class);
		List<Course> resultList = typedQuery.getResultList();
		logger.info("select c from Course c where name like '%10 steps ->{}", resultList); 
	}

}
