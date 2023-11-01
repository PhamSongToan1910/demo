package com.example.demo.service;

import java.util.List;

import com.example.demo.DTO.CoursesDTO;
import com.example.demo.DTO.PersonDTO;

public interface CoursesService extends BaseService<CoursesDTO, Long>{

	CoursesDTO deleteCourse(Long ID);
	 
	CoursesDTO addPersonToCourses(Long ID, PersonDTO person);
	
	List<CoursesDTO> getCoursesTeached(Long ID);
	
	List<CoursesDTO> getCoursesStudied(Long ID);
	
	CoursesDTO updateTeacher(Long id, PersonDTO person);
	
	CoursesDTO registerCourse(Long id, PersonDTO person);
}
