package com.cgi.integration.models;

public class IntegrationMetadata extends DocumentModel {

	private String reqid;

	private String sourcecorelid;

	private String processfamily;

	private String accountno;
	private String userid;

	public String getReqid() {
		return reqid;
	}

	public void setReqid(String reqid) {
		this.reqid = reqid;
	}

	public String getSourcecorelid() {
		return sourcecorelid;
	}

	public void setSourcecorelid(String sourcecorelid) {
		this.sourcecorelid = sourcecorelid;
	}

	public String getProcessfamily() {
		return processfamily;
	}

	public void setProcessfamily(String processfamily) {
		this.processfamily = processfamily;
	}

}