package fr.insa.msa.AdminService.resources;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.insa.msa.AdminService.models.Request;
import fr.insa.msa.AdminService.models.SpontaneousHelp;

@RestController
@CrossOrigin(origins = {"http://127.0.0.1:9090", "http://localhost:9090"})
@RequestMapping("/admin")
public class AdminResources {
	
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
	
	// Get all Requests awaiting admin action from the database
	@GetMapping("/allWaitingRequests")
	public List<Request> getRequests() {
		
		List<Request> reqList = new ArrayList<>();
		
		try {
			Connection connection = DriverManager.getConnection(getDbUrl(), getDbUsername(), getDbPassword());
			Statement stmt = connection.createStatement(); 
			ResultSet rs = stmt.executeQuery("SELECT * FROM Requests WHERE status = 'pendingAdmin';");
			
			while(rs.next()) {
				reqList.add(new Request(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)));
			}
			
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return reqList;
	}
	
	@PutMapping("/updateReq")
	public void updateRequest(@RequestBody Request req) {
		try {
			Connection connection = DriverManager.getConnection(getDbUrl(), getDbUsername(), getDbPassword());
			Statement stmt = connection.createStatement(); 
			stmt.executeQuery("UPDATE Requests SET status = '" + req.getStatus() + "', adminComment = '" + req.getAdminComment() + "' WHERE id = " + req.getId());
			
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Get all SpontaneousHelp awaiting admin action from the database
	@GetMapping("/allWaitingSpontaneousHelp")
	public List<SpontaneousHelp> getSpontaneousHelp() {
		
		List<SpontaneousHelp> spontHelpList = new ArrayList<>();
		
		try {
			Connection connection = DriverManager.getConnection(getDbUrl(), getDbUsername(), getDbPassword());
			Statement stmt = connection.createStatement(); 
			ResultSet rs = stmt.executeQuery("SELECT * FROM SpontaneousHelp WHERE status = 'pendingAdmin';");
			
			while(rs.next()) {
				spontHelpList.add(new SpontaneousHelp(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)));
			}
			
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return spontHelpList;
	}
	
	@PutMapping("/updateSpontHelp")
	public void updateSpontaneousHelp(@RequestBody SpontaneousHelp spontHelp) {
		try {
			Connection connection = DriverManager.getConnection(getDbUrl(), getDbUsername(), getDbPassword());
			Statement stmt = connection.createStatement(); 
			stmt.executeQuery("UPDATE SpontaneousHelp SET status = '" + spontHelp.getStatus() + "', adminComment = '" + spontHelp.getAdminComment() + "' WHERE id = " + spontHelp.getId());
			
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}