package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "profile")
public class Profile {
	@Id
	@Column
	private int user_account;
	@Column
	private String user_name;
	@Column
	private String user_password;
	@Column
	private int age;
	@Column
	private String mobile_no;
	@Column
	private String email_id;
	
	public Profile(int user_account, String user_name, String user_password, int age, String mobile_no,
			String email_id) {
		super();
		this.user_account = user_account;
		this.user_name = user_name;
		this.user_password = user_password;
		this.age = age;
		this.mobile_no = mobile_no;
		this.email_id = email_id;
	}
	
	

	public Profile() {
		super();
	}



	public int getUser_account() {
		return user_account;
	}

	public void setUser_account(int user_account) {
		this.user_account = user_account;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getMobile_no() {
		return mobile_no;
	}

	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}

	public String getEmail_id() {
		return email_id;
	}

	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}



	@Override
	public String toString() {
		return "Profile [user_account=" + user_account + ", user_name=" + user_name + ", user_password=" + user_password
				+ ", age=" + age + ", mobile_no=" + mobile_no + ", email_id=" + email_id + "]";
	}
	
	
}
