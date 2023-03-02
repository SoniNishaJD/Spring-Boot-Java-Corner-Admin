package com.JavacornerAdminspringboot.dao;

import java.awt.print.Pageable;
//import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.JavacornerAdminspringboot.Entity.Course;

public interface CourseDao extends JpaRepository<Course, Long>{
	//if we need make page in frontend application then remove list and replace it with page
	Page<Course> findCoursesByCourseNameContains(String keyword,PageRequest pageRequest);
	
	@Query(value = "select * from courses as c where c.course_id in (select e.course_id from enrolled_in as e where e.student_id=:studentId )", nativeQuery = true)
	Page<Course> getCoursesByStudentId(@Param("studentId") Long StudentId , PageRequest pageRequest);
	
	@Query(value = "select * from courses as c where c.course_id in (select e.course_id from enrolled_in as e where e.student_id=:studentId )", nativeQuery = true)
	Page<Course> getNonEnrolledInCoursesByStudentId(@Param("studentId") Long StudentId , PageRequest pageRequest);

	@Query(value = "select c from Course as c where c.instructor.instructorId=:instructorId")
	Page<Course> getCoursesByInstructorId(@Param("instructorId") Long instructorId, PageRequest pageRequest);
}