package fr.insa.msa.OrchestrationService.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import fr.insa.msa.OrchestrationService.models.*;

@RestController
@CrossOrigin(origins = {"http://127.0.0.1:9090", "http://localhost:9090"})
@RequestMapping("/orc")
public class OrchestrationResources {
	
	@Autowired
	private RestTemplate restTemplate;
	
	// Login block
	
	@GetMapping("/user={userID}+pwd={password}")
	public User getUser(@PathVariable("userID") int userID, @PathVariable("password") String pwd) {
		return restTemplate.getForObject("http://login-service/login/" + userID + "+" + pwd, User.class);
	}
	
	// Sign up block
	
	@GetMapping("/users")
    public List<User> getAllUsers() {
		ResponseEntity<List<User>> response = restTemplate.exchange(
		        "http://signup-service/users",          
		        HttpMethod.GET,
		        null, // Request body
		        new ParameterizedTypeReference<List<User>>() {}
		    );
		    return response.getBody();
	}
	
	// Spontaneous help block
	
	// Request block
	
}
