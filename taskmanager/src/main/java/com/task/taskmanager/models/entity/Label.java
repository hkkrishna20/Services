package com.task.taskmanager.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "label")
@Table(name = "label")

public class Label {
	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public Label(String id) {
		super();
		this.id = id;
	}

	@Id
	@Column
	private String id;

	@Column
	private String taskid;

	public String getTaskid() {
		return taskid;
	}

	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		// TODO Auto-generated method stub
		return id;
	}

	// Hibernate requires a no-arg constructor
	public Label() {

	}
	@JsonIgnore
	@OneToOne( fetch = FetchType.EAGER, optional = false)
   // @OneToOne(mappedBy = "address")
	@JoinColumn(name = "id", nullable = false)
	private Task task;

	@Enumerated(EnumType.STRING)
	@Column(length = 10)
	private Categories category;

	public Categories getCategory() {
		return category;
	}

	public void setCategory(Categories category) {
		this.category = category;
	}
}
