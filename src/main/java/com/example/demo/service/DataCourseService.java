package com.example.demo.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.DTO.LessonsDTO;
import com.example.demo.model.DataCourses;

public interface DataCourseService {
	
	List<DataCourses> getAllFile();
	
	DataCourses getFileByID(Long ID);
	
	DataCourses UploadFile(MultipartFile image, LessonsDTO lesson);
}
