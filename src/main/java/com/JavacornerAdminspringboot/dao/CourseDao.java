package com.JavacornerAdminspringboot.dao;

import java.awt.print.Pageable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.JavacornerAdminspringboot.Entity.Course;

public interface CourseDao extends JpaRepository<Course, Long> {

    Page<Course> findCoursesByCourseNameContains(String keyword, PageRequest pageRequest);


    @Query(value = "select * from courses where course_id in (select e.course_id from enrolled_in as e where e.student_id=:studentId)", nativeQuery = true)
    Page<Course> getCoursesByStudentId(@Param("studentId") Long studentId, PageRequest pageRequest);

    @Query(value = "select * from courses where course_id not in (select e.course_id from enrolled_in as e where e.student_id=:studentId)", nativeQuery = true)
    Page<Course> getNonEnrolledInCoursesByStudentId(@Param("studentId") Long studentId, PageRequest pageRequest);

    @Query(value = "select c from Course as c where c.instructor.instructorId=:instructorId")
    Page<Course> getCoursesByInstructorId(@Param("instructorId") Long instructorId, PageRequest pageRequest);

}
