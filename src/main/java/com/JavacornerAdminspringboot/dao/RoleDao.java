package com.JavacornerAdminspringboot.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.JavacornerAdminspringboot.Entity.Role;

public interface RoleDao extends JpaRepository<Role, Long> {
	
	Role findByName(String name);

}
