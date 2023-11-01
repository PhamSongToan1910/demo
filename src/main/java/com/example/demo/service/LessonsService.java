package com.example.demo.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.DTO.DataCoursesDTO;
import com.example.demo.DTO.LessonsDTO;

public interface LessonsService {
	LessonsDTO getByID(Long ID);
	
	List<LessonsDTO> getAll();
	
	LessonsDTO create(LessonsDTO lesson, Long courseID);
	
	LessonsDTO DeleteLesson(Long ID);
	
	LessonsDTO AddDataCoursesToLessons(MultipartFile data, Long id);
	
}
