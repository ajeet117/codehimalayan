package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Courses;

public interface CoursesRepository extends JpaRepository<Courses, Long> {

}
