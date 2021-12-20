package com.example.payload;

import java.util.ArrayList;
import java.util.List;

import com.example.model.Courses;
import com.example.model.Student;

public class StudentResponse {

	private long id;
	private String username;
	private String email;
	private String fullname;
	private List<Courses> courses=new ArrayList<>();
	
	public StudentResponse(long id, String username, String email, String fullname, List<Courses> courses) {
	
		this.id = id;
		this.username = username;
		this.email = email;
		this.fullname = fullname;
		this.courses = courses;
	}
	
	public static StudentResponse retrunStudent(Student student)
	{
		return new StudentResponse(student.getId(),student.getUsername(),student.getEmail(),student.getName(),student.getCourses());
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public List<Courses> getCourses() {
		return courses;
	}
	public void setCourses(List<Courses> courses) {
		this.courses = courses;
	}
	
	
}
