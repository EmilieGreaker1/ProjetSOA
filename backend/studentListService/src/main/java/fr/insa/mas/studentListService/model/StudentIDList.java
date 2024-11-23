package fr.insa.mas.studentListService.model;

import java.util.ArrayList;
import java.util.List;

public class StudentIDList {
	List <Integer> studentList;
	
	public StudentIDList() {
		this.studentList = new ArrayList<Integer>();
	}
	
	public List<Integer> getStudentList() {
		return studentList;
	}
	
	public void addStudentToList(Integer studentId) {
		this.studentList.add(studentId);
	}
}
