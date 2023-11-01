package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Questions;

public interface QuestionsRepository extends JpaRepository<Questions, Long>{

}
