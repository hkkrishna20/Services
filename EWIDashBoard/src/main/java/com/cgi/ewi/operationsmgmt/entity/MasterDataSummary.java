package com.cgi.ewi.operationsmgmt.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity(name = "master_data_summary")
@Table(name = "master_data_summary")
@JsonIgnoreProperties(ignoreUnknown = true)
public class MasterDataSummary implements Serializable {
	
	public MasterDataSummary(MasterDataSummaryId id, Integer categoryCount) {
		super();
		this.masterDataSummaryId = id;
		this.categoryCount = categoryCount;
	}

	@EmbeddedId
	MasterDataSummaryId masterDataSummaryId;
	
	@Override
	public int hashCode() {
		return Objects.hash(categoryCount, masterDataSummaryId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MasterDataSummary other = (MasterDataSummary) obj;
		return Objects.equals(categoryCount, other.categoryCount)
				&& Objects.equals(masterDataSummaryId, other.masterDataSummaryId);
	}

	@Column(name="Category_Count")
	private Integer categoryCount;
	
	public MasterDataSummary() {
	
	}

	@Override
	public String toString() {
		return "MasterDataSummary [masterDataSummaryId=" + masterDataSummaryId + ", categoryCount=" + categoryCount
				+ "]";
	}

	public MasterDataSummaryId getMasterDataSummaryId() {
		return masterDataSummaryId;
	}

	public void setMasterDataSummaryId(MasterDataSummaryId id) {
		this.masterDataSummaryId = id;
	}

	public Integer getCategoryCount() {
		return categoryCount;
	}

	public void setCategoryCount(Integer categoryCount) {
		this.categoryCount = categoryCount;
	}

	private final static long serialVersionUID = 4542434464490370183L;

}
