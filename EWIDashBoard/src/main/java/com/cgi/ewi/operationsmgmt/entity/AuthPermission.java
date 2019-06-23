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
@Entity(name = "auth_permission")
@Table(name = "auth_permission")

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "name", "content_type_id", "codename" })
public class AuthPermission implements Serializable {
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codename == null) ? 0 : codename.hashCode());
		result = prime * result + ((contentTypeId == null) ? 0 : contentTypeId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		AuthPermission other = (AuthPermission) obj;
		if (codename == null) {
			if (other.codename != null)
				return false;
		} else if (!codename.equals(other.codename))
			return false;
		if (contentTypeId == null) {
			if (other.contentTypeId != null)
				return false;
		} else if (!contentTypeId.equals(other.contentTypeId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Id
	@NotNull
	
	@Column(name="id")
	@JsonProperty("id")
	private Integer id;
	@Column(name="name")
	@JsonProperty("name")
	private String name;
	@Column(name="content_type_id")
	@JsonProperty("content_type_id")
	private Integer contentTypeId;
	@Column(name="codename")
	@JsonProperty("codename")
	private String codename;
	private final static long serialVersionUID = -7021957592107607273L;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public AuthPermission() {
	}

	/**
	 * 
	 * @param id
	 * @param codename
	 * @param contentTypeId
	 * @param name
	 */
	public AuthPermission(Integer id, String name, Integer contentTypeId, String codename) {
		super();
		this.id = id;
		this.name = name;
		this.contentTypeId = contentTypeId;
		this.codename = codename;
	}

	@JsonProperty("id")
	public Integer getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(Integer id) {
		this.id = id;
	}

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("content_type_id")
	public Integer getContentTypeId() {
		return contentTypeId;
	}

	@JsonProperty("content_type_id")
	public void setContentTypeId(Integer contentTypeId) {
		this.contentTypeId = contentTypeId;
	}

	@JsonProperty("codename")
	public String getCodename() {
		return codename;
	}

	@JsonProperty("codename")
	public void setCodename(String codename) {
		this.codename = codename;
	}


}
