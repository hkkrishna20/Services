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
@Entity(name = "ewi_account_summary")
@Table(name = "ewi_account_summary")

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "Pyramid_Account", "Month", "EWI_Category", "Category_Count" })
public class EwiAccountSummary implements Serializable {
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoryCount == null) ? 0 : categoryCount.hashCode());
		result = prime * result + ((eWICategory == null) ? 0 : eWICategory.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((month == null) ? 0 : month.hashCode());
		result = prime * result + ((pyramidAccount == null) ? 0 : pyramidAccount.hashCode());
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
		EwiAccountSummary other = (EwiAccountSummary) obj;
		if (categoryCount == null) {
			if (other.categoryCount != null)
				return false;
		} else if (!categoryCount.equals(other.categoryCount))
			return false;
		if (eWICategory == null) {
			if (other.eWICategory != null)
				return false;
		} else if (!eWICategory.equals(other.eWICategory))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (month == null) {
			if (other.month != null)
				return false;
		} else if (!month.equals(other.month))
			return false;
		if (pyramidAccount == null) {
			if (other.pyramidAccount != null)
				return false;
		} else if (!pyramidAccount.equals(other.pyramidAccount))
			return false;
		return true;
	}

	@Id
	@NotNull
	
	@Column(name="id")
	@JsonProperty("id")
	private Integer id;
	@Column(name="Pyramid_Account")
	@JsonProperty("Pyramid_Account")
	private String pyramidAccount;
	@Column(name="Month")
	@JsonProperty("Month")
	private String month;
	@Column(name="EWI_Category")
	@JsonProperty("EWI_Category")
	private String eWICategory;
	@Column(name="Category_Count")
	@JsonProperty("Category_Count")
	private Integer categoryCount;
	private final static long serialVersionUID = -822402778936304323L;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public EwiAccountSummary() {
	}

	/**
	 * 
	 * @param eWICategory
	 * @param id
	 * @param categoryCount
	 * @param pyramidAccount
	 * @param month
	 */
	public EwiAccountSummary(Integer id, String pyramidAccount, String month, String eWICategory,
			Integer categoryCount) {
		super();
		this.id = id;
		this.pyramidAccount = pyramidAccount;
		this.month = month;
		this.eWICategory = eWICategory;
		this.categoryCount = categoryCount;
	}

	@JsonProperty("id")
	public Integer getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(Integer id) {
		this.id = id;
	}

	@JsonProperty("Pyramid_Account")
	public String getPyramidAccount() {
		return pyramidAccount;
	}

	@JsonProperty("Pyramid_Account")
	public void setPyramidAccount(String pyramidAccount) {
		this.pyramidAccount = pyramidAccount;
	}

	@JsonProperty("Month")
	public String getMonth() {
		return month;
	}

	@JsonProperty("Month")
	public void setMonth(String month) {
		this.month = month;
	}

	@JsonProperty("EWI_Category")
	public String getEWICategory() {
		return eWICategory;
	}

	@JsonProperty("EWI_Category")
	public void setEWICategory(String eWICategory) {
		this.eWICategory = eWICategory;
	}

	@JsonProperty("Category_Count")
	public Integer getCategoryCount() {
		return categoryCount;
	}

	@JsonProperty("Category_Count")
	public void setCategoryCount(Integer categoryCount) {
		this.categoryCount = categoryCount;
	}

	@Override
	public String toString() {
		return "EwiAccountSummary [id=" + id + ", pyramidAccount=" + pyramidAccount + ", month=" + month
				+ ", eWICategory=" + eWICategory + ", categoryCount=" + categoryCount + "]";
	}
}
