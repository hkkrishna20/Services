package com.example.demo.model;

public class Profile2 {
	private int user_account;
	private String email_id;
	
	public Profile2(int user_account, String email_id) {
		super();
		this.user_account = user_account;
		this.email_id = email_id;
	}

	public Profile2() {
		super();
	}

	public int getUser_account() {
		return user_account;
	}

	public void setUser_account(int user_account) {
		this.user_account = user_account;
	}

	public String getEmail_id() {
		return email_id;
	}

	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}

	@Override
	public String toString() {
		return "Profile2 [user_account=" + user_account + ", email_id=" + email_id + "]";
	}
	
	
}
