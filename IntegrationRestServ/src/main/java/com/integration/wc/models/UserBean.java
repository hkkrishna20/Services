package com.integration.wc.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

 

@Entity
@Table(name = "login")
public class UserBean {
	@Id
	@Column(name="user_Id")
	@NotEmpty(message="UserId cannot be empty")
	private String userId;
	@Column
	@NotEmpty(message="Password cannot be empty")
	private String password;
 
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
}