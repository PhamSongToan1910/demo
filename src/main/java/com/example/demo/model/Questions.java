package com.example.demo.model;

import java.util.Collection;
import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name = "questions")
public class Questions {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Long ID;
	
	@Column(name = "content")
	private String content;
	
	@ManyToOne 
    @JoinColumn(name = "tests_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
	private Tests tests;
	
	@OneToMany(mappedBy = "questions", fetch = FetchType.LAZY)
	@EqualsAndHashCode.Exclude
    @ToString.Exclude
	private Collection<Answers> answers;
	
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
