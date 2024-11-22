package fr.insa.mas.LogInService.model;

public class Account {
	private String userId;
	private String password;
	private String accountType;
	private String firstName;
	private String lastName;
	
	public Account(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }
	
	public Account(String userId, String password, String accountType, String firstName, String lastName) {
        this.userId = userId;
        this.password = password;
        this.accountType = accountType;
        this.firstName = firstName;
        this.lastName = lastName;
    }
	
	public String getUserId() {
		return userId;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getAccountType() {
		return accountType;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
