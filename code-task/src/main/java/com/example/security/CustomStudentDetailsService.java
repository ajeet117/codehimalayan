package com.example.security;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.model.Student;
import com.example.repository.StudentRepository;
@Service
public class CustomStudentDetailsService implements UserDetailsService {

	@Autowired
	private StudentRepository studentRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Student student = studentRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Student with username " + username + "wasnot found"));
		
		return StudentPrincipal.create(student);
	}
	
	@Transactional
	public UserDetails loadUsersById(long id)
	{
		Student student = studentRepo.findById(id).orElseThrow(() -> new UsernameNotFoundException("Student with id " + id + "wasnot found"));
		return StudentPrincipal.create(student);
	}

}
