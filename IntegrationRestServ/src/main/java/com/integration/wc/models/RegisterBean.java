package com.integration.wc.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "register")
public class RegisterBean {
	@Id
	@Column(name="user_Id")
	@NotEmpty(message="UserId cannot be empty")
	private String userId;
	@Column
	@NotEmpty(message="Password cannot be empty")
	private String password;
	@Column
	private String email;
	@Column
	private String name;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

}
