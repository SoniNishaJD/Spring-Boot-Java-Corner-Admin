package com.JavacornerAdminspringboot.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.JavacornerAdminspringboot.Dto.InstructorDTO;
import com.JavacornerAdminspringboot.Entity.Instructor;
//Mapper is Service so we have to use @service
@Service
public class InstructorMapper {
	
	public InstructorDTO fromInstructor(Instructor instructor) {
        InstructorDTO instructorDTO = new InstructorDTO();
        BeanUtils.copyProperties(instructor, instructorDTO);
        return instructorDTO;
    }

    public Instructor fromInstructorDTO(InstructorDTO instructorDTO) {
        Instructor instructor = new Instructor();
        BeanUtils.copyProperties(instructorDTO, instructor);
        return instructor;
    }
}
