package fr.insa.soap;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;

@XmlRootElement // Marks this class as a root element in the XML schema
@XmlAccessorType(XmlAccessType.FIELD) // Uses fields for XML binding
public class User {
	private String userId;
	private String password;
	
	public User() {
		this.userId = "Greaker";
        this.password = "secret";
	}
	
	public User(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }
	
	public String getUserId() {
		return this.userId;
	}
	
	public String getPassword() {
		return this.userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}
