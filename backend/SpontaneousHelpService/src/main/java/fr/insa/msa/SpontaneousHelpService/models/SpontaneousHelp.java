package fr.insa.msa.SpontaneousHelpService.models;

import java.util.Date;
public class SpontaneousHelp {
	
	private int id;
	private int userId;
	private int volunteerId;
	private String title;
	private String description;
	private String status;
	private String adminComment;
	private Date date;
	
	public SpontaneousHelp() {}
	
	public SpontaneousHelp(int id, int volunteerId, String title) {
		this.id = id;
        this.volunteerId = volunteerId;
        this.title = title;
        this.status = "pendingAdmin";
    }
	
	public SpontaneousHelp(int id, int userId, int volunteerId, String title, String description, String status, String adminComment, Date date) {
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
	public void setId(int id) {
		this.id = id;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public int getVolunteerId() {
		return volunteerId;
	}
	public void setVolunteerId(int volunteerId) {
		this.volunteerId = volunteerId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getAdminComment() {
		return adminComment;
	}
	public void setAdminComment(String admComment) {
		this.adminComment = admComment;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}