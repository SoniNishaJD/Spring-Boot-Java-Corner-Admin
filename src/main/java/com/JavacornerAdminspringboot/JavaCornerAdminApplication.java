package com.JavacornerAdminspringboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.JavacornerAdminspringboot.dao.CourseDao;
import com.JavacornerAdminspringboot.dao.InstructorDao;
import com.JavacornerAdminspringboot.dao.RoleDao;
import com.JavacornerAdminspringboot.dao.StudentDao;
import com.JavacornerAdminspringboot.dao.UserDao;


//import com.JavacornerAdminspringboot.utility.OperationUtility;


@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class JavaCornerAdminApplication /*implements CommandLineRunner*/{
//
//	@Autowired
//	private UserDao userDao;
//	
//	@Autowired
//	private CourseDao courseDao;
//	
//	@Autowired
//	private InstructorDao instructorDao;
//	
//	@Autowired
//	private StudentDao studentDao;
//	
//	@Autowired
//	private RoleDao roleDao;
//	
//	
	public static void main(String[] args) {
		SpringApplication.run(JavaCornerAdminApplication.class, args);
	}
//
//	@Override
//	public void run(String... args) throws Exception {
////		OperationUtility.usersOperation(userDao);
////		OperationUtility.rolesOperation(roleDao);
////		OperationUtility.assignRoleToUsers(userDao, roleDao);
////		OperationUtility.instructorsOperation(userDao, instructorDao, roleDao);
////		OperationUtility.studentsOperation(userDao, studentDao, roleDao);
//		OperationUtility.coursesOperation(studentDao, courseDao, instructorDao);
//	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
