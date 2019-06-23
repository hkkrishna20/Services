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
@Entity(name = "django_migrations")
@Table(name = "django_migrations")

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "app", "name", "applied" })
public class DjangoMigrations implements Serializable {
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((app == null) ? 0 : app.hashCode());
		result = prime * result + ((applied == null) ? 0 : applied.hashCode());
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
		DjangoMigrations other = (DjangoMigrations) obj;
		if (app == null) {
			if (other.app != null)
				return false;
		} else if (!app.equals(other.app))
			return false;
		if (applied == null) {
			if (other.applied != null)
				return false;
		} else if (!applied.equals(other.applied))
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
	@Column(name="app")
	@JsonProperty("app")
	private String app;
	@Column(name="name")
	@JsonProperty("name")
	private String name;
	@Column(name="applied")
	@JsonProperty("applied")
	private String applied;
	private final static long serialVersionUID = 296317111406763549L;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public DjangoMigrations() {
	}

	/**
	 * 
	 * @param id
	 * @param app
	 * @param applied
	 * @param name
	 */
	public DjangoMigrations(Integer id, String app, String name, String applied) {
		super();
		this.id = id;
		this.app = app;
		this.name = name;
		this.applied = applied;
	}

	@JsonProperty("id")
	public Integer getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(Integer id) {
		this.id = id;
	}

	@JsonProperty("app")
	public String getApp() {
		return app;
	}

	@JsonProperty("app")
	public void setApp(String app) {
		this.app = app;
	}

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("applied")
	public String getApplied() {
		return applied;
	}

	@JsonProperty("applied")
	public void setApplied(String applied) {
		this.applied = applied;
	}



}
