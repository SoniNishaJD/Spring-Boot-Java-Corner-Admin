package com.JavacornerAdminspringboot.ServiceImpl;

import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.JavacornerAdminspringboot.Dto.CourseDTO;
import com.JavacornerAdminspringboot.Entity.Course;
import com.JavacornerAdminspringboot.Entity.Instructor;
import com.JavacornerAdminspringboot.Entity.Student;
import com.JavacornerAdminspringboot.dao.CourseDao;
import com.JavacornerAdminspringboot.dao.InstructorDao;
import com.JavacornerAdminspringboot.dao.StudentDao;
import com.JavacornerAdminspringboot.mapper.CourseMapper;
import com.JavacornerAdminspringboot.service.CourseService;

import jakarta.persistence.EntityNotFoundException;


@Service
@Transactional
public class CourseServiceImpl implements CourseService {

    private CourseDao courseDao;

    private CourseMapper courseMapper;

    private InstructorDao instructorDao;

    private StudentDao studentDao;

    public CourseServiceImpl(CourseDao courseDao, CourseMapper courseMapper, InstructorDao instructorDao, StudentDao studentDao) {
        this.courseDao = courseDao;
        this.courseMapper = courseMapper;
        this.instructorDao = instructorDao;
        this.studentDao = studentDao;
    }

    @Override
    public Course loadCourseById(Long courseId) {
        return courseDao.findById(courseId).orElseThrow(() -> new EntityNotFoundException("Course with ID " + courseId + " Not Found"));
    }

    @Override
    public CourseDTO createCourse(CourseDTO courseDTO) {
        Course course = courseMapper.fromCourseDto(courseDTO);
        Instructor instructor = instructorDao.findById(courseDTO.getInstructor().getInstructorId()).orElseThrow(() -> new EntityNotFoundException("Instructor with ID " + courseDTO.getInstructor().getInstructorId() + " Not Found"));
        course.setInstructor(instructor);
        Course savedCourse = courseDao.save(course);
        return courseMapper.fromCourse(savedCourse);
    }

    @Override
    public CourseDTO updateCourse(CourseDTO courseDTO) {
        Course loadedCourse = loadCourseById(courseDTO.getCourseId());
        Instructor instructor = instructorDao.findById(courseDTO.getInstructor().getInstructorId()).orElseThrow(() -> new EntityNotFoundException("Instructor with ID " + courseDTO.getInstructor().getInstructorId() + " Not Found"));
        Course course = courseMapper.fromCourseDto(courseDTO);
        course.setInstructor(instructor);
        course.setStudents(loadedCourse.getStudents());
        Course updatedCourse = courseDao.save(course);
        return courseMapper.fromCourse(updatedCourse);
    }

    @Override
    public Page<CourseDTO> findCoursesByCourseName(String keyword, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Course> coursesPage = courseDao.findCoursesByCourseNameContains(keyword, pageRequest);
        return new PageImpl<>(coursesPage.getContent().stream().map(course -> courseMapper.fromCourse(course)).collect(Collectors.toList()), pageRequest, coursesPage.getTotalElements());
    }

    @Override
    public void assignStudentToCourse(Long courseId, Long studentId) {
        Student student = studentDao.findById(studentId).orElseThrow(() -> new EntityNotFoundException("Student with ID " + studentId + " Not Found"));
        Course course = loadCourseById(courseId);
        course.assignStudentToCourse(student);
    }

    @Override
    public Page<CourseDTO> fetchCoursesForStudent(Long studentId, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Course> studentCoursesPage = courseDao.getCoursesByStudentId(studentId,pageRequest);
        return new PageImpl<>(studentCoursesPage.getContent().stream().map(course -> courseMapper.fromCourse(course)).collect(Collectors.toList()), pageRequest, studentCoursesPage.getTotalElements());
    }

    @Override
    public Page<CourseDTO> fetchNonEnrolledInCoursesForStudent(Long studentId, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Course> nonEnrolledInCoursesPage = courseDao.getNonEnrolledInCoursesByStudentId(studentId,pageRequest);
        return new PageImpl<>(nonEnrolledInCoursesPage.getContent().stream().map(course -> courseMapper.fromCourse(course)).collect(Collectors.toList()), pageRequest, nonEnrolledInCoursesPage.getTotalElements());
    }

    @Override
    public void removeCourse(Long courseId) {
        courseDao.deleteById(courseId);
    }

    @Override
    public Page<CourseDTO> fetchCoursesForInstructor(Long instructorId, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Course> instructorCoursesPage = courseDao.getCoursesByInstructorId(instructorId, pageRequest);
        return new PageImpl<>(instructorCoursesPage.getContent().stream().map(course -> courseMapper.fromCourse(course)).collect(Collectors.toList()), pageRequest, instructorCoursesPage.getTotalElements());
    }
}
