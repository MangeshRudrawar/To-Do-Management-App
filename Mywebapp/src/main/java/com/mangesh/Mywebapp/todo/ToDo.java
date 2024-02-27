package com.mangesh.Mywebapp.todo;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;

@Entity
public class ToDo {
	public ToDo() {
		
	}

	public ToDo(int id, String username, String description, LocalDate targetDate, boolean completionStatus) {
		super();
		this.id = id;
		this.username = username;
		this.description = description;
		this.targetDate = targetDate;
		this.CompletionStatus = completionStatus;
	}

	@Id
	@GeneratedValue
	private int id;
	private String username;

	@Size(min = 5, message = "Enter atleast 5 charachters")
	private String description;
	private LocalDate targetDate;
	private boolean CompletionStatus;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(LocalDate targetDate) {
		this.targetDate = targetDate;
	}

	public boolean isCompletionStatus() {
		return CompletionStatus;
	}

	public void setCompletionStatus(boolean completionStatus) {
		CompletionStatus = completionStatus;
	}

	@Override
	public String toString() {
		return "ToDo [id=" + id + ", username=" + username + ", description=" + description + ", targetDate="
				+ targetDate + ", CompletionStatus=" + CompletionStatus + "]";
	}

}
