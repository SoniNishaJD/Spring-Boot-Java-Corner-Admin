package com.JavacornerAdminspringboot.service;

import com.JavacornerAdminspringboot.Entity.User;

public interface UserService {

	User loadUserByEmail(String email);

	User createUser(String email, String password);

	void assignRoleToUser(String email, String roleName);

}
