package fr.insa.msa.OrchestrationService.resources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	// Method to GET a user by email and password
	@GetMapping("/{email}+{password}")
	public User getUser(@PathVariable("email") String email, @PathVariable("password") String pwd) {
		return restTemplate.getForObject("http://login-service/login/" + email + "+" + pwd, User.class);
	}
	
	// Sign up block
	
	// Method to GET all users
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
	
	// Method to ADD a new user
    @PostMapping("/signup")
    public User addUser(@RequestBody User user) {
    	return restTemplate.postForObject("http://signup-service/signup", user, User.class);
    }
	
	// Spontaneous help block
    
    // Get SpontaneousHelp by id
 	@GetMapping("/spontHelp+{id}")
 	public SpontaneousHelp getSpontHelp(@PathVariable("id") int id) {
 		return restTemplate.getForObject("http://spontaneoushelp-service/spontHelp/" + id, SpontaneousHelp.class);
 	}
 		
 	//Get all SpontaneousHelp with this user
 	@GetMapping("/allSpontHelpOfUser+{userId}")
 	public List<SpontaneousHelp> getAllUserSpontHelp(@PathVariable("userId") int userId) {
 		ResponseEntity<List<SpontaneousHelp>> response = restTemplate.exchange(
		        "http://spontaneoushelp-service/spontHelp/user+" + userId,          
		        HttpMethod.GET,
		        null, // Request body
		        new ParameterizedTypeReference<List<SpontaneousHelp>>() {}
		    );
		    return response.getBody();
 	}
 		
 	//Get all SpontaneousHelp with this volunteer
 	@GetMapping("/allSpontHelpOfVolunteer+{volunteerId}")
 	public List<SpontaneousHelp> getAllVolunteerSpontHelp(@PathVariable("volunteerId") int volunteerId) {
 		ResponseEntity<List<SpontaneousHelp>> response = restTemplate.exchange(
		        "http://spontaneoushelp-service/spontHelp/volunteer+" + volunteerId,          
		        HttpMethod.GET,
		        null, // Request body
		        new ParameterizedTypeReference<List<SpontaneousHelp>>() {}
		    );
		    return response.getBody();
 	}
 	
 	// Get all SpontaneousHelp awaiting admin action from the database
 	@GetMapping("/allWaitingSpontaneousHelp")
 	public List<SpontaneousHelp> getWaitingSpontaneousHelp() {
 		ResponseEntity<List<SpontaneousHelp>> response = restTemplate.exchange(
		        "http://spontaneoushelp-service/spontHelp/allWaitingSpontaneousHelp",          
		        HttpMethod.GET,
		        null, // Request body
		        new ParameterizedTypeReference<List<SpontaneousHelp>>() {}
		    );
		    return response.getBody();
 	}
 	
 	// Get all admin-accepted SpontaneousHelp from the database
 	@GetMapping("/allAcceptedSpontaneousHelp")
 	public List<SpontaneousHelp> getAcceptedSpontaneousHelp() {
 		ResponseEntity<List<SpontaneousHelp>> response = restTemplate.exchange(
		        "http://spontaneoushelp-service/spontHelp/allAcceptedSpontaneousHelp",          
		        HttpMethod.GET,
		        null, // Request body
		        new ParameterizedTypeReference<List<SpontaneousHelp>>() {}
		    );
		    return response.getBody();
 	}
 	
 	// Update a spontaneous help proposition
 	@PutMapping("/updateSpontHelp")
 	public void updateSpontaneousHelp(@RequestBody SpontaneousHelp spontHelp) {
 		restTemplate.exchange(
		        "http://spontaneoushelp-service/spontHelp/updateSpontHelp",          
		        HttpMethod.PUT,
		        new HttpEntity(spontHelp), // Request body
		        new ParameterizedTypeReference<SpontaneousHelp>() {}
		    );
 	}

 	// Update spontHelp STATUS
 	@PutMapping("/updateStatusSpontHelp")
 	public void updateSpontaneousHelpStatus(@RequestBody SpontaneousHelp spontHelp) {
 		restTemplate.exchange(
		        "http://spontaneoushelp-service/spontHelp/updateStatus",          
		        HttpMethod.PUT,
		        new HttpEntity(spontHelp), // Request body
		        new ParameterizedTypeReference<SpontaneousHelp>() {}
		    );
 	}

 	// Update spontHelp VOLUNTEER
 	@PutMapping("/updateVolunteerSpontHelp")
 	public void updateSpontaneousHelpVolunteer(@RequestBody SpontaneousHelp spontHelp) {
 		restTemplate.exchange(
		        "http://spontaneoushelp-service/spontHelp/updateVolunteer",          
		        HttpMethod.PUT,
		        new HttpEntity(spontHelp), // Request body
		        new ParameterizedTypeReference<SpontaneousHelp>() {}
		    );
 	}
 	
 	// Post a new spontaneous help proposition
 	@PostMapping("/postSpontHelp")
 	public void postSpontaneousHelp(@RequestBody SpontaneousHelp spontHelp) {
 		restTemplate.postForObject("http://spontaneoushelp-service/spontHelp/postSpontHelp", spontHelp, SpontaneousHelp.class);
 	}
	
	// Request block
 	
 	// Get request by id
 	@GetMapping("/request+{id}")
 	public Request getRequest(@PathVariable("id") int id) {
 		return restTemplate.getForObject("http://request-service/request/" + id, Request.class);
 	}
 	
 	//Get all requests with this user
 	@GetMapping("/allRequestsOfUser+{userId}")
 	public List<Request> getAllUserRequest(@PathVariable("userId") int userId) {
 		ResponseEntity<List<Request>> response = restTemplate.exchange(
		        "http://request-service/request/user+" + userId,          
		        HttpMethod.GET,
		        null, // Request body
		        new ParameterizedTypeReference<List<Request>>() {}
		    );
		    return response.getBody();
 	}
 	
 	//Get all requests with this volunteer
 	@GetMapping("/allRequestsOfVolunteer+{volunteerId}")
 	public List<Request> getAllVolunteerRequest(@PathVariable("volunteerId") int volunteerId) {
 		ResponseEntity<List<Request>> response = restTemplate.exchange(
		        "http://request-service/request/volunteerId+" + volunteerId,          
		        HttpMethod.GET,
		        null, // Request body
		        new ParameterizedTypeReference<List<Request>>() {}
		    );
		    return response.getBody();
 	}
 	
 	// Get all requests awaiting admin action from the database
 	@GetMapping("/allWaitingRequests")
 	public List<Request> getWaitingRequest() {
 		ResponseEntity<List<Request>> response = restTemplate.exchange(
		        "http://request-service/request/allWaitingRequests",          
		        HttpMethod.GET,
		        null, // Request body
		        new ParameterizedTypeReference<List<Request>>() {}
		    );
		    return response.getBody();
 	}
 	
 	// Get all admin-accepted requests from the database
 	@GetMapping("/allAcceptedRequests")
 	public List<Request> getAcceptedRequests() {
 		ResponseEntity<List<Request>> response = restTemplate.exchange(
		        "http://request-service/request/allAcceptedRequests",          
		        HttpMethod.GET,
		        null, // Request body
		        new ParameterizedTypeReference<List<Request>>() {}
		    );
		    return response.getBody();
 	}
 	
 	// Update a request
 	@PutMapping("/updateRequest")
 	public void updateHelpRequest(@RequestBody Request request) {
 		restTemplate.exchange(
		        "http://request-service/request/updateRequest",          
		        HttpMethod.PUT,
		        new HttpEntity(request), // Request body
		        new ParameterizedTypeReference<Request>() {}
		    );
 	}

 	// Update request STATUS
 	@PutMapping("/updateStatusRequest")
 	public void updateRequestStatus(@RequestBody Request request) {
 		restTemplate.exchange(
		        "http://request-service/request/updateStatus",          
		        HttpMethod.PUT,
		        new HttpEntity(request), // Request body
		        new ParameterizedTypeReference<Request>() {}
		    );
 	}
 	
 	// Post a new request
 	@PostMapping("/postRequest")
 	public void postHelpRequest(@RequestBody Request request) {
 		restTemplate.postForObject("http://request-service/request/postRequest", request, Request.class);
 	}
 	
 	// Review block
 	
 	// Get review by id
 	@GetMapping("/getReview/{id}")
 	public Review getReview(@PathVariable("id") int id) {
 		return restTemplate.getForObject("http://review-service/review/" + id, Review.class);
 	}
 	
 	//Get all reviews of this volunteer
 	@GetMapping("/allReviewsOfVol/{volId}")
 	public List<Review> getAllReviewsOnVol(@PathVariable("volId") int volId) {
 		ResponseEntity<List<Review>> response = restTemplate.exchange(
		        "http://review-service/review/vol/" + volId,          
		        HttpMethod.GET,
		        null, // Request body
		        new ParameterizedTypeReference<List<Review>>() {}
		    );
		    return response.getBody();
 	}
 	
 	//Get all reviews made by this user
 	@GetMapping("/allReviewsOfUser/{userId}")
 	public List<Review> getAllReviewsByUser(@PathVariable("userId") int userId) {
 		ResponseEntity<List<Review>> response = restTemplate.exchange(
		        "http://review-service/review/userId/" + userId,          
		        HttpMethod.GET,
		        null, // Request body
		        new ParameterizedTypeReference<List<Review>>() {}
		    );
		    return response.getBody();
 	}
 	
 	// Update a review
 	@PutMapping("/updateReview")
 	public void updateReview(@RequestBody Review review) {
 		restTemplate.exchange(
		        "http://review-service/review/updateReview",          
		        HttpMethod.PUT,
		        new HttpEntity(review), // Request body
		        new ParameterizedTypeReference<Review>() {}
		    );
 	}
 	
 	// Post a new review
 	@PostMapping("/postReview")
 	public void postHelpRequest(@RequestBody Review review) {
 		restTemplate.postForObject("http://review-service/review/postReview", review, Review.class);
 	}
 	
 	// Method to delete a review
 	@DeleteMapping("/deleteReview/{id}")
    public void deleteReviewById(@PathVariable String id) {
 		restTemplate.exchange(
		        "http://review-service/review/deleteReview/" + id,          
		        HttpMethod.DELETE,
		        null, // Request body
		        String.class
		    );
    }
	
}
