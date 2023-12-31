package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Lessons;
import java.util.List;
import java.util.Optional;


public interface LessonRepository extends JpaRepository<Lessons, Long>{
	Optional<Lessons> findByName(String name);
}
