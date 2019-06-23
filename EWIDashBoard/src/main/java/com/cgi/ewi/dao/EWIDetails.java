package com.cgi.ewi.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity(name = "ewi_details")
@Table(name = "ewi_details")

public class EWIDetails {

	@Id
	@NotNull
	@Column(name = "\"id\"")
	private String id;
	
	@Column(name = "\"PSA ID\"")
	private String psaId;
	
	@Column(name = "\"Member Name\"")
	private String memberName;
	
	@Column(name = "\"RM Name\"")
	private String rmName;
	
	@Column(name = "\"Total Exp\"")
	private String totalExp;
	
	@Column(name = "\"EWI_SCORE\"")
	private String ewiScore;
	
	@Column(name = "\"EWI_Category\"")
	private String ewiCategory;
	
	@Column(name = "\"Month\"")
	private String month;
	
	@Column(name = "\"Pyramid_Account\"")
	private String pyramidAccount;
	
	public String getEwiCategory() {
		return ewiCategory;
	}

	public void setEwiCategory(String ewiCategory) {
		this.ewiCategory = ewiCategory;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPsaId() {
		return psaId;
	}

	public void setPsaId(String psaId) {
		this.psaId = psaId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getRmName() {
		return rmName;
	}

	public void setRmName(String rmName) {
		this.rmName = rmName;
	}

	public String getTotalExp() {
		return totalExp;
	}

	public void setTotalExp(String totalExp) {
		this.totalExp = totalExp;
	}

	public String getEwiScore() {
		return ewiScore;
	}

	public void setEwiScore(String ewiScore) {
		this.ewiScore = ewiScore;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getPyramidAccount() {
		return pyramidAccount;
	}

	public void setPyramidAccount(String pyramidAccount) {
		this.pyramidAccount = pyramidAccount;
	}

	@Override
	public String toString() {
		return "EWIDetails [id=" + id + ", psaId=" + psaId + ", memberName=" + memberName + ", rmName=" + rmName
				+ ", totalExp=" + totalExp + ", ewiScore=" + ewiScore + ", ewiCategory=" + ewiCategory + ", month="
				+ month + ", pyramidAccount=" + pyramidAccount + "]";
	}

	

}
