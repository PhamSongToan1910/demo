package com.example.demo.DTO;

import java.util.Date;

import lombok.Data;

@Data
public class DataCoursesDTO {

	private Long ID;
	
	private String name;
	
	private String type;
	
	private byte[] data;
	
	private Date createDate;
	
	private Date UpdateDate;
	
	private Long createBy;
	
	private Long updateBy;
	
	private Boolean status;
	
	private Long lessons;

	public DataCoursesDTO(String name, String type, byte[] data, Date createDate, Date updateDate, Long createBy,
			Long updateBy, Boolean status, Long lessons) {
		this.name = name;
		this.type = type;
		this.data = data;
		this.createDate = createDate;
		UpdateDate = updateDate;
		this.createBy = createBy;
		this.updateBy = updateBy;
		this.status = status;
		this.lessons = lessons;
	}
	
	
}
