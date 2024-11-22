package fr.insa.mas.studentListService.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import fr.insa.mas.studentListService.model.*;

@RestController
@RequestMapping("/students")
public class StudentListResource {
	
	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("ids/{idSpeciality}")
	public StudentIDList getIDStudents(@PathVariable("idSpeciality") String speciality) {
		
		//Simulate the database using an object StudentIDList, which contains a list of integers
		StudentIDList studentsId = new StudentIDList();
		studentsId.addStudentToList(0);
		studentsId.addStudentToList(1);
		studentsId.addStudentToList(2);
		studentsId.addStudentToList(3);
		
		return studentsId;
	}
	
	@GetMapping("/all/{idSpeciality}")
	public List<Student> getStudents(@PathVariable("idSpeciality") String speciality) {
		
		//Simualte the database using a list of integers
		StudentIDList students = new StudentIDList();
		students.addStudentToList(0);
		students.addStudentToList(1);
		students.addStudentToList(2);
		students.addStudentToList(3);
		
		int i = 0;
		List<Student> listStudents = new ArrayList<Student>();
		
		while (i<students.getStudentList().size()) {
			//Call the MS to get student's information
			//The result is deserialized into StudentInfos java object
			StudentInfos etudInfos = restTemplate.getForObject("http://studentInfoService/student/" + i, StudentInfos.class);
			//Call the MS to get student's evaluation
			//The result is deserialized into Evaluation java object
			Evaluation eval = restTemplate.getForObject("http://studentEvalService/evaluation/" + i, Evaluation.class);
			//Instanciate a student with his id, first name, last name, average and store it in the list
			listStudents.add(new Student(i,etudInfos.getFirstName(),etudInfos.getLastName(),eval.getAverage()));
			i++;
		}
		return listStudents;
	}
}
