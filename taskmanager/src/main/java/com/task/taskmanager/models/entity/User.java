package com.task.taskmanager.models.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "user")
@Table(name = "user", uniqueConstraints = { @UniqueConstraint(columnNames = { "id" }) })

public class User {
	@Id
	@Column
	private String id;

	public User(String id, @NotNull String username, @NotNull String password, @NotNull String email) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getId() {
		// TODO Auto-generated method stub
		return id;
	}

	@NotNull
	@Column(name = "username")
	private String username;

	@NotNull
	@Column(name = "password")
	private String password;

	@NotNull
	@Column(name = "email")
	private String email;
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = false)
	private List<Task> listTasks;

	// Hibernate requires a no-arg constructor
	public User() {

	}

	public List<Task> getListTasks() {
		return listTasks;
	}

	public void setListTasks(List<Task> listTasks) {
		this.listTasks = listTasks;
	}
}
