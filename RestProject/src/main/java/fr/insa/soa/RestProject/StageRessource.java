package fr.insa.soa.RestProject;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("stage")
public class StageRessource {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Stage getStage(int idEtudiant) {
		Stage stage = new Stage();
		stage.setEvaluation(16);
		stage.setCompetences("SOA, REST");
		stage.setAnnee(2021);
		return stage;
	}
}
