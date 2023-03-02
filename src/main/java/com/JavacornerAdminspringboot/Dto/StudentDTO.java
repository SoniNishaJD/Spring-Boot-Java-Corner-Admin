package com.JavacornerAdminspringboot.Dto;

public class StudentDTO {
	
	private Long StudentId;
	
	private String firstName;
	
	private String lastName;
	
	private String level;
	
	private UserDTO user;

	public Long getStudentId() {
		return StudentId;
	}

	public void setStudentId(Long studentId) {
		StudentId = studentId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}
	
	

}
