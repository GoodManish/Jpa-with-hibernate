package com.in28minutes.database.databasedemo.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.in28minutes.database.databasedemo.entity.Person;

@Repository
public class PersonJdbcDao {
	@Autowired
	JdbcTemplate jdbcTemplate;

	class PersonRowMapper implements RowMapper<Person> {

		@Override
		public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
			Person person = new Person();
			person.setId(rs.getInt("id"));
			person.setName(rs.getString("name"));
			person.setLocation(rs.getString("location"));
			person.setBirthDate(rs.getDate("birth_date"));
			return person;
		}

	}

	// select * from person
	public List<Person> findAll() {
		return jdbcTemplate.query("select * from person", new PersonRowMapper());
	}

	// select * from person by Id
	public Person findById(int id) {
		return jdbcTemplate.queryForObject("select * from person where id=?", new Object[] { id },
				new BeanPropertyRowMapper<Person>(Person.class));
	}

	// select * from person by Id
	public int deleteById(int id) {
		return jdbcTemplate.update("delete from person where id=?", new Object[] { id });
	}

	// insert
	public int insert(Person person) {
		return jdbcTemplate.update("insert into person (id, name, location, birth_date)" + "values(?, ?, ?, ?)",
				new Object[] { person.getId(), person.getName(), person.getLocation(), person.getBirthDate() });
	}

	// update
	public int update(Person person) {
		return jdbcTemplate.update("update person " + "set name = ?, location = ?, birth_date = ? " + "where id = ? ",
				new Object[] { person.getName(), person.getLocation(), person.getBirthDate(), person.getId() });
	}
}
