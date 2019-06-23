package com.task.taskmanager.models.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "task")
@Table(name = "task", uniqueConstraints = { @UniqueConstraint(columnNames = { "id" }) })

public class Task {
	@Id
	@Column(name = "id")
	private String id;

	@NotNull
	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	public Task(String id, @NotNull String name, String description, String content, String deadline, Label label,
			User user) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.content = content;
		this.deadline = deadline;
		this.label = label;
		this.user = user;
	}

	@Column(name = "content")
	private String content;
	
	@Column(name = "deadline")
	private String deadline;

	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String value) {
		this.name = value;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String value) {
		this.description = value;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(final String value) {
		this.content = value;
	}

	

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		// TODO Auto-generated method stub
		return id;
	}

	// Hibernate requires a no-arg constructor
	public Task() {

	}

	@OneToOne(mappedBy="task", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Label label;
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;
	@Column
	private String usero;

	public String getUsero() {
		return usero;
	}

	public void setUsero(String usero) {
		this.usero = usero;
	}

	public Label getLabel() {
		return label;
	}

	public void setLabel(Label label) {
		this.label = label;
	}

}
