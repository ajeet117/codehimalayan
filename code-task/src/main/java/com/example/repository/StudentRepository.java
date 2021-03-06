package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

	public Optional<Student> findByUsername(String username);
	public boolean existsByUsername(String username);
}
