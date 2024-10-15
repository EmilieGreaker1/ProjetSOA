package fr.insa.soa.restClient;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.Response;

public class ClientRest {
	public static void main(String [] args) {
		//Instanciation du Client, necessaire pour l'exécution des requêtes et la consommation des résponses.
		Client client = ClientBuilder.newClient();
		//Appel du service Rest, invocation de la méthode get correspondant à l'URL
		Response response = client.target("http://localhost:8080/RestProject/webapi/comparator/longueur/Toulouse").request().get();
		//Lecture de la réponse récupérée
		System.out.println(response.readEntity(String.class));
	}
}
