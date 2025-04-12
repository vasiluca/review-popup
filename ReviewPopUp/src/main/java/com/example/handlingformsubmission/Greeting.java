package com.example.handlingformsubmission;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Greeting {
    @Id
    private long id;
	private String name; // Optional if doing bug report, or Provider does not display Name publicly for reviews
	private String email; // optional
	private String feedback;
	private boolean isBugReport;
	private String content;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public void setIsBugReport(boolean isBugReport) {
		this.isBugReport = isBugReport;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
