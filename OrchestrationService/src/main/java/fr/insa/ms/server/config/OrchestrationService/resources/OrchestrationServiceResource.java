package fr.insa.ms.server.config.OrchestrationService.resources;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrchestrationServiceResource {
	
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
}
