//package com.JavacornerAdminspringboot.utility;
////Utility is for extra functionality
////ex. we always have firstad box at home
////-> its lamda
//
//
//import java.util.List;
//import java.util.Optional;
//
//import com.JavacornerAdminspringboot.Entity.Course;
//import com.JavacornerAdminspringboot.Entity.Instructor;
//import com.JavacornerAdminspringboot.Entity.Role;
//import com.JavacornerAdminspringboot.Entity.Student;
//import com.JavacornerAdminspringboot.Entity.User;
//import com.JavacornerAdminspringboot.dao.CourseDao;
//import com.JavacornerAdminspringboot.dao.InstructorDao;
//import com.JavacornerAdminspringboot.dao.RoleDao;
//import com.JavacornerAdminspringboot.dao.StudentDao;
//import com.JavacornerAdminspringboot.dao.UserDao;
//
//import jakarta.persistence.EntityNotFoundException;
//
//public class OperationUtility {
//
//	
//
//	public static void usersOperation(UserDao userDao) {
//		createUsers(userDao);
////		updateUser(userDao);
////		deleteUser(userDao);
//		//fatchUsers(userDao);
//	}
//	
//
//	private static void createUsers(UserDao userDao) {
//		User user1 = new  User ("user1@gmail.com", "pass1");
//		userDao.save(user1);
//		
//		User user2 = new  User ("user2@gmail.com", "pass2");
//		userDao.save(user2);
//		
//		User user3 = new  User ("user3@gmail.com", "pass3");
//		userDao.save(user3);
//		
//		User user4 = new  User ("user4@gmail.com", "pass4");
//		userDao.save(user4);
//	}
//	
//	private static void updateUser(UserDao userDao) {
//		//2L means second user
//		User user =userDao.findById(2L).orElseThrow(()-> new EntityNotFoundException("User not found"));
//		user.setEmail("newEmail@gmail.com");
//		userDao.save(user);
//	}
//	
//
//	private static void deleteUser(UserDao userDao) {
//		User user =userDao.findById(3L).orElseThrow(()-> new EntityNotFoundException("User not found"));
//		userDao.delete(user);
//		
//	}
//
//	private static void fatchUsers(UserDao userDao) {
//		userDao.findAll().forEach(user -> System.out.println(user.toString()));
//	}
//	
//	public static void rolesOperation(RoleDao roleDao) {
//		createRoles(roleDao);
//	//	updateRole(roleDao);
////		deleteRole(roleDao);
//	//	fatchRole(roleDao);
//	}
//	
//	private static void createRoles(RoleDao roleDao) {
//		Role role1 = new Role("Admin");
//		roleDao.save(role1);
//		Role role2 = new Role("Instructor");
//		roleDao.save(role2);
//		Role role3 = new Role("Student");
//		roleDao.save(role3);
//		
//	}
//	private static void updateRole(RoleDao roleDao) {
//		Role role = roleDao.findById(2L).orElseThrow(()-> new EntityNotFoundException("User not found"));
//		role.setName("NewAdmin");
//		roleDao.save(role);
//		
//	}
//	private static void deleteRole(RoleDao roleDao) {
//		roleDao.deleteById(3L);
//	}
//	private static void fatchRole(RoleDao roleDao) {
//		roleDao.findAll().forEach(role -> System.out.println(role.toString()));
//		
//	}
//	
//	public static void assignRoleToUsers(UserDao userDao,RoleDao roleDao) {
//		Role role = roleDao.findByName("Admin");
//		if(role==null) throw new EntityNotFoundException("Role Not Found");
//		List<User> users = userDao.findAll();
//		users.forEach(user ->{
//			user.assignRoleToUser(role);
//			userDao.save(user);
//		});
//	}
//	
//	public static void instructorsOperation(UserDao userDao, InstructorDao instructorDao,RoleDao roleDao ) {
//		createInstructors(userDao ,instructorDao, roleDao);
//	//	updateInstructor(userDao ,instructorDao, roleDao);
////		removeInstructor(userDao ,instructorDao, roleDao);
//	//	fatchInstructor(userDao ,instructorDao, roleDao);
//	}
//
//	private static void createInstructors(UserDao userDao, InstructorDao instructorDao, RoleDao roleDao) {
//		Role role = roleDao.findByName("Instructor");
//		if(role == null) throw new EntityNotFoundException("Role Not Found");
//		
//		User user1 = new User("instructorUser1@gmail.com" ,"pass1");
//		user1.assignRoleToUser(role);
//		userDao.save(user1);
//		Instructor instructor1 = new Instructor("instructor1FN", "instructor1LN", "Experienced Instructor", user1);
//		instructorDao.save(instructor1);
//		
//		User user2 = new User("instructorUser2@gmail.com" ,"pass2");
//		user2.assignRoleToUser(role);
//		userDao.save(user2);
//		Instructor instructor2 = new Instructor("instructor2FN", "instructor2LN", "Senior Instructor", user2);
//		instructorDao.save(instructor2);
//	}
//
//	private static void updateInstructor(UserDao userDao, InstructorDao instructorDao, RoleDao roleDao) {
//		Instructor instructor = instructorDao.findById(1L).orElseThrow(()-> new EntityNotFoundException("Instructor not found"));
//		instructor.setSummary("Certified Instructor");
//		instructorDao.save(instructor);
//		
//	}
//
//	private static void removeInstructor(UserDao userDao, InstructorDao instructorDao, RoleDao roleDao) {
//	instructorDao.deleteById(1L);
//		
//	}
//
//	private static void fatchInstructor(UserDao userDao, InstructorDao instructorDao, RoleDao roleDao) {
//		instructorDao.findAll().forEach(instructor -> System.out.println(instructor.toString()));
//		
//	}
//	public static void studentsOperation(UserDao userDao, StudentDao studentDao,RoleDao roleDao ) {
//		createStudents(userDao ,studentDao, roleDao);
////		updateStudent(userDao ,studentDao, roleDao);
//	//	removeStudent(userDao ,studentDao, roleDao);
////		fatchStudent(userDao ,studentDao, roleDao);
//	}
//
//
//	private static void createStudents(UserDao userDao, StudentDao studentDao, RoleDao roleDao) {
//	Role role = roleDao.findByName("Student");
//	if(role ==null) throw new EntityNotFoundException("Role Not Found");
//	
//	User user1 = new User ("stdUser1@gmail.com","pass1");
//	userDao.save(user1);
//	user1.assignRoleToUser(role);
//	Student student1 = new Student("student1FN", "student1LN","masters",user1);
//	studentDao.save(student1);
//	
//	User user2 = new User ("stdUser2@gmail.com","pass1");
//	userDao.save(user2);
//	user2.assignRoleToUser(role);
//	Student student2 = new Student("student2FN", "student2LN","Phds",user2);
//	studentDao.save(student2);
//	}
//
//	private static void updateStudent(UserDao userDao, StudentDao studentDao, RoleDao roleDao) {
//		Student student = studentDao.findById(1L).orElseThrow(()-> new EntityNotFoundException("Student not found"));
//		student.setFirstName("updatedStudentFN");
//		student.setLastName("UpdatedStudentLN");
//		studentDao.save(student);
//	}
//
//	private static void removeStudent(UserDao userDao, StudentDao studentDao, RoleDao roleDao) {
//		studentDao.deleteById(1L);
//		
//	}
//
//	private static void fatchStudent(UserDao userDao, StudentDao studentDao, RoleDao roleDao) {
//		studentDao.findAll().forEach(student -> System.out.println(student.toString()));
//		
//	}
//
//	public static void coursesOperation( StudentDao studentDao,CourseDao courseDao, InstructorDao instructorDao ) {
//	//	createCourses(instructorDao, courseDao);
////		updateCourse(instructorDao,studentDao, courseDao);
////		deleteCourse(instructorDao ,studentDao, courseDao);
////		fatchCourse(instructorDao , studentDao, courseDao);
//	//	assignStudentToCourse(courseDao,studentDao);
//		fatchCoursesForStudent(courseDao);
//	}
//
//	
//	private static void createCourses(InstructorDao instructorDao, CourseDao courseDao) {
//		Instructor instructor =instructorDao.findById(2L).orElseThrow(()-> new EntityNotFoundException("Instructor Not Found"));
//		
//		Course course1 = new Course("Hibernate","5Hours", "Introduction to Hibernate",instructor);
//		courseDao.save(course1);
//		
//		Course course2 = new Course ("Spring Data JPA", "10 hours","Master Spring JPA",instructor);
//		courseDao.save(course2);
//	}
//
//
//	private static void updateCourse(InstructorDao instructorDao, StudentDao studentDao, CourseDao courseDao) {
//		Course course = courseDao.findById(1L).orElseThrow(()-> new EntityNotFoundException("Course not found"));
//		course.setCourseDuration("20 hours ");
//		courseDao.save(course); 
//		
//		
//	}
//
//	private static void deleteCourse(InstructorDao instructorDao, StudentDao studentDao, CourseDao courseDao) {
//		courseDao.deleteById(2L);
//		
//	}
//
//	private static void fatchCourse(InstructorDao instructorDao, StudentDao studentDao, CourseDao courseDao) {
//		courseDao.findAll().forEach(course -> System.out.println(course.toString()));
//		
//	}
//
//
//	private static void assignStudentToCourse(CourseDao courseDao, StudentDao studentDao) {
//		Optional<Student> student1 = studentDao.findById(1L);
//		Optional<Student> student2 = studentDao.findById(2L);
//		Course course = courseDao.findById(1L).orElseThrow(()-> new EntityNotFoundException("Course Not Found"));
//		
//		student1.ifPresent(course::assignStudentToCourse);
//		student2.ifPresent(course::assignStudentToCourse);
//		courseDao.save(course);
//		
//	}
//	private static void fatchCoursesForStudent(CourseDao courseDao) {
//		courseDao.getCoursesByStudentId(1L).forEach(course -> System.out.println(course.toString()));
//		
//	}
//
//
//
//}
//
