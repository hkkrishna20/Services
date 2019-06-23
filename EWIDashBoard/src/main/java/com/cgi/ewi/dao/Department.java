package com.cgi.ewi.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity(name = "departments")
@Table(name = "departments")
public class Department {

	@Id
	@NotNull
	@Column(name = "\"Department ID\"")
	private String departmentId;
	@Column(name = "\"Department Description\"")
	private String departmentDesc;
	@Column(name = "\"Project Manager\"")
	private String projectManger;
	@Column(name = "\"Director\"")
	private String director;
	@Column(name = "\"Group Lead\"")
	private String groupLead;
	@Column(name = "\"BU Lead\"")
	private String buLead;
	@Column(name = "\"Pyramid Account\"")
	private String pyramidAccount;
	@Column(name = "\"HR BU\"")
	private String hrBu;
	
	
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentDesc() {
		return departmentDesc;
	}
	public void setDepartmentDesc(String departmentDesc) {
		this.departmentDesc = departmentDesc;
	}
	public String getProjectManger() {
		return projectManger;
	}
	public void setProjectManger(String projectManger) {
		this.projectManger = projectManger;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getGroupLead() {
		return groupLead;
	}
	public void setGroupLead(String groupLead) {
		this.groupLead = groupLead;
	}
	public String getBuLead() {
		return buLead;
	}
	public void setBuLead(String buLead) {
		this.buLead = buLead;
	}
	public String getPyramidAccount() {
		return pyramidAccount;
	}
	public void setPyramidAccount(String pyramidAccount) {
		this.pyramidAccount = pyramidAccount;
	}
	public String getHrBu() {
		return hrBu;
	}
	public void setHrBu(String hrBu) {
		this.hrBu = hrBu;
	}
	@Override
	public String toString() {
		return "Department [departmentId=" + departmentId + ", departmentDesc=" + departmentDesc + ", projectManger="
				+ projectManger + ", director=" + director + ", groupLead=" + groupLead + ", buLead=" + buLead
				+ ", pyramidAccount=" + pyramidAccount + ", hrBu=" + hrBu + "]";
	}
	
}
