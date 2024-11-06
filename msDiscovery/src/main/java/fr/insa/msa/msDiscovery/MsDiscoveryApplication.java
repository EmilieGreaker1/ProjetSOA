package fr.insa.msa.msDiscovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MsDiscoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsDiscoveryApplication.class, args);
	}

}
