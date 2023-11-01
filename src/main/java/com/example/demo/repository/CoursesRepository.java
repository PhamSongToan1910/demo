package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Courses;
import java.util.List;


public interface CoursesRepository extends JpaRepository<Courses, Long>{
	Optional<Courses> findByName(String name);
	
	Courses getByName(String name);
}
