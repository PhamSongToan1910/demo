package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Roles;
import java.util.List;
import java.util.Optional;


public interface RoleRepository extends JpaRepository<Roles, Integer>{
	Optional<Roles> findByName(String name);
}
