package fr.insa.msa.RequestService.models;

import java.util.Date;
public class Request {
	
	private int id;
	private int userId;
	private int volunteerId;
	private String title;
	private String description;
	private String status;
	private String adminComment;
	private Date date;
	
	public Request() {}
	
	public Request(int id, int userId, String title, String description) {
		this.id = id;
        this.userId = userId;
		this.title = title;
        this.description = description;
        this.status = "pendingAdmin";
    }
	
	public Request(int id, int userId, int volunteerId, String title, String description, String status, String adminComment, Date date) {
        this.id = id;
        this.userId = userId;
        this.volunteerId = volunteerId;
		this.title = title;
        this.description = description;
        this.status = status;
        this.adminComment = adminComment;
        this.date = date;
    }
	
	public int getId() {
		return id;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public int getVolunteerId() {
		return volunteerId;
	}

	public String getTitle() {
		return title;
	}
	public String getDescription() {
		return description;
	}
	
	public String getStatus() {
		return status;
	}
	
	public String getAdminComment() {
		return adminComment;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public void setVolunteerId(int volunteerId) {
		this.volunteerId = volunteerId;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public void setAdminComment(String admComment) {
		this.adminComment = admComment;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
}