package fr.insa.ms.signUP.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userID; // ID random
    private String firstName;
    private String lastName;
    private String email; // ID

    private String nickName; // ID
    private String password;
    private String userType;  // Volunteer or Other

    public User(String firstName, String lastName, String email, String password, String userType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.userType = userType;
        this.nickName = firstName + lastName;
    }

    public User() {

    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    // Anter de guardar el usuario genera el nickname
    /*
    @PrePersist
    private void generateNickName() {
        if (this.nickName == null || this.nickName.isEmpty()) {
            this.nickName = (this.firstName + this.lastName).replaceAll(" ", "");
        }
    }
    */

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }


}
