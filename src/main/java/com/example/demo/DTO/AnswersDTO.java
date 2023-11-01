package com.example.demo.DTO;

import java.util.Date;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswersDTO {

	private Long ID;
	
	private String content;
	
	private String question;
	
	private Boolean result;
	
	private Date createDate;
	
	private Date UpdateDate;
	
	private Long createBy;
	
	private Long updateBy;
	
	private Boolean status;
}
