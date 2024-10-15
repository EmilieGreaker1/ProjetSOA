package fr.insa.soa.RestProject;

public class Stage {
	private int evaluation;
	private String competences;
	private int annee;
	
	public int getEvaluation() {
		return this.evaluation;
	}
	public String getCompetences() {
		return this.competences;
	}
	public int getAnnee() {
		return this.annee;
	}
	
	public void setEvaluation(int eval) {
		this.evaluation = eval;
	}
	public void setCompetences(String competences) {
		this.competences = competences;
	}
	public void setAnnee(int annee) {
		this.annee = annee;
	}
}
