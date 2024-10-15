package fr.insa.soa.RestProject;

public class Link {
	private String uri;
	private String rel;
	private String methode;
	
	public String getUri() {
		return uri;
	}
	public String getRel() {
		return rel;
	}
	public String getMethode() {
		return methode;
	}
	
	public void setUri(String uri) {
		this.uri = uri;
	}
	public void setRel(String rel) {
		this.rel = rel;
	}
	public void setMethode(String methode) {
		this.methode = methode;
	}
}
