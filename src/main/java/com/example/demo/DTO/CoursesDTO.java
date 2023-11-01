package com.example.demo.DTO;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import lombok.Data;

@Data
public class CoursesDTO {
	private Long ID;

	private String name;

	private Date timeStart;

	private Date timeEnd;

	private PersonDTO teacher;

	private Set<LessonsDTO> lessons;

	private Set<TestsDTO> tests;

	private Date createDate;

	private Date UpdateDate;

	private Long createBy;

	private Long updateBy;

	private Boolean status;
}
