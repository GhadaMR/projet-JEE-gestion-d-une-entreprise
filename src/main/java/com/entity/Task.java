package com.entity;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="task")
public class Task implements Serializable{

	private String code;
	private String description;
	@JsonbDateFormat("yyyy-MM-dd")
	private LocalDate startDate;
	@JsonbDateFormat("yyyy-MM-dd")
	private LocalDate endDate;
	@JsonbProperty
	private Project project;

	public Task() {
		super();
	}

	public Task(String code, String description, LocalDate startDate, LocalDate endDate, Project project) {
		super();
		this.code = code;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.project = project;
	}

	@ManyToOne
	@JoinColumn(name="ProjFk")
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}


	@Id
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "Task [code=" + code + ", description=" + description + ", startDate=" + startDate + ", endDate=" + endDate
				+ "]";
	}

}
