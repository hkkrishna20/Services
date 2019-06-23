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
@Entity(name = "django_admin_log")
@Table(name = "django_admin_log")

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "action_time", "object_id", "object_repr", "action_flag", "change_message",
		"content_type_id", "user_id" })
public class DjangoAdminLog implements Serializable {
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((actionFlag == null) ? 0 : actionFlag.hashCode());
		result = prime * result + ((actionTime == null) ? 0 : actionTime.hashCode());
		result = prime * result + ((changeMessage == null) ? 0 : changeMessage.hashCode());
		result = prime * result + ((contentTypeId == null) ? 0 : contentTypeId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((objectId == null) ? 0 : objectId.hashCode());
		result = prime * result + ((objectRepr == null) ? 0 : objectRepr.hashCode());
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
		DjangoAdminLog other = (DjangoAdminLog) obj;
		if (actionFlag == null) {
			if (other.actionFlag != null)
				return false;
		} else if (!actionFlag.equals(other.actionFlag))
			return false;
		if (actionTime == null) {
			if (other.actionTime != null)
				return false;
		} else if (!actionTime.equals(other.actionTime))
			return false;
		if (changeMessage == null) {
			if (other.changeMessage != null)
				return false;
		} else if (!changeMessage.equals(other.changeMessage))
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
		if (objectId == null) {
			if (other.objectId != null)
				return false;
		} else if (!objectId.equals(other.objectId))
			return false;
		if (objectRepr == null) {
			if (other.objectRepr != null)
				return false;
		} else if (!objectRepr.equals(other.objectRepr))
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
	@Column(name="action_time")
	@JsonProperty("action_time")
	private String actionTime;
	@Column(name="object_id")
	@JsonProperty("object_id")
	private Integer objectId;
	@Column(name="object_repr")
	@JsonProperty("object_repr")
	private String objectRepr;
	@Column(name="action_flag")
	@JsonProperty("action_flag")
	private Integer actionFlag;
	@Column(name="change_message")
	@JsonProperty("change_message")
	private Integer changeMessage;
	@Column(name="content_type_id")
	@JsonProperty("content_type_id")
	private Integer contentTypeId;
	@Column(name="user_id")
	@JsonProperty("user_id")
	private Integer userId;
	private final static long serialVersionUID = -2539889039299440904L;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public DjangoAdminLog() {
	}

	/**
	 * 
	 * @param changeMessage
	 * @param id
	 * @param contentTypeId
	 * @param objectId
	 * @param userId
	 * @param actionFlag
	 * @param objectRepr
	 * @param actionTime
	 */
	public DjangoAdminLog(Integer id, String actionTime, Integer objectId, String objectRepr, Integer actionFlag,
			Integer changeMessage, Integer contentTypeId, Integer userId) {
		super();
		this.id = id;
		this.actionTime = actionTime;
		this.objectId = objectId;
		this.objectRepr = objectRepr;
		this.actionFlag = actionFlag;
		this.changeMessage = changeMessage;
		this.contentTypeId = contentTypeId;
		this.userId = userId;
	}

	@JsonProperty("id")
	public Integer getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(Integer id) {
		this.id = id;
	}

	@JsonProperty("action_time")
	public String getActionTime() {
		return actionTime;
	}

	@JsonProperty("action_time")
	public void setActionTime(String actionTime) {
		this.actionTime = actionTime;
	}

	@JsonProperty("object_id")
	public Integer getObjectId() {
		return objectId;
	}

	@JsonProperty("object_id")
	public void setObjectId(Integer objectId) {
		this.objectId = objectId;
	}

	@JsonProperty("object_repr")
	public String getObjectRepr() {
		return objectRepr;
	}

	@JsonProperty("object_repr")
	public void setObjectRepr(String objectRepr) {
		this.objectRepr = objectRepr;
	}

	@JsonProperty("action_flag")
	public Integer getActionFlag() {
		return actionFlag;
	}

	@JsonProperty("action_flag")
	public void setActionFlag(Integer actionFlag) {
		this.actionFlag = actionFlag;
	}

	@JsonProperty("change_message")
	public Integer getChangeMessage() {
		return changeMessage;
	}

	@JsonProperty("change_message")
	public void setChangeMessage(Integer changeMessage) {
		this.changeMessage = changeMessage;
	}

	@JsonProperty("content_type_id")
	public Integer getContentTypeId() {
		return contentTypeId;
	}

	@JsonProperty("content_type_id")
	public void setContentTypeId(Integer contentTypeId) {
		this.contentTypeId = contentTypeId;
	}

	@JsonProperty("user_id")
	public Integer getUserId() {
		return userId;
	}

	@JsonProperty("user_id")
	public void setUserId(Integer userId) {
		this.userId = userId;
	}


}
