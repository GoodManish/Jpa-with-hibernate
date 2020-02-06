package com.in28minutes.jpa.hibernate.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
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
import com.in28minutes.jpa.hibernate.demo.repository.CourseRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class NativeQueriesTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EntityManager em;
	
	@Test
	public void native_query_basic() {
		Query query = em.createNativeQuery("select * from course", Course.class);
		List resultList = query.getResultList(); 
		logger.info("native query select * from course ->{}",resultList);
	}
	
	@Test
	public void native_query_with_PARAMETER() {
		Query query = em.createNativeQuery("select * from course where id=?", Course.class);
		query.setParameter(1, 10001L);
		List resultList = query.getResultList(); 
		logger.info("native query select * from course where id=? -> {}",resultList);
	}
	
	@Test
	public void native_query_with_NAMED_PARAMETER() {
		Query query = em.createNativeQuery("select * from course where id= :id", Course.class);
		query.setParameter("id", 10001L);
		List resultList = query.getResultList(); 
		logger.info("native query select * from course where id= :id -> {}",resultList);
	}

	@Test
	@Transactional
	public void native_query_to_update() {
		Query query = em.createNativeQuery("Update COURSE set last_updated_date = sysdate()", Course.class);
		int noOfRowsUpdated = query.executeUpdate(); // we directly calling query update.. so if @Transactional is not annotated over this method, then it will throw transaction error.
		logger.info("native update query-- noOfRowsUpdated -> {}",noOfRowsUpdated);
	}
}
