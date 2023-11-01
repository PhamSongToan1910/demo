package com.example.demo.model;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lessons")
public class Lessons {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Long ID;
	
	@Column(name = "name")
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "courses_id") 
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
	private Courses courses;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dataCourse_id", referencedColumnName = "id")
	private DataCourses data;
	
	@Column(name = "create_date")
	private Date createDate;
	
	@Column(name = "update_date")
	private Date updateDate;
	
	@Column(name = "create_by")
	private Long createBy;
	
	@Column(name = "update_by")
	private Long updateBy;
	
	@Column(name = "status")
	private Boolean status;
}
