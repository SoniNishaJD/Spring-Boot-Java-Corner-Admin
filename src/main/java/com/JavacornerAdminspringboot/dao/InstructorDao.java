package com.JavacornerAdminspringboot.dao;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.JavacornerAdminspringboot.Entity.Instructor;

public interface InstructorDao extends JpaRepository<Instructor, Long> {
	
	//There is no name ,there is firstName and lastName
	//So write query to combine firstName and lastName
	//Here,we use PageRequest because pageable is interface but pageRequest is working on service layer 
	
	@Query(value = "select i from Instructor as i where i.firstName like %:name% or i.lastName like %:name%")
	Page<Instructor> findInstructorsByName(@Param("name")String name, PageRequest pageRequest);
	
	//email comes from user
	@Query(value = "select i from Instructor as i where i.user.email=:email")
	Instructor findInstuctorByEmail(@Param("email") String email);

}
