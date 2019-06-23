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
@Entity(name = "lob_billable_fy")
@Table(name = "lob_billable_fy")

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "LOB", "Billable_Category", "Month", "FY", "Category_Count" })
public class LobBillableFy implements Serializable {
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((billableCategory == null) ? 0 : billableCategory.hashCode());
		result = prime * result + ((categoryCount == null) ? 0 : categoryCount.hashCode());
		result = prime * result + ((fY == null) ? 0 : fY.hashCode());
		result = prime * result + ((lOB == null) ? 0 : lOB.hashCode());
		result = prime * result + ((month == null) ? 0 : month.hashCode());
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
		LobBillableFy other = (LobBillableFy) obj;
		if (billableCategory == null) {
			if (other.billableCategory != null)
				return false;
		} else if (!billableCategory.equals(other.billableCategory))
			return false;
		if (categoryCount == null) {
			if (other.categoryCount != null)
				return false;
		} else if (!categoryCount.equals(other.categoryCount))
			return false;
		if (fY == null) {
			if (other.fY != null)
				return false;
		} else if (!fY.equals(other.fY))
			return false;
		if (lOB == null) {
			if (other.lOB != null)
				return false;
		} else if (!lOB.equals(other.lOB))
			return false;
		if (month == null) {
			if (other.month != null)
				return false;
		} else if (!month.equals(other.month))
			return false;
		return true;
	}

	@Id
	@NotNull
	
	@Column(name="LOB")
	@JsonProperty("LOB")
	private String lOB;
	@Column(name="Billable_Category")
	@JsonProperty("Billable_Category")
	private String billableCategory;
	@Column(name="Month")
	@JsonProperty("Month")
	private String month;
	@Column(name="FY")
	@JsonProperty("FY")
	private String fY;
	@Column(name="Category_Count")
	@JsonProperty("Category_Count")
	private Double categoryCount;
	private final static long serialVersionUID = 3280122352454814039L;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public LobBillableFy() {
	}

	/**
	 * 
	 * @param fY
	 * @param categoryCount
	 * @param month
	 * @param lOB
	 * @param billableCategory
	 */
	public LobBillableFy(String lOB, String billableCategory, String month, String fY, Double categoryCount) {
		super();
		this.lOB = lOB;
		this.billableCategory = billableCategory;
		this.month = month;
		this.fY = fY;
		this.categoryCount = categoryCount;
	}

	@JsonProperty("LOB")
	public String getLOB() {
		return lOB;
	}

	@JsonProperty("LOB")
	public void setLOB(String lOB) {
		this.lOB = lOB;
	}

	@JsonProperty("Billable_Category")
	public String getBillableCategory() {
		return billableCategory;
	}

	@JsonProperty("Billable_Category")
	public void setBillableCategory(String billableCategory) {
		this.billableCategory = billableCategory;
	}

	@JsonProperty("Month")
	public String getMonth() {
		return month;
	}

	@JsonProperty("Month")
	public void setMonth(String month) {
		this.month = month;
	}

	@JsonProperty("FY")
	public String getFY() {
		return fY;
	}

	@JsonProperty("FY")
	public void setFY(String fY) {
		this.fY = fY;
	}

	@JsonProperty("Category_Count")
	public Double getCategoryCount() {
		return categoryCount;
	}

	@JsonProperty("Category_Count")
	public void setCategoryCount(Double categoryCount) {
		this.categoryCount = categoryCount;
	}

	
}
