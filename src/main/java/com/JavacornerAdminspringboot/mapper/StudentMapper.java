package com.JavacornerAdminspringboot.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.JavacornerAdminspringboot.Dto.StudentDTO;
import com.JavacornerAdminspringboot.Entity.Student;

@Service
public class StudentMapper {

	public StudentDTO fromStudent(Student student) {
		StudentDTO studentDto = new StudentDTO();
		BeanUtils.copyProperties(student, studentDto);
		return studentDto;
	}
	
	public Student fromStudentDto(StudentDTO studentDto) {
		Student student = new Student();
		BeanUtils.copyProperties(studentDto, student);
		return student;
	}
}
