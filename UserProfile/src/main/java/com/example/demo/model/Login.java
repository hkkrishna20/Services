package com.example.demo.model;

//import javax.persistence.Column;

public class Login {
	
	private int user_account;
	private String user_password;
	public Login(int user_account, String user_password) {
		super();
		this.user_account = user_account;
		this.user_password = user_password;
	}
	public Login() {
		super();
	}
	public int getUser_account() {
		return user_account;
	}
	public void setUser_account(int user_account) {
		this.user_account = user_account;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	

	
}
