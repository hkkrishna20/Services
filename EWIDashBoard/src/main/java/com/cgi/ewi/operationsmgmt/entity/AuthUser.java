package com.cgi.ewi.operationsmgmt.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
@Entity(name = "auth_user")
@Table(name = "auth_user")

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "password", "last_login", "is_superuser", "username", "first_name", "last_name", "email",
		"is_staff", "is_active", "date_joined" })
public class AuthUser implements Serializable {
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateJoined == null) ? 0 : dateJoined.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((isActive == null) ? 0 : isActive.hashCode());
		result = prime * result + ((isStaff == null) ? 0 : isStaff.hashCode());
		result = prime * result + ((isSuperuser == null) ? 0 : isSuperuser.hashCode());
		result = prime * result + ((lastLogin == null) ? 0 : lastLogin.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AuthUser other = (AuthUser) obj;
		if (dateJoined == null) {
			if (other.dateJoined != null)
				return false;
		} else if (!dateJoined.equals(other.dateJoined))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isActive == null) {
			if (other.isActive != null)
				return false;
		} else if (!isActive.equals(other.isActive))
			return false;
		if (isStaff == null) {
			if (other.isStaff != null)
				return false;
		} else if (!isStaff.equals(other.isStaff))
			return false;
		if (isSuperuser == null) {
			if (other.isSuperuser != null)
				return false;
		} else if (!isSuperuser.equals(other.isSuperuser))
			return false;
		if (lastLogin == null) {
			if (other.lastLogin != null)
				return false;
		} else if (!lastLogin.equals(other.lastLogin))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Id
	@NotNull
	
	@Column(name="id")
	@JsonProperty("id")
	private Integer id;
	@Column(name="password")
	@JsonProperty("password")
	private String password;
	@Column(name="last_login")
	@JsonProperty("last_login")
	private String lastLogin;
	@Column(name="is_superuser")
	@JsonProperty("is_superuser")
	private Integer isSuperuser;
	@Column(name="username")
	@JsonProperty("username")
	private String username;
	@Column(name="first_name")
	@JsonProperty("first_name")
	private String firstName;
	@Column(name="last_name")
	@JsonProperty("last_name")
	private String lastName;
	@Column(name="email")
	@JsonProperty("email")
	private String email;
	@Column(name="is_staff")
	@JsonProperty("is_staff")
	private String isStaff;
	@Column(name="is_active")
	@JsonProperty("is_active")
	private String isActive;
	@Column(name="date_joined")
	@JsonProperty("date_joined")
	private String dateJoined;
	private final static long serialVersionUID = -1848938234568872530L;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public AuthUser() {
	}

	/**
	 * 
	 * @param isActive
	 * @param id
	 * @param lastName
	 * @param username
	 * @param lastLogin
	 * @param email
	 * @param isSuperuser
	 * @param dateJoined
	 * @param isStaff
	 * @param firstName
	 * @param password
	 */
	public AuthUser(Integer id, String password, String lastLogin, Integer isSuperuser, String username,
			String firstName, String lastName, String email, String isStaff, String isActive, String dateJoined) {
		super();
		this.id = id;
		this.password = password;
		this.lastLogin = lastLogin;
		this.isSuperuser = isSuperuser;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.isStaff = isStaff;
		this.isActive = isActive;
		this.dateJoined = dateJoined;
	}

	@JsonProperty("id")
	public Integer getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(Integer id) {
		this.id = id;
	}

	@JsonProperty("password")
	public String getPassword() {
		return password;
	}

	@JsonProperty("password")
	public void setPassword(String password) {
		this.password = password;
	}

	@JsonProperty("last_login")
	public String getLastLogin() {
		return lastLogin;
	}

	@JsonProperty("last_login")
	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}

	@JsonProperty("is_superuser")
	public Integer getIsSuperuser() {
		return isSuperuser;
	}

	@JsonProperty("is_superuser")
	public void setIsSuperuser(Integer isSuperuser) {
		this.isSuperuser = isSuperuser;
	}

	@JsonProperty("username")
	public String getUsername() {
		return username;
	}

	@JsonProperty("username")
	public void setUsername(String username) {
		this.username = username;
	}

	@JsonProperty("first_name")
	public String getFirstName() {
		return firstName;
	}

	@JsonProperty("first_name")
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@JsonProperty("last_name")
	public String getLastName() {
		return lastName;
	}

	@JsonProperty("last_name")
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@JsonProperty("email")
	public String getEmail() {
		return email;
	}

	@JsonProperty("email")
	public void setEmail(String email) {
		this.email = email;
	}

	@JsonProperty("is_staff")
	public String getIsStaff() {
		return isStaff;
	}

	@JsonProperty("is_staff")
	public void setIsStaff(String isStaff) {
		this.isStaff = isStaff;
	}

	@JsonProperty("is_active")
	public String getIsActive() {
		return isActive;
	}

	@JsonProperty("is_active")
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	@JsonProperty("date_joined")
	public String getDateJoined() {
		return dateJoined;
	}

	@JsonProperty("date_joined")
	public void setDateJoined(String dateJoined) {
		this.dateJoined = dateJoined;
	}


}
