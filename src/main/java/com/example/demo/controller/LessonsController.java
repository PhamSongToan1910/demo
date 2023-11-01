package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo.DTO.LessonsDTO;
import com.example.demo.model.DataCourses;
import com.example.demo.model.Lessons;
import com.example.demo.repository.LessonRepository;
import com.example.demo.service.LessonsService;
import com.example.demo.serviceImpl.LessonsServiceImpl;

@RestController
@RequestMapping("api/v1/lesson")
public class LessonsController {

	@Autowired
	private LessonsService lessonsService;
	
	@Autowired
	private LessonRepository lessonRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("")
	public ResponseEntity<List<LessonsDTO>> getAll(){
		return new ResponseEntity<>(this.lessonsService.getAll(), HttpStatus.OK);
	}
	
	@GetMapping("/getLesson/{id}")
	public ResponseEntity<?> getByID(@PathVariable("id") Long ID){
		Optional<Lessons> lessons = this.lessonRepository.findById(ID);
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(lessons.get().getData().getType()))
				.body(lessons.get());
	}
	
	@PostMapping("/addLesson/{id}")
	public ResponseEntity<LessonsDTO> createLesson(@RequestBody LessonsDTO lessonsDTO, @PathVariable("id") Long ID){
		this.lessonsService.create(lessonsDTO, ID);
		return new ResponseEntity<>(lessonsDTO, HttpStatus.OK);
	}
	
	@DeleteMapping("/delLesson/{id}")
	public ResponseEntity<LessonsDTO> deleteLesson(@PathVariable("id") Long ID) {
		return new ResponseEntity<>(this.lessonsService.DeleteLesson(ID), HttpStatus.OK);
	}
	
	@PostMapping("/addData/{id}")
	public ResponseEntity<LessonsDTO> addData(@ModelAttribute("newFile") MultipartFile newFile, @PathVariable("id") Long ID){
		LessonsDTO lessonsDTO = this.lessonsService.AddDataCoursesToLessons(newFile, ID);
		Lessons lessons = modelMapper.map(lessonsDTO, Lessons.class);
		this.lessonRepository.save(lessons);
		return new ResponseEntity<>(lessonsDTO, HttpStatus.CREATED);
	}
}
