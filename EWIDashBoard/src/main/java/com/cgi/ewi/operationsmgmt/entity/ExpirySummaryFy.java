package com.cgi.ewi.operationsmgmt.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
@Entity(name = "expiry_summary_fy")
@Table(name = "expiry_summary_fy")

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "LOB", "Manager_Name", "Director", "billing_end", "FY", "SOW_Expires", "SOW_Renewals" })
public class ExpirySummaryFy implements Serializable {
	
	
	@EmbeddedId
	ExpirySummaryId expirySummaryId;
	

	@Column(name="SOW_Expires")
	@JsonProperty("SOW_Expires")
	private Double sOWExpires;
	@Column(name="SOW_Renewals")
	@JsonProperty("SOW_Renewals")
	private Double sOWRenewals;
	private final static long serialVersionUID = 4532668580785130701L;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public ExpirySummaryFy() {
	}

	/**
	 * 
	 * @param fY
	 * @param sOWRenewals
	 * @param managerName
	 * @param sOWExpires
	 * @param lOB
	 * @param director
	 * @param billingEnd
	 */


	public ExpirySummaryId getExpirySummaryId() {
		return expirySummaryId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(expirySummaryId, sOWExpires, sOWRenewals);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExpirySummaryFy other = (ExpirySummaryFy) obj;
		return Objects.equals(expirySummaryId, other.expirySummaryId) && Objects.equals(sOWExpires, other.sOWExpires)
				&& Objects.equals(sOWRenewals, other.sOWRenewals);
	}

	public ExpirySummaryFy(ExpirySummaryId expirySummaryId, Double sOWExpires, Double sOWRenewals) {
		super();
		this.expirySummaryId = expirySummaryId;
		this.sOWExpires = sOWExpires;
		this.sOWRenewals = sOWRenewals;
	}

	public void setExpirySummaryId(ExpirySummaryId expirySummaryId) {
		this.expirySummaryId = expirySummaryId;
	}

	public Double getsOWExpires() {
		return sOWExpires;
	}

	public void setsOWExpires(Double sOWExpires) {
		this.sOWExpires = sOWExpires;
	}

	public Double getsOWRenewals() {
		return sOWRenewals;
	}

	public void setsOWRenewals(Double sOWRenewals) {
		this.sOWRenewals = sOWRenewals;
	}

}
