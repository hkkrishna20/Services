package com.cgi.ewi.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Entity(name = "ewi_manager_summary")
@Table(name = "ewi_manager_summary")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "Pyramid_Account", "Month", "RM Name", "EWI_Category", "Category_Count" })
public class EwiManagerSummary implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "id", nullable = true, insertable = true, updatable = true, unique = true)
	@Id
	@NotNull
	@JsonProperty("id")
	private String id;

	@Column(name = "Pyramid_Account")
	@JsonProperty("Pyramid_Account")
	private String pyramidAccount;

	@Column(name = "Month")
	@JsonProperty("Month")
	private String month;

	@Column(name = "\"RM Name\"")
	@JsonProperty("RM Name")
	private String rMName;

	@Column(name = "EWI_Category")
	@JsonProperty("EWI_Category")
	private String eWICategory;

	@Column(name = "Category_Count")
	@JsonProperty("Category_Count")
	private String categoryCount;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPyramidAccount() {
		return pyramidAccount;
	}

	public void setPyramidAccount(String pyramidAccount) {
		this.pyramidAccount = pyramidAccount;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getrMName() {
		return rMName;
	}

	public void setrMName(String rMName) {
		this.rMName = rMName;
	}

	public String geteWICategory() {
		return eWICategory;
	}

	public void seteWICategory(String eWICategory) {
		this.eWICategory = eWICategory;
	}

	public String getCategoryCount() {
		return categoryCount;
	}

	public void setCategoryCount(String categoryCount) {
		this.categoryCount = categoryCount;
	}

	@Override
	public String toString() {
		return "EwiManagerSummary [id=" + id + ", pyramidAccount=" + pyramidAccount + ", month=" + month + ", rMName="
				+ rMName + ", eWICategory=" + eWICategory + ", categoryCount=" + categoryCount + "]";
	}

	
}