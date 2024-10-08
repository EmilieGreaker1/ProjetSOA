package fr.insa.soap;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;

@XmlRootElement // Marks this class as a root element in the XML schema
@XmlAccessorType(XmlAccessType.FIELD) // Uses fields for XML binding
public class User {
    private String email;
    private String password;
    private String lastName;
    private String firstName;
    private String userType;

    public User() {
  //      this.userId = "nothing";
  //      this.password = "nothingPassword";
    }

    public User(String firstName, String lastName, String email, String userType, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userType = userType;
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}