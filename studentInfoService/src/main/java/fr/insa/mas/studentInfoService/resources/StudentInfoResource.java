package fr.insa.mas.studentInfoService.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.insa.mas.studentInfoService.model.StudentInfos;

@RestController
@RequestMapping("/student")
public class StudentInfoResource {
	
	@GetMapping("/{idStudent}")
	public StudentInfos getInfoEtudiant(@PathVariable("idStudent") int id) {
		//Simulate the DB with a list
		List<StudentInfos> etudInfos = Arrays.asList(
				new StudentInfos(0, "Godart", "Noemie", "12/12/1992"),
				new StudentInfos(1, "Perrin", "Ania", "10/02/1993"),
				new StudentInfos(2, "Azi", "Sana", "22/05/1992"),
				new StudentInfos(3, "Yala", "Nelia", "12/06/1994")
				);
		//Get the student that corresponds to the id
		return etudInfos.get(id);
	}
}
