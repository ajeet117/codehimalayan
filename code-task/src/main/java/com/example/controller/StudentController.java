package com.example.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.exception.ResourceNotFoundException;
import com.example.model.Student;
import com.example.payload.StudentResponse;
import com.example.repository.StudentRepository;

@RestController
@RequestMapping("/api/students")
public class StudentController {
	
	@Autowired
	private StudentRepository studentRepo;

	@GetMapping("/{id}")
	public ResponseEntity<?> getStudentById(@PathVariable(name="id") long id)
	{
		Student student=studentRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student with id" + id + "was not found"));;
		StudentResponse convertedStudent=StudentResponse.retrunStudent(student);
		return new ResponseEntity<>(convertedStudent,HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> saveStudent(@Valid @RequestBody Student student)
	{
		Student savedStudent= studentRepo.save(student);
		StudentResponse convertedStudent= StudentResponse.retrunStudent(savedStudent);
		return new ResponseEntity<>(convertedStudent,HttpStatus.CREATED);
	}
	@PutMapping("/{id}")
	public ResponseEntity<?> editStudent(@PathVariable(name="id") long id, @RequestBody Student student)
	{
		Student studentById= studentRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student with id" + id + "was not found"));;
		studentById.setName(student.getName());
		studentById.setEmail(student.getEmail());
		studentById.setUsername(student.getUsername());
		Student editedStudent= studentRepo.save(studentById);
		StudentResponse convertedStudent= StudentResponse.retrunStudent(editedStudent);
		return new ResponseEntity<>(convertedStudent,HttpStatus.OK);
	}
	
	@PutMapping("/courses/{id}")
	public ResponseEntity<?> addCoursesToStudent(@PathVariable(name="id") long id, @RequestBody Student student)
	{
		Student studentById= studentRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student with id" + id + "was not found"));;
		studentById.setCourses(student.getCourses());
		Student editedStudent= studentRepo.save(studentById);
		StudentResponse convertedStudent= StudentResponse.retrunStudent(editedStudent);
		return new ResponseEntity<>(convertedStudent,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCourse(@PathVariable(name="id") long id)
	{
		studentRepo.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
}
