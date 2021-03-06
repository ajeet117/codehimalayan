package com.example.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name="student")
public class Student implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotBlank
	@Size(max=20)
	private String username;
	@NotBlank
	@Email
	private String email;
	@NotBlank
	@Size(max=100)
	private String password;
	@NotBlank
	@Size(max=40)
	private String fullName;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="student_couse",
	joinColumns = @JoinColumn(name="student_id"),
	inverseJoinColumns = @JoinColumn(name="course_id"))
	private List<Courses> courses = new ArrayList<>() ;

	public Student()
	{
		
	}
	
	public Student( String username, String email,
			 String password,String fullName) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.fullName = fullName;
	}

	public Student(String username, String email, String password, String name, List<Courses> courses) {
		
		this.username = username;
		this.email = email;
		this.password = password;
		this.fullName = name;
		this.courses = courses;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return fullName;
	}
	public void setName(String name) {
		this.fullName = name;
	}
	public List<Courses> getCourses() {
		return courses;
	}
	public void setCourses(List<Courses> courses) {
		this.courses = courses;
	}
	
	

	
}
