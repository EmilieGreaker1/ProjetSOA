package fr.insa.mas.studentListService.model;

public class Evaluation {
	private int idStudent;
	private float average;
	
	public Evaluation(int idStudent, float average) {
		this.idStudent = idStudent;
		this.average = average;
	}
	
	public Evaluation() {
	}
	
	public int getIdStudent() {
		return idStudent;
	}
	
	public void setIdStudent(int idStudent) {
		this.idStudent = idStudent;
	}
	
	public float getAverage() {
		return average;
	}
	
	public void setAverage(float average) {
		this.average = average;
	}
}
