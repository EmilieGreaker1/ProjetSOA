package fr.insa.msa.logInService.models;

public class User {
	
	private int userID;
	private String nickName;
	private String firstName;
	private String lastName;
	private String email;
	private String userType;
	private String password;
	
	public User(int userId, String nickName, String password) {
        this.userID = userId;
        this.nickName = nickName;
        this.password = password;
    }
	
	public User(int userId, String password, String nickName, String email, String userType, String firstName, String lastName) {
        this.userID = userId;
        this.nickName = nickName;
        this.password = password;
        this.userType = userType;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }
	
	public int getUserId() {
		return userID;
	}
	
	public String getNickName() {
		return nickName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getUserType() {
		return userType;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setUserId(int userId) {
		this.userID = userId;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setUserType(String accountType) {
		this.userType = accountType;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}