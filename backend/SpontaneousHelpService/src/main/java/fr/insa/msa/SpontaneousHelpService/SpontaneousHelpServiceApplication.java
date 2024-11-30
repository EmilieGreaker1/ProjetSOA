package fr.insa.msa.SpontaneousHelpService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class SpontaneousHelpServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpontaneousHelpServiceApplication.class, args);
		System.out.println("Spontaneous Help Service Activated!");
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**") // Permitir todas las rutas
						.allowedOrigins("http://localhost:9090", "http://127.0.0.1:9090") // Agrega tus orígenes permitidos
						.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Métodos HTTP permitidos
						.allowedHeaders("*") // Permitir todos los headers
						.allowCredentials(true); // Permitir cookies si es necesario
			}
		};
	}

}
