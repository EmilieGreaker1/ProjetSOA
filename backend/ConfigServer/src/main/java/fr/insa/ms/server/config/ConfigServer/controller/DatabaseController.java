package fr.insa.ms.server.config.ConfigServer.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseController {
    private final String url = "jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/projet_gei_026";
    private final String username = "projet_gei_026";

    private final String password = "EiGhai4l";

    Connection connection;

    //TODO: Handle exception when connections fails
    //TODO: Handle exception when email already exists in database
    //CONSTRUCTOR: Tries to make the database connection with the username and password
    public DatabaseController() throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(url, username, password);

    }
}
