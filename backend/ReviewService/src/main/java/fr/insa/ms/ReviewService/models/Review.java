package fr.insa.ms.ReviewService.models;

public class Review {
	
	private int id;
	private int userId;
	private int volId;
	private int rating;
	private String comment;
	
	public Review() {}
	
	public Review(int id, int userId, int volId, int rating, String comment) {
		this.id = id;
        this.userId = userId;
        this.volId = volId;
		this.rating = rating;
        this.comment = comment;
    }
	
	public int getId() {
		return id;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public int getVolunteerId() {
		return volId;
	}

	public int getRating() {
		return rating;
	}
	public String getComment() {
		return comment;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public void setVolunteerId(int volunteerId) {
		this.volId = volunteerId;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
}