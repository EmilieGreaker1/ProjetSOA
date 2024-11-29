package fr.insa.ms.signUP.Controller;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    @Bean
    public DataSource dataSource(DataSourceProperties properties) {
        try {
            // Intenta configurar la conexión a la base de datos
            return properties.initializeDataSourceBuilder().build();
        } catch (Exception e) {
            System.err.println("La conexión a la base de datos falló: " + e.getMessage());
            System.err.println("El servicio seguirá funcionando sin la base de datos.");
            return null; // No se inicializa el DataSource
        }
    }
}