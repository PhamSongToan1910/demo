package com.example.demo.model;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

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
@Table(name = "courses")
public class Courses {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Long ID;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "time_start")
	private Date timeStart;
	
	@Column(name = "time_end")
	private Date timeEnd;
	
	@ManyToOne 
    @JoinColumn(name = "teacher_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
	private Person teacher;
	
//	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    // Quan hệ n-n với đối tượng ở dưới (Person) (1 khóa học có nhiều người học)
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
//
//    @JoinTable(name = "course_student", //Tạo ra một join Table tên là "course_student"
//            joinColumns = @JoinColumn(name = "course_id"),  // TRong đó, khóa ngoại chính là course_id trỏ tới class hiện tại (course)
//            inverseJoinColumns = @JoinColumn(name = "student_id") //Khóa ngoại thứ 2 trỏ tới thuộc tính ở dưới (students)
//    )
//	private Collection<Person> students;
//	
	@OneToMany(mappedBy = "courses", fetch = FetchType.EAGER)
	@EqualsAndHashCode.Exclude
    @ToString.Exclude
	private Collection<Lessons> lessons;
	
	@OneToMany(mappedBy = "courseID", fetch = FetchType.EAGER)
	private Set<Student_courses> studentCourses;
	
	@OneToMany(mappedBy = "courses", fetch = FetchType.LAZY)
	@EqualsAndHashCode.Exclude
    @ToString.Exclude
	private Collection<Tests> tests;
	
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
