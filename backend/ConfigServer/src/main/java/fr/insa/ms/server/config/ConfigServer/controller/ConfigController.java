package fr.insa.ms.server.config.ConfigServer.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = {"http://127.0.0.1:9090", "http://localhost:9090"})
public class ConfigController {

    @Value("${server.port}")
    private String serverPort;

    //@CrossOrigin(origins = "http://localhost:63342")
    // @GetMapping("/config/server-port")client-service/dev
    // @GetMapping("/client-service/dev/server-port")    -- before
    @GetMapping("/config/server-port")
    public String getServerPort() {
        return serverPort;
    }
}
