package com.JavacornerAdminspringboot.Controller;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.JavacornerAdminspringboot.Dto.CourseDTO;
import com.JavacornerAdminspringboot.service.CourseService;

@RestController
@RequestMapping("/courses")
public class CourseRestController {

    private CourseService courseService;

    public CourseRestController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public Page<CourseDTO> searchCourses(@RequestParam(name = "keyword", defaultValue = "") String keyword,
                                         @RequestParam(name = "page", defaultValue = "0") int page,
                                         @RequestParam(name = "size", defaultValue = "5") int size) {
        return courseService.findCoursesByCourseName(keyword, page, size);
    }

    @DeleteMapping("/{courseId}")
    public void deleteCourse(@PathVariable Long courseId) {
        courseService.removeCourse(courseId);
    }

    @PostMapping
    public CourseDTO saveCourse(@RequestBody CourseDTO courseDTO) {
        return courseService.createCourse(courseDTO);
    }

    @PutMapping("/{courseId}")
    public CourseDTO updateCourse(@RequestBody CourseDTO courseDTO, @PathVariable Long courseId) {
        courseDTO.setCourseId(courseId);
        return courseService.updateCourse(courseDTO);
    }

    @PostMapping("/{courseId}/enroll/students/{studentId}")
    public void enrollStudentInCourse(@PathVariable Long courseId, @PathVariable Long studentId) {
        courseService.assignStudentToCourse(courseId, studentId);
    }
}
