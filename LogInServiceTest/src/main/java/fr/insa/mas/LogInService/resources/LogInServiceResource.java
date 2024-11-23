package fr.insa.mas.LogInService.resources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.insa.mas.LogInService.model.Account;

@RestController
@RequestMapping("/login")
public class LogInServiceResource {
	
	private Connection connection;
	
	@Value("${db.uri}")
    private String dbUri;

    @Value("${db.name}")
    private String dbName;

    @Value("${db.login}")
    private String dbLogin;

    @Value("${db.pwd}")
    private String dbPwd;
    
    @GetMapping("/dbUri")
    public String getDbUri() {
		return dbUri;
	}
	
	@GetMapping("/dbName")
	public String getDbName() {
		return dbName;
	}
	
	@GetMapping("/dbLogin")
	public String getDbLogin() {
		return dbLogin;
	}
	
	@GetMapping("/dbPwd")
	public String getDbPwd() {
		return dbPwd;
	}

	@GetMapping("/{userId}+{password}")
	public Account getUser(@PathVariable("userId") String userId, @PathVariable("password") String pwd) {
		
		// Get the user from the database based on name and password
		try {
			connection = DriverManager.getConnection(getDbUri(), getDbLogin(), getDbPwd());
			Statement stmt = connection.createStatement(); 
			ResultSet rs = stmt.executeQuery("select * from AccountInformation;");
			
			while(rs.next()) {
				System.out.print(rs.getString(1));
				if (userId.equals(rs.getString(1)) && pwd.equals(rs.getString(2))) {
					return new Account(userId, pwd, rs.getString(3), rs.getString(4), rs.getString(5));
				}
			}
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new Account("notFound", "notFound");
	}
}
