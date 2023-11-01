package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Roles;
import com.example.demo.repository.RoleRepository;

@RestController
@RequestMapping("api/v1/roles")
public class RoleController {

	@Autowired
	private RoleRepository roleRepository;
	
	@GetMapping("")
	public ResponseEntity<List<Roles>> getAll(){
		return new ResponseEntity<>(this.roleRepository.findAll(), HttpStatus.OK);
	}
}
