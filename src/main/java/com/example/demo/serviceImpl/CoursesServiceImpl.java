package com.example.demo.serviceImpl;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.CoursesDTO;
import com.example.demo.DTO.PersonDTO;
import com.example.demo.exception.AccountNotFoundException;
import com.example.demo.exception.CoursesNotFoundException;
import com.example.demo.model.Courses;
import com.example.demo.model.Person;
import com.example.demo.model.Student_courses;
import com.example.demo.repository.CoursesRepository;
import com.example.demo.repository.PersonRepository;
import com.example.demo.repository.StudentCourseRepository;
import com.example.demo.service.CoursesService;

@Service
public class CoursesServiceImpl implements CoursesService{

	@Autowired
	private CoursesRepository coursesRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private StudentCourseRepository studentCourseRepository;
	
	@Override
	public CoursesDTO getById(Long key) {
		// TODO Auto-generated method stub
		Optional<Courses> course = this.coursesRepository.findById(key);
		if(course.get() != null) {
			return modelMapper.map(course, CoursesDTO.class);
		}else {
			throw new AccountNotFoundException("Course isn't existed");
		}
	}

	@Override
	public List<CoursesDTO> getAll() {
		// TODO Auto-generated method stub
		List<Courses> listCourses = this.coursesRepository.findAll();
		List<CoursesDTO> listCoursesDTOs = new ArrayList<CoursesDTO>();
		if(!listCourses.isEmpty()) {
			for(Courses c: listCourses) {
				CoursesDTO coursesDTO = modelMapper.map(c, CoursesDTO.class);
				listCoursesDTOs.add(coursesDTO);
			}
            return listCoursesDTOs;
		}else {
			throw new CoursesNotFoundException("List courses is empty");
		}
	}

	@Override
	public CoursesDTO create(CoursesDTO type) {
		// TODO Auto-generated method stub
		List<Courses> listCoursesDTOs = this.coursesRepository.findAll();
		if(!listCoursesDTOs.isEmpty()) {
			Courses checkCourses = this.coursesRepository.getByName(type.getName());
			if(checkCourses != null) {
				throw new CoursesNotFoundException("Courses is existed");
			}else {
				System.out.println(type.getTeacher());
				Optional<Person> person = this.personRepository.findById(type.getTeacher().getID());
				PersonDTO personDTO = this.modelMapper.map(person.get(), PersonDTO.class);
				type.setTeacher(personDTO);
				Courses courses = this.modelMapper.map(type, Courses.class);
				this.coursesRepository.save(courses);
				return type;
			}
		}else {
			Optional<Person> person = this.personRepository.findById(type.getTeacher().getID());
			PersonDTO personDTO = this.modelMapper.map(person.get(), PersonDTO.class);
			type.setTeacher(personDTO);
			Courses courses = this.modelMapper.map(type, Courses.class);
			this.coursesRepository.save(courses);
			return type;
		}
	}

	@Override
	public CoursesDTO updateTeacher(Long id, PersonDTO person) {
		// TODO Auto-generated method stub
		Optional<Courses> course = this.coursesRepository.findById(id);
		if(course.get() != null) {
			Optional<Person> newTeacher = this.personRepository.findById(person.getID());
			if(!newTeacher.isEmpty()) {
				if(newTeacher.get().getRole().getName().contains("TEACHER")){
					course.get().setTeacher(newTeacher.get());
					CoursesDTO coursesDTO = this.modelMapper.map(course.get(), CoursesDTO.class);
					this.coursesRepository.save(course.get());
					return coursesDTO;
				}else {
					throw new AccountNotFoundException("User isn't teacher");
				}
			}else {
				throw new AccountNotFoundException("Teacher isn't existed");
			}
		}else {
			throw new AccountNotFoundException("User isn't existed");
		}
	}

	@Override
	public CoursesDTO deleteCourse(Long ID) {
		// TODO Auto-generated method stub
		Optional<Courses> course = this.coursesRepository.findById(ID);
		course.get().setStatus(false);
		this.coursesRepository.save(course.get());
		CoursesDTO coursesDTO = this.modelMapper.map(course.get(), CoursesDTO.class);
		return coursesDTO;
	}

	@Override
	public CoursesDTO addPersonToCourses(Long ID, PersonDTO person) {
		// TODO Auto-generated method stub
		Optional<Courses> course = this.coursesRepository.findById(ID);
		if(course.get() != null) {
			Optional<Person> checkPerson = this.personRepository.findById(person.getID());
			if(checkPerson.get() != null) {
				Optional<Student_courses> StudentCourse = this.studentCourseRepository.findByStudentIDAndCourseID(person.getID(), ID);
				if(StudentCourse.get() != null) {
					Person savedPerson = modelMapper.map(person, Person.class);
					this.studentCourseRepository.save(new Student_courses(savedPerson, course.get(), null));
				}
				else {
					throw new CoursesNotFoundException("This course is registered");
				}
				return this.modelMapper.map(course, CoursesDTO.class);
			}else {
				throw new AccountNotFoundException("User isn't existed");
			}
		}else {
			throw new AccountNotFoundException("Course isn't existed");
		}
	}

	@Override
	public List<CoursesDTO> getCoursesTeached(Long ID) {
		// TODO Auto-generated method stub
		Optional<Person> person = this.personRepository.findById(ID);
		if(!person.isEmpty()) {
			List<Courses> listCourses = (List<Courses>) person.get().getListCoursesTeached();
			Type listType = new TypeToken<List<CoursesDTO>>() {}.getType();
            return modelMapper.map(listCourses, listType);
		}else {
			throw new AccountNotFoundException("Account not found");
		}
	}

	@Override
	public List<CoursesDTO> getCoursesStudied(Long ID) {
		// TODO Auto-generated method stub
		Optional<Person> person = this.personRepository.findById(ID);
		if(person.get() != null) {
			Set<Student_courses> listStudentCourse = (Set<Student_courses>) person.get().getStudentCourses();
			List<CoursesDTO> listCourses = new ArrayList<CoursesDTO>();
			if(!listStudentCourse.isEmpty()) {
				for(Student_courses c: listStudentCourse) {
					Optional<Courses> courses = this.coursesRepository.findById(c.getCourseID().getID());
					CoursesDTO coursesDTO = modelMapper.map(courses.get(), CoursesDTO.class);
					listCourses.add(coursesDTO);
				}
			}
			return listCourses;
		}else {
			throw new AccountNotFoundException("Account not found");
		}
	}

	@Override
	public CoursesDTO registerCourse(Long id, PersonDTO person) {
		// TODO Auto-generated method stub
		Student_courses student_courses = this.studentCourseRepository.findByStudentIDAndCourseID(person.getID(), id).orElse(null);
		if(student_courses == null) {
			Optional<Courses> courses = this.coursesRepository.findById(id);
			if(courses.get() != null) {
				Person person2 = this.personRepository.findById(person.getID()).orElse(null);
				Student_courses student_courses2 = new Student_courses(person2, courses.get(), null);
//				person2.getStudentCourses().add(student_courses2);
//				this.personRepository.save(person2);
//				courses.get().getStudentCourses().add(student_courses2);
//				this.coursesRepository.save(courses.get());
				this.studentCourseRepository.save(student_courses2);
				return modelMapper.map(courses.get(), CoursesDTO.class);
			}else {
				throw new CoursesNotFoundException("COurse not found");
			}
		}else {
			throw new AccountNotFoundException("Course is not existed or you registerd this course");
		}
	}
	

}
