package com.example.demo.DTO;

import java.util.Date;

import com.example.demo.model.Courses;
import com.example.demo.model.DataCourses;

import lombok.Data;


@Data
public class LessonsDTO {

	private Long ID;
	
	private String name;
	
	private Long courses;
	
	private DataCourses data;
	
	private Date createDate;
	
	private Date UpdateDate;
	
	private Long createBy;
	
	private Long updateBy;
	
	private Boolean status;
}
