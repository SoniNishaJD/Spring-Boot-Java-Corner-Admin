package com.JavacornerAdminspringboot.service;

import org.springframework.data.domain.Page;

import com.JavacornerAdminspringboot.Dto.StudentDTO;
import com.JavacornerAdminspringboot.Entity.Student;

public interface StudentService {
	
	 Student loadStudentById(Long studentId);

	    Page<StudentDTO> loadStudentsByName(String name, int page, int size);

	    StudentDTO loadStudentByEmail(String email);

	    StudentDTO createStudent(StudentDTO studentDto);

	    StudentDTO updateStudent(StudentDTO studentDto);

	    void removeStudent(Long studentId);

}
