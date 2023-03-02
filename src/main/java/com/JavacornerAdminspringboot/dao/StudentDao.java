package com.JavacornerAdminspringboot.dao;

//import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.JavacornerAdminspringboot.Entity.Student;

public interface StudentDao extends JpaRepository<Student, Long> {

	// if we use nativeQuery then we have to use database table name
	@Query(value = "select s from Student as s where s.firstName like %:name% or s. lastName like %:name%")
	Page<Student> findStudentsByName(@Param("name")String name , PageRequest pageRequest);
	
	@Query(value = "select s from Student as s where s.user.email=:email")
	Student findStudentByEmail(@Param("email")String email);

}
