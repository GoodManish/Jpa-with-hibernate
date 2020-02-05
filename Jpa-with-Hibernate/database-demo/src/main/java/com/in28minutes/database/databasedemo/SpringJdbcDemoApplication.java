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

//@SpringBootApplication
public class SpringJdbcDemoApplication implements CommandLineRunner {
	@Autowired
	PersonJdbcDao dao;
	
	private Logger logger = LoggerFactory.getLogger(SpringJdbcDemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringJdbcDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	logger.info("All users -> {}", dao.findAll());
	logger.info("Find User By Id 10001  -> {}", dao.findById(10001));
	
	logger.info("Deleting 10002 - Number of rows deleted -> {}", dao.deleteById(10002));
	
	logger.info("Inserting person with id 10005 - Number of rows inserted -> {}", dao.insert(new Person(10005, "Manish","Bangalore", new Date())));

	logger.info("updating person with id 10005 - Number of rows updated -> {}", dao.update(new Person(10005, "Manish","USA", new Date())));
	}

}
