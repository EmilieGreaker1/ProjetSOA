package fr.insa.msa.SpontaneousHelpService.resources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import java.sql.PreparedStatement; // Import necesario
import java.sql.Timestamp; // Para manejar fechas

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.insa.msa.SpontaneousHelpService.models.SpontaneousHelp;

@RestController
@CrossOrigin(origins = {"http://127.0.0.1:9090", "http://localhost:9090"})
@RequestMapping("/spontHelp")
public class SpontaneousHelpResources {
	
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
	
	// Get SpontaneousHelp by id
	@GetMapping("/{id}")
	public SpontaneousHelp getSpontHelp(@PathVariable("id") int id) {
			
		SpontaneousHelp spontHelp = null;
			
		try {
			Connection connection = DriverManager.getConnection(getDbUrl(), getDbUsername(), getDbPassword());
			Statement stmt = connection.createStatement(); 
			ResultSet rs = stmt.executeQuery("SELECT * FROM SpontaneousHelp WHERE id = '" + id + "';");
			
			while(rs.next()) {
				spontHelp = new SpontaneousHelp(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getDate(8));
			}
				
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return spontHelp;
	}
		
	//Get all SpontaneousHelp with this user
	@GetMapping("/user+{userId}")
	public List<SpontaneousHelp> getAllUserSpontHelp(@PathVariable("userId") int userId) {
			
		List<SpontaneousHelp> spontHelpList = new ArrayList<>();
			
		try {
			Connection connection = DriverManager.getConnection(getDbUrl(), getDbUsername(), getDbPassword());
			Statement stmt = connection.createStatement(); 
			ResultSet rs = stmt.executeQuery("SELECT * FROM SpontaneousHelp WHERE userId = '" + userId + "';");
			
			while(rs.next()) {
				spontHelpList.add(new SpontaneousHelp(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getDate(8)));
			}
			
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return spontHelpList;
	}
		
	//Get all SpontaneousHelp with this volunteer
	@GetMapping("/volunteer+{volunteerId}")
	public List<SpontaneousHelp> getAllVolunteerSpontHelp(@PathVariable("volunteerId") int volunteerId) {
			
		List<SpontaneousHelp> spontHelpList = new ArrayList<>();
			
		try {
			Connection connection = DriverManager.getConnection(getDbUrl(), getDbUsername(), getDbPassword());
			Statement stmt = connection.createStatement(); 
			ResultSet rs = stmt.executeQuery("SELECT * FROM SpontaneousHelp WHERE volunteerId = '" + volunteerId + "' AND status = 'CONFIRMED';");
				
			while(rs.next()) {
				spontHelpList.add(new SpontaneousHelp(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getDate(8)));
			}
				
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return spontHelpList;
	}
	
	// Get all SpontaneousHelp awaiting admin action from the database
	@GetMapping("/allWaitingSpontaneousHelp")
	public List<SpontaneousHelp> getWaitingSpontaneousHelp() {
		
		List<SpontaneousHelp> spontHelpList = new ArrayList<>();
		
		try {
			Connection connection = DriverManager.getConnection(getDbUrl(), getDbUsername(), getDbPassword());
			Statement stmt = connection.createStatement(); 
			ResultSet rs = stmt.executeQuery("SELECT * FROM SpontaneousHelp WHERE status = 'pendingAdmin';");
			
			while(rs.next()) {
				spontHelpList.add(new SpontaneousHelp(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getDate(8)));
			}
			
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return spontHelpList;
	}
	
	// Get all admin-accepted SpontaneousHelp from the database
	@GetMapping("/allAcceptedSpontaneousHelp")
	public List<SpontaneousHelp> getAcceptedSpontaneousHelp() {
			
		List<SpontaneousHelp> spontHelpList = new ArrayList<>();
			
		try {
			Connection connection = DriverManager.getConnection(getDbUrl(), getDbUsername(), getDbPassword());
			Statement stmt = connection.createStatement(); 
			ResultSet rs = stmt.executeQuery("SELECT * FROM SpontaneousHelp WHERE status = 'CONFIRMED' AND volunteerId IS NULL;");
				
			while(rs.next()) {
				spontHelpList.add(new SpontaneousHelp(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getDate(8)));
			}
				
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return spontHelpList;
	}
	
	// Update a spontaneous help proposition
	@PutMapping("/updateSpontHelp")
	public void updateSpontaneousHelp(@RequestBody SpontaneousHelp spontHelp) {
		try (Connection connection = DriverManager.getConnection(getDbUrl(), getDbUsername(), getDbPassword())) {
			// Usa un PreparedStatement para evitar problemas de formato y SQL Injection
			String sql = "UPDATE SpontaneousHelp SET title = ?, description = ?, status = ?, date = ? WHERE id = ?";
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

	// Update spontHelp STATUS
	@PutMapping("/updateStatus")
	public void updateSpontaneousHelpStatus(@RequestBody SpontaneousHelp spontHelp) {
		try {
			Connection connection = DriverManager.getConnection(getDbUrl(), getDbUsername(), getDbPassword());
			Statement stmt = connection.createStatement();
			stmt.executeUpdate("UPDATE SpontaneousHelp SET status = '" + spontHelp.getStatus() + "', adminComment = '" + spontHelp.getAdminComment() + "' WHERE id = " + spontHelp.getId() + ";");

			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Update spontHelp VOLUNTEER
	@PutMapping("/updateVolunteer")
	public void updateSpontaneousHelpVolunteer(@RequestBody SpontaneousHelp spontHelp) {
		try {
			Connection connection = DriverManager.getConnection(getDbUrl(), getDbUsername(), getDbPassword());
			Statement stmt = connection.createStatement();
			stmt.executeUpdate("UPDATE SpontaneousHelp SET volunteerId = '" + spontHelp.getVolunteerId() + "' WHERE id = " + spontHelp.getId() + ";");

			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Post a new spontaneous help proposition
	@PostMapping("/postSpontHelp")
	public void postSpontaneousHelp(@RequestBody SpontaneousHelp spontHelp) {
		String sql = "INSERT INTO SpontaneousHelp (userId, volunteerId, title, description, status, date) VALUES (?, ?, ?, ?, ?, ?)";

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
			System.out.println("Spontaneous help created successfully!");

		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Error inserting spontaneous help: " + e.getMessage());
		}
	}
}