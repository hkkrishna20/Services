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
@Entity(name = "auth_user_groups")
@Table(name = "auth_user_groups")

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "user_id", "group_id" })
public class AuthUserGroups implements Serializable {
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((groupId == null) ? 0 : groupId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		AuthUserGroups other = (AuthUserGroups) obj;
		if (groupId == null) {
			if (other.groupId != null)
				return false;
		} else if (!groupId.equals(other.groupId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
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
	@Column(name="group_id")
	@JsonProperty("group_id")
	private Integer groupId;
	private final static long serialVersionUID = -5027606488721277319L;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public AuthUserGroups() {
	}

	/**
	 * 
	 * @param id
	 * @param groupId
	 * @param userId
	 */
	public AuthUserGroups(Integer id, Integer userId, Integer groupId) {
		super();
		this.id = id;
		this.userId = userId;
		this.groupId = groupId;
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

	@JsonProperty("group_id")
	public Integer getGroupId() {
		return groupId;
	}

	@JsonProperty("group_id")
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}


}
