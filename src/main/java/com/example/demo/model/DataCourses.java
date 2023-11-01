package com.example.demo.model;

import java.util.Date;

import com.example.demo.DTO.LessonsDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dataCourses")
public class DataCourses {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Long ID;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "data")
	@Lob
	private byte[] data;
	
	@Column(name = "create_date")
	private Date createDate;
	
	@Column(name = "update_date")
	private Date UpdateDate;
	
	@Column(name = "create_by")
	private Long createBy;
	
	@Column(name = "update_by")
	private Long updateBy;
	
	@Column(name = "status")
	private Boolean status;
	
	@OneToOne(mappedBy = "data")
	private Lessons lessons;
	
	
}
