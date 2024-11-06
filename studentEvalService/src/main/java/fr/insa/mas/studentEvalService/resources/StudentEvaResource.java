package fr.insa.mas.studentEvalService.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.insa.mas.studentEvalService.model.Evaluation;

@RestController
@RequestMapping("/evaluation")
public class StudentEvaResource {

	@GetMapping("/{idStudent}")
	public Evaluation getStudentEval(@PathVariable("idStudent") int id) {
		
		//Simulate the DB with a list that contains the marks of 4 students whose id are 0,1,2,3
		List<Evaluation> evalList = Arrays.asList(
				new Evaluation(0, 13F),
				new Evaluation(1, 10.65F),
				new Evaluation(2, 18F),
				new Evaluation(3, 18F)
				);
		//Return the evaluation of the specified idStudent
		return evalList.get(id);
	}
}
