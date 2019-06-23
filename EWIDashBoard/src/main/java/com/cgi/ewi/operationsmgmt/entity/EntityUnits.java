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
@Entity(name = "entity_units")
@Table(name = "entity_units")

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "entity_id", "entity_name", "short_name", "parent_id", "entity_type", "entity_owner" })
public class EntityUnits implements Serializable {
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((entityId == null) ? 0 : entityId.hashCode());
		result = prime * result + ((entityName == null) ? 0 : entityName.hashCode());
		result = prime * result + ((entityOwner == null) ? 0 : entityOwner.hashCode());
		result = prime * result + ((entityType == null) ? 0 : entityType.hashCode());
		result = prime * result + ((parentId == null) ? 0 : parentId.hashCode());
		result = prime * result + ((shortName == null) ? 0 : shortName.hashCode());
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
		EntityUnits other = (EntityUnits) obj;
		if (entityId == null) {
			if (other.entityId != null)
				return false;
		} else if (!entityId.equals(other.entityId))
			return false;
		if (entityName == null) {
			if (other.entityName != null)
				return false;
		} else if (!entityName.equals(other.entityName))
			return false;
		if (entityOwner == null) {
			if (other.entityOwner != null)
				return false;
		} else if (!entityOwner.equals(other.entityOwner))
			return false;
		if (entityType == null) {
			if (other.entityType != null)
				return false;
		} else if (!entityType.equals(other.entityType))
			return false;
		if (parentId == null) {
			if (other.parentId != null)
				return false;
		} else if (!parentId.equals(other.parentId))
			return false;
		if (shortName == null) {
			if (other.shortName != null)
				return false;
		} else if (!shortName.equals(other.shortName))
			return false;
		return true;
	}

	@Id
	@NotNull
	
	@Column(name="entity_id")
	@JsonProperty("entity_id")
	private Integer entityId;
	@Column(name="entity_name")
	@JsonProperty("entity_name")
	private String entityName;
	@Column(name="short_name")
	@JsonProperty("short_name")
	private String shortName;
	@Column(name="parent_id")
	@JsonProperty("parent_id")
	private Integer parentId;
	@Column(name="entity_type")
	@JsonProperty("entity_type")
	private String entityType;
	@Column(name="entity_owner")
	@JsonProperty("entity_owner")
	private String entityOwner;
	private final static long serialVersionUID = -58477320977501711L;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public EntityUnits() {
	}

	/**
	 * 
	 * @param parentId
	 * @param entityId
	 * @param entityType
	 * @param shortName
	 * @param entityOwner
	 * @param entityName
	 */
	public EntityUnits(Integer entityId, String entityName, String shortName, Integer parentId, String entityType,
			String entityOwner) {
		super();
		this.entityId = entityId;
		this.entityName = entityName;
		this.shortName = shortName;
		this.parentId = parentId;
		this.entityType = entityType;
		this.entityOwner = entityOwner;
	}

	@JsonProperty("entity_id")
	public Integer getEntityId() {
		return entityId;
	}

	@JsonProperty("entity_id")
	public void setEntityId(Integer entityId) {
		this.entityId = entityId;
	}

	@JsonProperty("entity_name")
	public String getEntityName() {
		return entityName;
	}

	@JsonProperty("entity_name")
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	@JsonProperty("short_name")
	public String getShortName() {
		return shortName;
	}

	@JsonProperty("short_name")
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	@JsonProperty("parent_id")
	public Integer getParentId() {
		return parentId;
	}

	@JsonProperty("parent_id")
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	@JsonProperty("entity_type")
	public String getEntityType() {
		return entityType;
	}

	@JsonProperty("entity_type")
	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	@JsonProperty("entity_owner")
	public String getEntityOwner() {
		return entityOwner;
	}

	@JsonProperty("entity_owner")
	public void setEntityOwner(String entityOwner) {
		this.entityOwner = entityOwner;
	}


}
