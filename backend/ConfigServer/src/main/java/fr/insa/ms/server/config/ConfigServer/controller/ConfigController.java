package fr.insa.ms.server.config.ConfigServer.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/config/server-port")
    public String getServerPort() {
        return serverPort;
    }
}
