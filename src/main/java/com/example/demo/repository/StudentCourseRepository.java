package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Student_courses;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudentCourseRepository extends JpaRepository<Student_courses, Long>{
//	List<Student_courses> findByCourseID(Long courseID);
	
	@Query(value = "Select sc from Student_courses sc where sc.studentID.id = :studentID and sc.courseID.id = :courseID")
	Optional<Student_courses> findByStudentIDAndCourseID(Long studentID, Long courseID);
}
