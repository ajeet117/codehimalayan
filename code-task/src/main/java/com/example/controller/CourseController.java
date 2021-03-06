package com.example.controller;

import java.util.List;

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
import com.example.model.Courses;
import com.example.repository.CoursesRepository;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

	@Autowired
	private CoursesRepository coursesRepo;
	@GetMapping
	public ResponseEntity<?> getAllCourses()
	{
		List<Courses> allCourses = coursesRepo.findAll();
		return new ResponseEntity<>(allCourses, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getCourseById(@PathVariable(name="id") long id)
	{
		Courses course= coursesRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Course with id" + id + "was not found"));
		return new ResponseEntity<>(course,HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> saveCourse(@Valid @RequestBody Courses course)
	{
		Courses savedCourses=coursesRepo.save(course);
		return new ResponseEntity<>(savedCourses,HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> editCourse(@PathVariable(name="id") Long id,@Valid @RequestBody Courses course)
	{
		Courses editCourse=coursesRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Course with id" + id + "was not found"));;
		editCourse.setName(course.getName());
		editCourse.setTeacher(course.getTeacher());
		Courses editedCourses= coursesRepo.save(editCourse);
		return new ResponseEntity<>(editedCourses,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCourse(@PathVariable(name="id") Long id)
	{
		coursesRepo.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
