package com.example.demo.DTO;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {

	private Long ID;
	
	private String name;
	
	private String username;
	
	private String password;
	
	private String role;
	
	private Boolean status;
	
	private Date createDate;
	
	private Date UpdateDate;
	
	private Long createBy;
	
	private Long updateBy;
	
}
