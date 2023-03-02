package com.JavacornerAdminspringboot.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.JavacornerAdminspringboot.Dto.CourseDTO;
import com.JavacornerAdminspringboot.Entity.Course;
 
@Service
public class CourseMapper {
	
	private InstructorMapper instructorMapper;
		
	public CourseMapper(InstructorMapper instructorMapper) {
		super();
		this.instructorMapper = instructorMapper;
	}

	public CourseDTO fromCourse(Course course) {
		CourseDTO courseDto = new CourseDTO();
		BeanUtils.copyProperties(course, courseDto);// course, courseDto means copy source course to coursedto
		courseDto.setInstructor(instructorMapper.fromInstructor(course.getInstructor()));
		return courseDto;		
	}
	//this method is use to post in request 
	public Course fromCourseDto(CourseDTO courseDto) {
		Course course = new Course();
		BeanUtils.copyProperties(courseDto, course); //courseDto, course is means to copy courseDto to target means course 
		return course;
	}

}
