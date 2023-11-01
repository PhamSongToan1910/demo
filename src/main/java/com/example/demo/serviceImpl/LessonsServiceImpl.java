package com.example.demo.serviceImpl;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.DTO.CoursesDTO;
import com.example.demo.DTO.DataCoursesDTO;
import com.example.demo.DTO.LessonsDTO;
import com.example.demo.exception.CoursesNotFoundException;
import com.example.demo.exception.LessonsNotFoundException;
import com.example.demo.model.Courses;
import com.example.demo.model.DataCourses;
import com.example.demo.model.Lessons;
import com.example.demo.repository.CoursesRepository;
import com.example.demo.repository.LessonRepository;
import com.example.demo.service.LessonsService;

import jakarta.websocket.server.ServerEndpoint;

@Service
public class LessonsServiceImpl implements LessonsService{

	@Autowired
	private LessonRepository lessonRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CoursesRepository coursesRepository;
	
	@Autowired
	private DataCourseServiceImpl dataCourseServiceImpl;
	
	@Override
	public List<LessonsDTO> getAll() {
		// TODO Auto-generated method stub
		List<Lessons> listLessons = this.lessonRepository.findAll();
		Type listType = new TypeToken<List<LessonsDTO>>() {}.getType();
        return modelMapper.map(listLessons, listType);
	}

	@Override
	public LessonsDTO getByID(Long ID) {
		// TODO Auto-generated method stub
		Optional<Lessons> lesson = this.lessonRepository.findById(ID);
		return modelMapper.map(lesson.get(), LessonsDTO.class);
	}

	@Override
	public LessonsDTO create(LessonsDTO lesson, Long courseID) {
		// TODO Auto-generated method stub
		Optional<Courses> course = this.coursesRepository.findById(courseID);
		if(course.get() != null) {
			lesson.setCourses(course.get().getID());
			Lessons lessons = modelMapper.map(lesson, Lessons.class);
			this.lessonRepository.save(lessons);
			return lesson;
		}else {
			throw new CoursesNotFoundException("Can't find this course");
		}
	}

	@Override
	public LessonsDTO DeleteLesson(Long ID) {
		// TODO Auto-generated method stub
		Optional<Lessons> lesson = this.lessonRepository.findById(ID);
		lesson.get().setStatus(false);
		return modelMapper.map(lesson, LessonsDTO.class);
	}

	@Override
	public LessonsDTO AddDataCoursesToLessons(MultipartFile data, Long id) {
		// TODO Auto-generated method stub
		Optional<Lessons> lessons = this.lessonRepository.findById(id);
		if(lessons.get() != null) {
			LessonsDTO lessonsDTO = modelMapper.map(lessons, LessonsDTO.class);
			DataCourses dataCourses = this.dataCourseServiceImpl.UploadFile(data, lessonsDTO);
			lessonsDTO.setData(dataCourses);
			return lessonsDTO;
		}else {
			throw new LessonsNotFoundException("Lesson not found");
		}
	}

}
