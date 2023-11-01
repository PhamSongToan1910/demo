package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Tests;
import java.util.List;
import java.util.Optional;


public interface TestRepository extends JpaRepository<Tests, Long>{

	Optional<Tests> findByName(String name);
}
