package com.JavacornerAdminspringboot.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.JavacornerAdminspringboot.Dto.InstructorDTO;
import com.JavacornerAdminspringboot.Entity.Instructor;

public interface InstructorService {

	Instructor loadInstructorById(Long instructorId);

    Page<InstructorDTO> findInstructorsByName(String name, int page, int size);

    InstructorDTO loadInstructorByEmail(String email);

    InstructorDTO createInstructor(InstructorDTO InstructorDto);

    InstructorDTO updateInstructor(InstructorDTO InstructorDto);

    List<InstructorDTO> fetchInstructors();

    void removeInstructor(Long instructorId);
}
