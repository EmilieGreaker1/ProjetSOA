package fr.insa.ms.ReviewService.resources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.insa.ms.ReviewService.models.Review;

@RestController
@CrossOrigin(origins = {"http://127.0.0.1:9090", "http://localhost:9090"})
@RequestMapping("/review")
public class ReviewResources {
	
	@Value("${db.url}")
	private String dbUrl;
	
	@Value("${db.username}")
	private String dbUsername;
	
	@Value("${db.password}")
	private String dbPassword;
	
	@GetMapping("/dbUrl")
	public String getDbUrl() {
		return dbUrl;
	}
	
	@GetMapping("/dbUsername")
	public String getDbUsername() {
		return dbUsername;
	}
	
	@GetMapping("/dbPassword")
	public String getDbPassword() {
		return dbPassword;
	}
	
	// Get review by id
	@GetMapping("/{id}")
	public Review getReview(@PathVariable("id") int id) {
		
		Review rev = null;
		
		try {
			Connection connection = DriverManager.getConnection(getDbUrl(), getDbUsername(), getDbPassword());
			Statement stmt = connection.createStatement(); 
			ResultSet rs = stmt.executeQuery("SELECT * FROM Reviews WHERE id = '" + id + "';");
			
			while(rs.next()) {
				rev = new Review(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getString(5));
			}
			
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rev;
	}
	
	//Get all reviews of this volunteer
	@GetMapping("/vol/{volId}")
	public List<Review> getAllReviewsOnVol(@PathVariable("volId") int volId) {
		
		List<Review> revList = new ArrayList<>();
		
		try {
			Connection connection = DriverManager.getConnection(getDbUrl(), getDbUsername(), getDbPassword());
			Statement stmt = connection.createStatement(); 
			ResultSet rs = stmt.executeQuery("SELECT * FROM Reviews WHERE volId = '" + volId + "';");
			
			while(rs.next()) {
				revList.add(new Review(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getString(5)));
			}
			
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return revList;
	}
	
	//Get all reviews made by this user
	@GetMapping("/userId/{userId}")
	public List<Review> getAllReviewsByUser(@PathVariable("userId") int userId) {
		
		List<Review> revList = new ArrayList<>();
		
		try {
			Connection connection = DriverManager.getConnection(getDbUrl(), getDbUsername(), getDbPassword());
			Statement stmt = connection.createStatement(); 
			ResultSet rs = stmt.executeQuery("SELECT * FROM Reviews WHERE userId = '" + userId + "';");
			
			while(rs.next()) {
				revList.add(new Review(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getString(5)));
			}
			
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return revList;
	}
	
	// Update a review
	@PutMapping("/updateReview")
	public void updateReview(@RequestBody Review review) {
		try (Connection connection = DriverManager.getConnection(getDbUrl(), getDbUsername(), getDbPassword())) {
			Statement stmt = connection.createStatement();
			stmt.executeUpdate("UPDATE Reviews SET rating = '" + review.getRating() + "', comment = '" + review.getComment() + "' WHERE id = '" + review.getId() + "';");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Post a new review
	@PostMapping("/postReview")
	public void postHelpRequest(@RequestBody Review review) {

		try {
			Connection connection = DriverManager.getConnection(getDbUrl(), getDbUsername(), getDbPassword());
			Statement stmt = connection.createStatement();
			stmt.executeUpdate("INSERT INTO Reviews (userId, volId, rating, comment) VALUES (" + review.getUserId() + ", " + review.getVolunteerId() + ", " + review.getRating() + ", " + review.getComment() + ");");
			System.out.println("Review created successfully!");

		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Error inserting Reviews: " + e.getMessage());
		}
	}
	
	// Method to delete a review
	@DeleteMapping("/deleteReview/{id}")
    public void deleteReviewById(@PathVariable String id) {
		try {
			Connection connection = DriverManager.getConnection(getDbUrl(), getDbUsername(), getDbPassword());
			Statement stmt = connection.createStatement();
			stmt.executeUpdate("DELETE FROM Reviews WHERE id = " + id + ");");
			System.out.println("Review deleted successfully!");

		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Error deleting Review: " + e.getMessage());
		}
    }
}