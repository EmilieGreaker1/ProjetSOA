package fr.insa.msa.RequestService.resources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.PreparedStatement; // Import necesario
import java.sql.Timestamp; // Para manejar fechas

import fr.insa.msa.RequestService.models.Request;

@RestController
@CrossOrigin(origins = {"http://127.0.0.1:9090", "http://localhost:9090"})
@RequestMapping("/request")
public class RequestResources {
	
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
	
	// Get request by id
	@GetMapping("/{id}")
	public Request getRequest(@PathVariable("id") int id) {
		
		Request req = null;
		
		try {
			Connection connection = DriverManager.getConnection(getDbUrl(), getDbUsername(), getDbPassword());
			Statement stmt = connection.createStatement(); 
			ResultSet rs = stmt.executeQuery("SELECT * FROM Requests WHERE id = '" + id + "';");
			
			while(rs.next()) {
				req = new Request(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getDate(8));
			}
			
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return req;
	}
	
	//Get all requests with this user
	@GetMapping("/user+{userId}")
	public List<Request> getAllUserRequest(@PathVariable("userId") int userId) {
		
		List<Request> reqList = new ArrayList<>();
		
		try {
			Connection connection = DriverManager.getConnection(getDbUrl(), getDbUsername(), getDbPassword());
			Statement stmt = connection.createStatement(); 
			ResultSet rs = stmt.executeQuery("SELECT * FROM Requests WHERE userId = '" + userId + "';");
			
			while(rs.next()) {
				reqList.add(new Request(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getDate(8)));
			}
			
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return reqList;
	}
	
	//Get all requests with this volunteer
	@GetMapping("/volunteerId+{volunteerId}")
	public List<Request> getAllVolunteerRequest(@PathVariable("volunteerId") int volunteerId) {
		
		List<Request> reqList = new ArrayList<>();
		
		try {
			Connection connection = DriverManager.getConnection(getDbUrl(), getDbUsername(), getDbPassword());
			Statement stmt = connection.createStatement(); 
			ResultSet rs = stmt.executeQuery("SELECT * FROM Requests WHERE volunteerId = '" + volunteerId + "';");
			
			while(rs.next()) {
				reqList.add(new Request(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getDate(8)));
			}
			
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return reqList;
	}
	
	// Get all requests awaiting admin action from the database
	@GetMapping("/allWaitingRequests")
	public List<Request> getWaitingRequest() {
		
		List<Request> reqList = new ArrayList<>();
		
		try {
			Connection connection = DriverManager.getConnection(getDbUrl(), getDbUsername(), getDbPassword());
			Statement stmt = connection.createStatement(); 
			ResultSet rs = stmt.executeQuery("SELECT * FROM Requests WHERE status = 'pendingAdmin';");
			
			while(rs.next()) {
				reqList.add(new Request(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getDate(8)));
			}
			
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return reqList;
	}
	
	// Get all admin-accepted requests from the database
	@GetMapping("/allAcceptedRequests")
	public List<Request> getAcceptedRequests() {
			
		List<Request> reqList = new ArrayList<>();
			
		try {
			Connection connection = DriverManager.getConnection(getDbUrl(), getDbUsername(), getDbPassword());
			Statement stmt = connection.createStatement(); 
			ResultSet rs = stmt.executeQuery("SELECT * FROM Requests WHERE status = 'confirmed';");
				
			while(rs.next()) {
				reqList.add(new Request(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getDate(8)));
			}
				
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return reqList;
	}
	
	// Update a request
	@PutMapping("/updateRequest")
	public void updateHelpRequest(@RequestBody Request spontHelp) {
		try (Connection connection = DriverManager.getConnection(getDbUrl(), getDbUsername(), getDbPassword())) {
			// Usa un PreparedStatement para evitar problemas de formato y SQL Injection
			String sql = "UPDATE Requests SET title = ?, description = ?, status = ?, date = ? WHERE id = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);

			// Configura los parámetros
			stmt.setString(1, spontHelp.getTitle());
			stmt.setString(2, spontHelp.getDescription());
			stmt.setString(3, spontHelp.getStatus());

			// Asegúrate de que la fecha sea un java.util.Date o java.sql.Timestamp
			stmt.setTimestamp(4, new java.sql.Timestamp(spontHelp.getDate().getTime()));
			stmt.setInt(5, spontHelp.getId());

			// Ejecuta la actualización
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Update request STATUS
	@PutMapping("/updateStatus")
	public void updateRequestStatus(@RequestBody Request request) {
		try {
			Connection connection = DriverManager.getConnection(getDbUrl(), getDbUsername(), getDbPassword());
			Statement stmt = connection.createStatement();
			stmt.executeUpdate("UPDATE Requests SET status = '" + request.getStatus() + "', adminComment = '" + request.getAdminComment() + "' WHERE id = " + request.getId() + ";");

			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Post a new request
	@PostMapping("/postRequest")
	public void postHelpRequest(@RequestBody Request spontHelp) {
		String sql = "INSERT INTO Requests (userId, volunteerId, title, description, status, date) VALUES (?, ?, ?, ?, ?, ?)";

		try (Connection connection = DriverManager.getConnection(getDbUrl(), getDbUsername(), getDbPassword());
			 PreparedStatement pstmt = connection.prepareStatement(sql)) {

			// Configurar los parámetros del PreparedStatement
			pstmt.setInt(1, spontHelp.getUserId());
			pstmt.setInt(2, spontHelp.getVolunteerId());
			pstmt.setString(3, spontHelp.getTitle());
			pstmt.setString(4, spontHelp.getDescription());
			pstmt.setString(5, spontHelp.getStatus());

			// Convertir la fecha a un formato compatible con MySQL (YYYY-MM-DD)
			pstmt.setDate(6, new java.sql.Date(spontHelp.getDate().getTime()));

			// Ejecutar la consulta
			pstmt.executeUpdate();
			System.out.println("Help Request created successfully!");

		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Error inserting Help Request: " + e.getMessage());
		}
	}
}