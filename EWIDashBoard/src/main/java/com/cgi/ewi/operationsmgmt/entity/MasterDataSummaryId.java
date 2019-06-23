package com.cgi.ewi.operationsmgmt.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class MasterDataSummaryId implements Serializable {

	@Column(name = "LOB")
	private String lob;
	@Column(name = "Manager_Name")
	private String managername;
	public String getLob() {
		return lob;
	}
	public void setLob(String lob) {
		this.lob = lob;
	}
	public String getManagername() {
		return managername;
	}
	public void setManagername(String managername) {
		this.managername = managername;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getBillcategory() {
		return billcategory;
	}
	public void setBillcategory(String billcategory) {
		this.billcategory = billcategory;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getFy() {
		return fy;
	}
	public void setFy(String fy) {
		this.fy = fy;
	}
	@Column(name = "Director")
	private String director;
	@Column(name = "Billable_Category")
	private String billcategory;
	@Column(name = "Month")
	private String month;
	@Column(name = "FY")
	private String fy;
	@Override
	public int hashCode() {
		return Objects.hash(billcategory, director, fy, lob, managername, month);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MasterDataSummaryId other = (MasterDataSummaryId) obj;
		return Objects.equals(billcategory, other.billcategory) && Objects.equals(director, other.director)
				&& Objects.equals(fy, other.fy) && Objects.equals(lob, other.lob)
				&& Objects.equals(managername, other.managername) && Objects.equals(month, other.month);
	}
	@Override
	public String toString() {
		return "MasterDataSummaryId [lob=" + lob + ", managername=" + managername + ", director=" + director
				+ ", billcategory=" + billcategory + ", month=" + month + ", fy=" + fy + "]";
	}
	
	


}