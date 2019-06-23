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
@Entity(name = "director_expiry_fy")
@Table(name = "director_expiry_fy")

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "Director", "billing_end", "FY", "Expires_Count", "Renewals_Count" })
public class DirectorExpiryFy implements Serializable {
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((billingEnd == null) ? 0 : billingEnd.hashCode());
		result = prime * result + ((director == null) ? 0 : director.hashCode());
		result = prime * result + ((expiresCount == null) ? 0 : expiresCount.hashCode());
		result = prime * result + ((fY == null) ? 0 : fY.hashCode());
		result = prime * result + ((renewalsCount == null) ? 0 : renewalsCount.hashCode());
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
		DirectorExpiryFy other = (DirectorExpiryFy) obj;
		if (billingEnd == null) {
			if (other.billingEnd != null)
				return false;
		} else if (!billingEnd.equals(other.billingEnd))
			return false;
		if (director == null) {
			if (other.director != null)
				return false;
		} else if (!director.equals(other.director))
			return false;
		if (expiresCount == null) {
			if (other.expiresCount != null)
				return false;
		} else if (!expiresCount.equals(other.expiresCount))
			return false;
		if (fY == null) {
			if (other.fY != null)
				return false;
		} else if (!fY.equals(other.fY))
			return false;
		if (renewalsCount == null) {
			if (other.renewalsCount != null)
				return false;
		} else if (!renewalsCount.equals(other.renewalsCount))
			return false;
		return true;
	}

	@Id
	@NotNull
	
	@Column(name="Director")
	@JsonProperty("Director")
	private String director;
	@Column(name="billing_end")
	@JsonProperty("billing_end")
	private String billingEnd;
	@Column(name="FY")
	@JsonProperty("FY")
	private String fY;
	@Column(name="Expires_Count")
	@JsonProperty("Expires_Count")
	private Double expiresCount;
	@Column(name="Renewals_Count")
	@JsonProperty("Renewals_Count")
	private Double renewalsCount;
	private final static long serialVersionUID = 5748272381376405833L;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public DirectorExpiryFy() {
	}

	/**
	 * 
	 * @param fY
	 * @param expiresCount
	 * @param director
	 * @param billingEnd
	 * @param renewalsCount
	 */
	public DirectorExpiryFy(String director, String billingEnd, String fY, Double expiresCount, Double renewalsCount) {
		super();
		this.director = director;
		this.billingEnd = billingEnd;
		this.fY = fY;
		this.expiresCount = expiresCount;
		this.renewalsCount = renewalsCount;
	}

	@JsonProperty("Director")
	public String getDirector() {
		return director;
	}

	@JsonProperty("Director")
	public void setDirector(String director) {
		this.director = director;
	}

	@JsonProperty("billing_end")
	public String getBillingEnd() {
		return billingEnd;
	}

	@JsonProperty("billing_end")
	public void setBillingEnd(String billingEnd) {
		this.billingEnd = billingEnd;
	}

	@JsonProperty("FY")
	public String getFY() {
		return fY;
	}

	@JsonProperty("FY")
	public void setFY(String fY) {
		this.fY = fY;
	}

	@JsonProperty("Expires_Count")
	public Double getExpiresCount() {
		return expiresCount;
	}

	@JsonProperty("Expires_Count")
	public void setExpiresCount(Double expiresCount) {
		this.expiresCount = expiresCount;
	}

	@JsonProperty("Renewals_Count")
	public Double getRenewalsCount() {
		return renewalsCount;
	}

	@JsonProperty("Renewals_Count")
	public void setRenewalsCount(Double renewalsCount) {
		this.renewalsCount = renewalsCount;
	}



}
