package com.cgi.integration.models;


import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

public class IntegrationAuditPoint extends DocumentModel {
	private String id;
	private String transXML;
	private String auditstagecode;

	private String reqid_FK;
	
	public String getReqid_FK() {
		return reqid_FK;
	}

	public void setReqid_FK(String reqid_FK) {
		this.reqid_FK = reqid_FK;
	}

	private String status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTransXML() {
		return transXML;
	}

	public void setTransXML(String transXML) {
		this.transXML = transXML;
	}

	public String getAuditstagecode() {
		return auditstagecode;
	}

	public void setAuditstagecode(String auditstagecode) {
		this.auditstagecode = auditstagecode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
