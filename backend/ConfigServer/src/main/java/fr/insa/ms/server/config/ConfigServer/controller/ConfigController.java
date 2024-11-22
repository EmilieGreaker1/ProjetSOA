package fr.insa.ms.server.config.ConfigServer.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigController {

    @Value("${server.port}")
    private String serverPort;

    //@CrossOrigin(origins = "http://localhost:63342")
    @GetMapping("/config/server-port")
    public String getServerPort() {
        return serverPort;
    }
}
