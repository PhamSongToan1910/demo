package com.example.demo.config.mapper;

import org.aspectj.weaver.ast.Test;
import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.NamingConventions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.demo.DTO.AnswersDTO;
import com.example.demo.DTO.CoursesDTO;
import com.example.demo.DTO.DataCoursesDTO;
import com.example.demo.DTO.LessonsDTO;
import com.example.demo.DTO.PersonDTO;
import com.example.demo.DTO.QuestionsDTO;
import com.example.demo.DTO.TestsDTO;
import com.example.demo.config.convert.StringToRolesConverter;
import com.example.demo.model.Answers;
import com.example.demo.model.DataCourses;
import com.example.demo.model.Lessons;
import com.example.demo.model.Person;
import com.example.demo.model.Questions;
import com.example.demo.model.Tests;



@Configuration
public class ModelMapperConfig {
	
	@Bean
	public StringToRolesConverter stringToRolesConverter() {
		return new StringToRolesConverter();
	}
	
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setSourceNamingConvention(NamingConventions.NONE)
				.setDestinationNamingConvention(NamingConventions.NONE);
		// map entity to dto
		
		modelMapper.createTypeMap(Person.class, PersonDTO.class)
			.addMapping(src -> src.getRole().getName(), PersonDTO::setRole);

		modelMapper.createTypeMap(Answers.class, AnswersDTO.class)
			.addMapping(src -> src.getQuestions().getContent(), AnswersDTO::setContent);
		
		modelMapper.createTypeMap(Questions.class, QuestionsDTO.class)
			.addMapping(src -> src.getTests().getID(), QuestionsDTO::setTests);
		
		modelMapper.createTypeMap(DataCourses.class, DataCoursesDTO.class)
			.addMapping(src -> src.getLessons().getID(), DataCoursesDTO::setLessons);
		
		modelMapper.createTypeMap(Lessons.class, LessonsDTO.class)
			.addMapping(src -> src.getCourses().getID(), LessonsDTO::setCourses);
		
		modelMapper.createTypeMap(Tests.class, TestsDTO.class)
			.addMapping(src -> src.getCourses().getID(), TestsDTO::setCourses);

		//map dto to entity
		
		modelMapper.createTypeMap(PersonDTO.class, Person.class)
			.addMappings(m -> m.using(stringToRolesConverter()).map(PersonDTO::getRole, Person::setRole));
		
		modelMapper.createTypeMap(AnswersDTO.class, Answers.class)
			.addMapping(AnswersDTO::getQuestion, (dest, value) -> dest.getQuestions().setContent(value != null ? value.toString() : null));
		
		modelMapper.createTypeMap(QuestionsDTO.class, Questions.class)
			.addMapping(QuestionsDTO::getTests, (dest, value) -> dest.getTests().setID(value != null ? Long.parseLong(value.toString()) : null));
		
		modelMapper.createTypeMap(DataCoursesDTO.class, DataCourses.class)
			.addMapping(DataCoursesDTO::getLessons, (dest, value) -> dest.getLessons().setID(value != null ? Long.parseLong(value.toString()) : null));
		
		modelMapper.createTypeMap(LessonsDTO.class, Lessons.class)
			.addMapping(LessonsDTO::getCourses, (dest, value) -> dest.getCourses().setID(value != null ? Long.parseLong(value.toString()) : null));
		
		modelMapper.createTypeMap(TestsDTO.class, Tests.class)
			.addMapping(TestsDTO::getCourses, (dest, value) -> dest.getCourses().setID(value != null ? Long.parseLong(value.toString()) : null));
		
		return modelMapper;
	}
}
