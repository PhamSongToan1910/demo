package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.CoursesDTO;
import com.example.demo.DTO.PersonDTO;
import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;
import com.example.demo.service.CoursesService;
import com.example.demo.service.PersonService;

@RestController
@RequestMapping("api/v1/person")
public class PersonController {
	@Autowired
	private PersonService personService;
	
	@Autowired
	private CoursesService coursesService;
	
	@Autowired
	private PersonRepository personRepository;
	
	@GetMapping("")
	public ResponseEntity<List<PersonDTO>> getAllPerson(){
		List<PersonDTO> listPersons = this.personService.getAll();
		return new ResponseEntity<>(listPersons, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getPerson(@PathVariable("id") Long ID){
//		List<PersonDTO> listPersons = this.personService.getAll();
		Person person = this.personRepository.findById(ID).get();
		return ResponseEntity.ok()
				.body(person.getStudentCourses());
	}
	
	@PostMapping("/addPerson")
	public ResponseEntity<PersonDTO> addData(@RequestBody PersonDTO person){
		PersonDTO personDTO = this.personService.create(person);
		return new ResponseEntity<>(personDTO, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delPerson/{id}")
	public ResponseEntity<?> delData(@PathVariable("id") Long ID){
		PersonDTO savedPerson = this.personService.delete(ID);
		return new ResponseEntity<>(savedPerson, HttpStatus.OK);
	}
	
	@GetMapping("/getCourseTeached/{id}")
	public ResponseEntity<CoursesDTO> getCourseByID(@PathVariable("id") Long ID){
		return new ResponseEntity(this.coursesService.getCoursesTeached(ID), HttpStatus.OK);
	}
	
	@GetMapping("/getCourseStudied/{id}")
	public ResponseEntity<List<CoursesDTO>> getCourseStudiedByID(@PathVariable("id") Long ID){
		List<CoursesDTO> coursesDTO = this.coursesService.getCoursesStudied(ID);
		return new ResponseEntity(coursesDTO, HttpStatus.OK);
	}
}
