package fr.insa.soap;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;

@XmlRootElement // Marks this class as a root element in the XML schema
@XmlAccessorType(XmlAccessType.FIELD) // Uses fields for XML binding
public class User {
	private String userId;
	private String password;
	private String lastName;
	private String firstName;
	private String userType;
	
	public User() {
		this.userId = "nothing";
        this.password = "nothingPassword";
	}
	
	public User(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public void setUserType(String userType) {
		this.userType = userType;
	}
}
