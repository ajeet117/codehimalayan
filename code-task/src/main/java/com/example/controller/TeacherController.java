package com.example.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Teacher;
import com.example.repository.TeacherRepository;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {

	@Autowired
	public TeacherRepository teacherRepo;
	
	@GetMapping
	public ResponseEntity<?> getAllTeachers()
	{
		List<Teacher> allTeachers = teacherRepo.findAll();
		return new ResponseEntity<>(allTeachers,HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> saveTeacher(@Valid @RequestBody Teacher teacher)
	{
		Teacher savedTeacher = teacherRepo.save(teacher);
		return new ResponseEntity<>(savedTeacher,HttpStatus.CREATED);
	}
}
