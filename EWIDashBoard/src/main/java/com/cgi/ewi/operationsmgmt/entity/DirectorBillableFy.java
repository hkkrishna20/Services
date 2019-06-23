package com.cgi.ewi.operationsmgmt.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
@Entity(name = "director_billable_fy")
@Table(name = "director_billable_fy")

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "Director", "Billable_Category", "Month", "FY", "Category_Count" })
public class DirectorBillableFy implements Serializable {
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((billableCategory == null) ? 0 : billableCategory.hashCode());
		result = prime * result + ((categoryCount == null) ? 0 : categoryCount.hashCode());
		result = prime * result + ((director == null) ? 0 : director.hashCode());
		result = prime * result + ((fY == null) ? 0 : fY.hashCode());
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
		DirectorBillableFy other = (DirectorBillableFy) obj;
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
		if (director == null) {
			if (other.director != null)
				return false;
		} else if (!director.equals(other.director))
			return false;
		if (fY == null) {
			if (other.fY != null)
				return false;
		} else if (!fY.equals(other.fY))
			return false;
		if (month == null) {
			if (other.month != null)
				return false;
		} else if (!month.equals(other.month))
			return false;
		return true;
	}

	@Id
	@Column(name="Director")
	@JsonProperty("Director")
	private String director;
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
	private final static long serialVersionUID = -1852153976931036962L;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public DirectorBillableFy() {
	}

	/**
	 * 
	 * @param fY
	 * @param categoryCount
	 * @param month
	 * @param billableCategory
	 * @param director
	 */
	public DirectorBillableFy(String director, String billableCategory, String month, String fY, Double categoryCount) {
		super();
		this.director = director;
		this.billableCategory = billableCategory;
		this.month = month;
		this.fY = fY;
		this.categoryCount = categoryCount;
	}

	@JsonProperty("Director")
	public String getDirector() {
		return director;
	}

	@JsonProperty("Director")
	public void setDirector(String director) {
		this.director = director;
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
