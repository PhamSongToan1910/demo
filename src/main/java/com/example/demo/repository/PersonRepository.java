package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{
	
	@Query(value = "SELECT p FROM Person p WHERE p.status = true")
	List<Person> ListPersons();
	
	Person findByUsername(String username);
}
