package com.example;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.model.Courses;
import com.example.model.Teacher;
import com.example.repository.CoursesRepository;
import com.example.repository.TeacherRepository;

@SpringBootApplication
public class CodeTaskApplication {


	
	@Autowired
	private CoursesRepository courseRepo;
	public static void main(String[] args) {
		SpringApplication.run(CodeTaskApplication.class, args);
	}

//	@PostConstruct
//	void init()
//	{
//		
//		Courses courses1=new Courses("Math",new Teacher("Ram Maharjan"));
//		Courses courses2=new Courses("Science",new Teacher("Shyam Shakya"));
//		
//		courseRepo.save(courses1);
//		courseRepo.save(courses2);
//	}
}
