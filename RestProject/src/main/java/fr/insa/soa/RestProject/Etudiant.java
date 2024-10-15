package fr.insa.soa.RestProject;

import java.util.ArrayList;

public class Etudiant {
	private int id;
	private String nom;
	private String prenom;
	private Binome binome;
	private Stage stage;
	private ArrayList<Link> links = new ArrayList<Link>();
	
	public int getId() {
		return this.id;
	}
	public String getNom() {
		return this.nom;
	}
	public String getPrenom() {
		return this.prenom;
	}
	public Binome getBinome() {
		return this.binome;
	}
	public Stage getStage() {
		return this.stage;
	}
	public ArrayList<Link> getLinks() {
		return this.links;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public void setBinome(Binome binome) {
		this.binome = binome;
	}
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	public void addLink(String uri, String rel, String methode) {
		Link newLink = new Link();
		newLink.setUri(uri);
		newLink.setRel(rel);
		newLink.setMethode(methode);
		this.links.add(newLink);
	}
}
