package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.aspectj.apache.bcel.generic.RET;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.CoursesDTO;
import com.example.demo.DTO.LessonsDTO;
import com.example.demo.DTO.PersonDTO;
import com.example.demo.exception.CoursesNotFoundException;
import com.example.demo.model.Courses;
import com.example.demo.model.Person;
import com.example.demo.repository.CoursesRepository;
import com.example.demo.service.CoursesService;

import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("api/v1/courses")
public class CoursesController {

	@Autowired
	private CoursesRepository coursesRepository;
	
	@Autowired
	private CoursesService coursesService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping("/addCourse")
	public ResponseEntity<CoursesDTO> addCoursesDTO(@RequestBody CoursesDTO coursesDTO){
		this.coursesService.create(coursesDTO);
		return new ResponseEntity<>(coursesDTO, HttpStatus.CREATED);
	}
	
	@GetMapping("")
	public ResponseEntity<List<CoursesDTO>> getAll(){
		List<CoursesDTO> listCourses = this.coursesService.getAll();
		return new ResponseEntity<>(listCourses, HttpStatus.OK);
	}
	
	@PutMapping("/changeTeacher/{id}")
	public ResponseEntity<CoursesDTO> ChangeTeacher(@PathVariable("id") Long id, PersonDTO person){
		return new ResponseEntity<>(this.coursesService.updateTeacher(id, person), HttpStatus.OK);
	}
	
	@PostMapping("/registerCourse/{id}")
	public ResponseEntity<CoursesDTO> registerCourse(@PathVariable("id") Long id, @RequestBody PersonDTO personDTO){
		CoursesDTO coursesDTO = this.coursesService.registerCourse(id, personDTO);
		return new ResponseEntity<>(coursesDTO, HttpStatus.CREATED);
	}
}
