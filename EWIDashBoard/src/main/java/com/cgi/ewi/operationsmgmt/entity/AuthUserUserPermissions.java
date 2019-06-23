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
@Entity(name = "auth_user_user_permissions")
@Table(name = "auth_user_user_permissions")

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "user_id", "permission_id" })
public class AuthUserUserPermissions implements Serializable {
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((permissionId == null) ? 0 : permissionId.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		AuthUserUserPermissions other = (AuthUserUserPermissions) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (permissionId == null) {
			if (other.permissionId != null)
				return false;
		} else if (!permissionId.equals(other.permissionId))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	@Id
	@NotNull
	
	@Column(name="id")
	@JsonProperty("id")
	private Integer id;
	@Column(name="user_id")
	@JsonProperty("user_id")
	private Integer userId;
	@Column(name="permission_id")
	@JsonProperty("permission_id")
	private Integer permissionId;
	private final static long serialVersionUID = 3202519593971542655L;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public AuthUserUserPermissions() {
	}

	/**
	 * 
	 * @param id
	 * @param userId
	 * @param permissionId
	 */
	public AuthUserUserPermissions(Integer id, Integer userId, Integer permissionId) {
		super();
		this.id = id;
		this.userId = userId;
		this.permissionId = permissionId;
	}

	@JsonProperty("id")
	public Integer getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(Integer id) {
		this.id = id;
	}

	@JsonProperty("user_id")
	public Integer getUserId() {
		return userId;
	}

	@JsonProperty("user_id")
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@JsonProperty("permission_id")
	public Integer getPermissionId() {
		return permissionId;
	}

	@JsonProperty("permission_id")
	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}


}
