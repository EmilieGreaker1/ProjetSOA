package fr.insa.msa.AdminService.models;

public class SpontaneousHelp {
	
	private int id;
	private int userId;
	private int volunteerId;
	private String text;
	private String status;
	private String adminComment;
	private String time;
	
	public SpontaneousHelp(int id, int volunteerId, String text) {
		this.id = id;
        this.volunteerId = volunteerId;
        this.text = text;
        this.status = "pendingAdmin";
    }
	
	public SpontaneousHelp(int id, int userId, int volunteerId, String text, String status, String adminComment, String time) {
        this.id = id;
        this.userId = userId;
        this.volunteerId = volunteerId;
        this.text = text;
        this.status = status;
        this.adminComment = adminComment;
        this.time = time;
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
	
	public String getText() {
		return text;
	}
	
	public String getStatus() {
		return status;
	}
	
	public String getAdminComment() {
		return adminComment;
	}
	
	public String getTime() {
		return time;
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
	
	public void setText(String text) {
		this.text = text;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public void setAdminComment(String admComment) {
		this.adminComment = admComment;
	}
	
	public void setTime(String time) {
		this.time = time;
	}
}