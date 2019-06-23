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
@Entity(name = "auth_group_permissions")
@Table(name = "auth_group_permissions")

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "group_id", "permission_id" })
public class AuthGroupPermissions implements Serializable {
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((groupId == null) ? 0 : groupId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((permissionId == null) ? 0 : permissionId.hashCode());
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
		AuthGroupPermissions other = (AuthGroupPermissions) obj;
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
		if (permissionId == null) {
			if (other.permissionId != null)
				return false;
		} else if (!permissionId.equals(other.permissionId))
			return false;
		return true;
	}

	@Id
	@NotNull
	
	@Column(name ="id")
	@JsonProperty("id")
	private Integer id;
	@Column(name ="group_id")
	@JsonProperty("group_id")
	private Integer groupId;
	@Column(name = "permission_id")
	@JsonProperty("permission_id")
	private Integer permissionId;
	private final static long serialVersionUID = -8362872962201509819L;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public AuthGroupPermissions() {
	}

	/**
	 * 
	 * @param id
	 * @param groupId
	 * @param permissionId
	 */
	public AuthGroupPermissions(Integer id, Integer groupId, Integer permissionId) {
		super();
		this.id = id;
		this.groupId = groupId;
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

	@JsonProperty("group_id")
	public Integer getGroupId() {
		return groupId;
	}

	@JsonProperty("group_id")
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
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
