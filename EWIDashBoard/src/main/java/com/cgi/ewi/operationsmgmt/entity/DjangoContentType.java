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
@Entity(name = "django_content_type")
@Table(name = "django_content_type")

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "app_label", "model" })
public class DjangoContentType implements Serializable {
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((appLabel == null) ? 0 : appLabel.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((model == null) ? 0 : model.hashCode());
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
		DjangoContentType other = (DjangoContentType) obj;
		if (appLabel == null) {
			if (other.appLabel != null)
				return false;
		} else if (!appLabel.equals(other.appLabel))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		return true;
	}

	@Id
	@NotNull
	
	@Column(name="id")
	@JsonProperty("id")
	private Integer id;
	@Column(name="app_label")
	@JsonProperty("app_label")
	private String appLabel;
	@Column(name="model")
	@JsonProperty("model")
	private String model;
	private final static long serialVersionUID = -4195227582279446948L;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public DjangoContentType() {
	}

	/**
	 * 
	 * @param id
	 * @param model
	 * @param appLabel
	 */
	public DjangoContentType(Integer id, String appLabel, String model) {
		super();
		this.id = id;
		this.appLabel = appLabel;
		this.model = model;
	}

	@JsonProperty("id")
	public Integer getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(Integer id) {
		this.id = id;
	}

	@JsonProperty("app_label")
	public String getAppLabel() {
		return appLabel;
	}

	@JsonProperty("app_label")
	public void setAppLabel(String appLabel) {
		this.appLabel = appLabel;
	}

	@JsonProperty("model")
	public String getModel() {
		return model;
	}

	@JsonProperty("model")
	public void setModel(String model) {
		this.model = model;
	}



}
