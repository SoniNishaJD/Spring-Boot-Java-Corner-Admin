package com.JavacornerAdminspringboot.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.JavacornerAdminspringboot.Entity.User;

public interface UserDao extends JpaRepository<User, Long>{
	
	User findByEmail(String email);

}
