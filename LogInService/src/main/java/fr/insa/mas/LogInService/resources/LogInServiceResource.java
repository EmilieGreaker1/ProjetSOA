package fr.insa.mas.LogInService.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.insa.mas.LogInService.model.User;

@RestController
@RequestMapping("/evaluation")
public class LogInServiceResource {

	@GetMapping("/{idStudent}")
	public User getStudentEval(@PathVariable("idStudent") int id) {
		
		//Simulate the DB with a list that contains the marks of 4 students whose id are 0,1,2,3
		List<User> evalList = Arrays.asList(
				new User(0, 13F),
				new User(1, 10.65F),
				new User(2, 18F),
				new User(3, 18F)
				);
		//Return the evaluation of the specified idStudent
		return evalList.get(id);
	}
}
