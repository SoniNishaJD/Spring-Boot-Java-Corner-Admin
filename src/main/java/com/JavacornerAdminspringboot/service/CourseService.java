package com.JavacornerAdminspringboot.service;

import org.springframework.data.domain.Page;

import com.JavacornerAdminspringboot.Dto.CourseDTO;
import com.JavacornerAdminspringboot.Entity.Course;

public interface CourseService {
	
	Course loadCourseById (Long courseId);
	
	CourseDTO createCourse (CourseDTO courseDto);
	
	CourseDTO updateCourse (CourseDTO courseDto);
	
	Page<CourseDTO> findCoursesByCourseName(String keyword, int page,int size);
	
	void assignStudentToCourse(Long courserId, Long StudentId);
	
	void removeCourse(Long courseId);
	
	Page<CourseDTO> fetchNonEnrolledInCoursesForStudent(Long studentId, int page, int size);

	Page<CourseDTO> fetchCoursesForInstructor(Long instructorId, int page, int size);

	Page<CourseDTO> fetchCoursesForStudent(Long studentId, int page, int size);
}
