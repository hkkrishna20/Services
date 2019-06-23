package com.cgi.ewi.dao;

import java.util.Date;
import java.util.TreeSet;

public class ChartRequest {

	private String buLead;
	private String groupLead;
	private Date fromDate;
	private Date toDate;
	private TreeSet<Integer> dateInterval;
	
	public String getBuLead() {
		return buLead;
	}
	public void setBuLead(String buLead) {
		this.buLead = buLead;
	}
	public String getGroupLead() {
		return groupLead;
	}
	public void setGroupLead(String groupLead) {
		this.groupLead = groupLead;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public TreeSet<Integer> getDateInterval() {
		return dateInterval;
	}
	public void setDateInterval(TreeSet<Integer> dateInterval) {
		this.dateInterval = dateInterval;
	}
	
	

	
}
