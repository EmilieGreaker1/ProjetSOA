package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Student;

import java.util.ArrayList;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class StudentResource {
	private ArrayList<Student> students = new ArrayList<Student>();
	
	@GetMapping("/students")
	public ArrayList<Student> studentNumber() {
		return students;
	}
	
	@GetMapping(value="/students/{id}") 
	public Student infoStudent(@PathVariable int id) {
		Student student = new Student(id, "Rosa", "Parks");
		return student;
	}
	
	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_XML_VALUE) 
	public Student xmlInfoStudent(@PathVariable int id) {
		Student student = new Student(id, "Rosa", "Parks");
		return student;
	}
	
	@PostMapping(value="/students") 
	public void createStudent(@RequestBody Student student) {
		students.add(student);
	}
}
