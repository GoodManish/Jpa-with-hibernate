package com.in28minutes.database.databasedemo;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.in28minutes.database.databasedemo.entity.Person;
import com.in28minutes.database.databasedemo.jdbc.PersonJdbcDao;
import com.in28minutes.database.databasedemo.jpa.PersonJpaRepository;

@SpringBootApplication
public class JpaDemoApplication implements CommandLineRunner {
	@Autowired
	PersonJpaRepository repo;
	
	private Logger logger = LoggerFactory.getLogger(JpaDemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(JpaDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("Find User By Id 10001  -> {}", repo.findById(10001));
		logger.info("Find User By Id 10004  -> {}",     repo.insert(new Person("Ishaani","Khagaria", new Date())));
		logger.info("Update person by Id 10001  -> {}", repo.update(new Person(10001, "Manish","USA", new Date())));
		logger.info("Update person without Id  -> {}",  repo.insert(new Person("Ishaan","USA", new Date())));
		repo.deleteById(10002);
		logger.info("Find all persons -> {}",  repo.findAll());
	
	/*
	  logger.info("Deleting 10002 - Number of rows deleted -> {}", dao.deleteById(10002));
	  logger.info("Inserting person with id 10005 - Number of rows inserted -> {}", dao.insert(new Person(10005, "Manish","Bangalore", new Date())));
	  logger.info("updating person with id 10005 - Number of rows updated -> {}", dao.update(new Person(10005, "Manish","USA", new Date())));
	*/
	}

}
