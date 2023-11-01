package com.example.demo.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "answers")
public class Answers {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Long ID;
	
	@Column(name = "content")
	private String content;
	
	@ManyToOne
	@JoinColumn(name = "questions_id") //thông qua khóa ngoại questions_id
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
	private Questions questions;
	
	@Column(name = "result")
	private Boolean result;
	
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
	
	
}
