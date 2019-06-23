package com.cgi.ewi.operationsmgmt.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonProperty;

@Embeddable
public class ExpirySummaryId implements Serializable {

	@Column(name="LOB")
	@JsonProperty("LOB")
	private String LOB;
	@Column(name="Manager_Name")
	@JsonProperty("Manager_Name")
	private String managerName;
	@Column(name="Director")
	@JsonProperty("Director")
	private String director;
	@Column(name="billing_end")
	@JsonProperty("billing_end")
	private String billingEnd;
	@Column(name="FY")
	@JsonProperty("FY")
	private String FY;

	public String getLOB() {
		return LOB;
	}

	public void setLOB(String lOB) {
		LOB = lOB;
	}

	
	public String getManagerName() {
		return managerName;
	}

	@Override
	public int hashCode() {
		return Objects.hash(LOB, billingEnd, director, FY, managerName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExpirySummaryId other = (ExpirySummaryId) obj;
		return Objects.equals(LOB, other.LOB) && Objects.equals(billingEnd, other.billingEnd)
				&& Objects.equals(director, other.director) && Objects.equals(FY, other.FY)
				&& Objects.equals(managerName, other.managerName);
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getBillingEnd() {
		return billingEnd;
	}

	public void setBillingEnd(String billingEnd) {
		this.billingEnd = billingEnd;
	}

	public String getfY() {
		return FY;
	}

	public void setfY(String fY) {
		this.FY = fY;
	}

	public ExpirySummaryId() {

	}


}