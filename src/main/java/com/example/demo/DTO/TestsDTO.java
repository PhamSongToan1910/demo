package com.example.demo.DTO;

import java.util.Collection;
import java.util.Date;
import com.example.demo.model.Courses;
import lombok.Data;

@Data
public class TestsDTO {
	
	private Long ID;

	private String name;
	
	private Long courses;
	
	private Collection<QuestionsDTO> questions;
	
	private Date createDate;
	
	private Date UpdateDate;
	
	private Long createBy;
	
	private Long updateBy;
	
	private Boolean status;
}
