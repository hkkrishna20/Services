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
@Entity(name = "django_session")
@Table(name = "django_session")

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "session_key", "session_data", "expire_date" })
public class DjangoSession implements Serializable {
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((expireDate == null) ? 0 : expireDate.hashCode());
		result = prime * result + ((sessionData == null) ? 0 : sessionData.hashCode());
		result = prime * result + ((sessionKey == null) ? 0 : sessionKey.hashCode());
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
		DjangoSession other = (DjangoSession) obj;
		if (expireDate == null) {
			if (other.expireDate != null)
				return false;
		} else if (!expireDate.equals(other.expireDate))
			return false;
		if (sessionData == null) {
			if (other.sessionData != null)
				return false;
		} else if (!sessionData.equals(other.sessionData))
			return false;
		if (sessionKey == null) {
			if (other.sessionKey != null)
				return false;
		} else if (!sessionKey.equals(other.sessionKey))
			return false;
		return true;
	}

	@Id
	@NotNull
	
	@Column(name="session_key")
	@JsonProperty("session_key")
	private String sessionKey;
	@Column(name="session_data")
	@JsonProperty("session_data")
	private Integer sessionData;
	@Column(name="expire_date")
	@JsonProperty("expire_date")
	private String expireDate;
	private final static long serialVersionUID = 6477153371428080373L;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public DjangoSession() {
	}

	/**
	 * 
	 * @param sessionData
	 * @param expireDate
	 * @param sessionKey
	 */
	public DjangoSession(String sessionKey, Integer sessionData, String expireDate) {
		super();
		this.sessionKey = sessionKey;
		this.sessionData = sessionData;
		this.expireDate = expireDate;
	}

	@JsonProperty("session_key")
	public String getSessionKey() {
		return sessionKey;
	}

	@JsonProperty("session_key")
	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}

	@JsonProperty("session_data")
	public Integer getSessionData() {
		return sessionData;
	}

	@JsonProperty("session_data")
	public void setSessionData(Integer sessionData) {
		this.sessionData = sessionData;
	}

	@JsonProperty("expire_date")
	public String getExpireDate() {
		return expireDate;
	}

	@JsonProperty("expire_date")
	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}


}
