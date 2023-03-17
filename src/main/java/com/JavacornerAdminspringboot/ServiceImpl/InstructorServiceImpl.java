package com.JavacornerAdminspringboot.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.JavacornerAdminspringboot.Dto.InstructorDTO;
import com.JavacornerAdminspringboot.Entity.Course;
import com.JavacornerAdminspringboot.Entity.Instructor;
import com.JavacornerAdminspringboot.Entity.User;
import com.JavacornerAdminspringboot.dao.InstructorDao;
import com.JavacornerAdminspringboot.mapper.InstructorMapper;
import com.JavacornerAdminspringboot.service.CourseService;
import com.JavacornerAdminspringboot.service.InstructorService;
import com.JavacornerAdminspringboot.service.UserService;



@Service
@Transactional
public class InstructorServiceImpl implements InstructorService {

    private InstructorDao instructorDao;

    private InstructorMapper instructorMapper;

    private UserService userService;

    private CourseService courseService;

    public InstructorServiceImpl(InstructorDao instructorDao, InstructorMapper instructorMapper, UserService userService, CourseService courseService) {
        this.instructorDao = instructorDao;
        this.instructorMapper = instructorMapper;
        this.userService = userService;
        this.courseService = courseService;
    }

    @Override
    public Instructor loadInstructorById(Long instructorId) {
        return instructorDao.findById(instructorId).orElseThrow(() -> new EntityNotFoundException("Instructor with ID" + instructorId + " not found"));
    }

    @Override
    public Page<InstructorDTO> findInstructorsByName(String name, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Instructor> instructorsPage = instructorDao.findInstructorsByName(name, pageRequest);
        return new PageImpl<>(instructorsPage.getContent().stream().map(instructor -> instructorMapper.fromInstructor(instructor)).collect(Collectors.toList()), pageRequest, instructorsPage.getTotalElements());
    }

    @Override
    public InstructorDTO loadInstructorByEmail(String email) {
        return instructorMapper.fromInstructor(instructorDao.findInstuctorByEmail(email));
    }

    @Override
    public InstructorDTO createInstructor(InstructorDTO instructorDto) {
        User user = userService.createUser(instructorDto.getUser().getEmail(),instructorDto.getUser().getPassword());
        userService.assignRoleToUser(user.getEmail(),"Instructor");
        Instructor instructor = instructorMapper.fromInstructorDTO(instructorDto);
        instructor.setUser(user);
        Instructor savedInstructor = instructorDao.save(instructor);
        return instructorMapper.fromInstructor(savedInstructor);
    }

    @Override
    public InstructorDTO updateInstructor(InstructorDTO instructorDto) {
        Instructor loadedInstructor = loadInstructorById(instructorDto.getInstructorId());
        Instructor instructor = instructorMapper.fromInstructorDTO(instructorDto);
        instructor.setUser(loadedInstructor.getUser());
        instructor.setCourses(loadedInstructor.getCourses());
        Instructor updatedInstructor = instructorDao.save(instructor);
        return instructorMapper.fromInstructor(updatedInstructor);
    }

    @Override
    public List<InstructorDTO> fetchInstructors() {
        return instructorDao.findAll().stream().map(instructor -> instructorMapper.fromInstructor(instructor)).collect(Collectors.toList());
    }

    @Override
    public void removeInstructor(Long instructorId) {
        Instructor instructor = loadInstructorById(instructorId);
        for(Course course : instructor.getCourses()) {
            courseService.removeCourse(course.getCourseId());
        }
        instructorDao.deleteById(instructorId);
    }
}
