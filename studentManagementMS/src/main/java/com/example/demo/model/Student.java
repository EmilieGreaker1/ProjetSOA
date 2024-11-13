package com.example.demo.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Student")
public class Student {
	private int id;
	private String lastName;
	private String firstName;
	
	 
	public Student(int id, String lastName, String firstName) {
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
	}
	
	public Student() {
	}
	
	@XmlElement
	public int getId() {
		return id;
	}
	
	@XmlElement
	public String getLastName() {
		return lastName;
	}
	
	@XmlElement
	public String getFirstName() {
		return firstName;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
}
