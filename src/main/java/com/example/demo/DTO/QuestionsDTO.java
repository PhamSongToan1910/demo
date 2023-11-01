package com.example.demo.DTO;

import java.util.Collection;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionsDTO {

	private Long ID;

	private String content;
	
	private Long tests;
	
	private Collection<AnswersDTO> answers;
	
	private Date createDate;
	
	private Date UpdateDate;
	
	private Long createBy;
	
	private Long updateBy;
	
	private Boolean status;
}
