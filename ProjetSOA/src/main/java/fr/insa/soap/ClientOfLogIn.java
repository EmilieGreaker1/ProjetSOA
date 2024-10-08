package fr.insa.soap;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import fr.insa.soap.wsdltojava.LogInWS;
import fr.insa.soap.wsdltojava.LogIn;

public class ClientOfLogIn {
	public static void main(String [] args) throws MalformedURLException {
		final String adresse = "http://localhost:8088/LogIn";
		final URL url = URI.create(adresse).toURL();
		final LogIn service = new LogIn(url);
		final LogInWS port = service.getPort(LogInWS.class);
		String un = "Cool Unique Username";
		String pw = "password-secret";
		System.out.println("The user is " + port.getUser(un, pw).getUserId());
	}
}
