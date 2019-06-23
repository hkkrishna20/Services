package com.integration.wc.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "auditpoint")
public class IntegrationAuditPoint extends DocumentModel {

	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 255)
	private String id;

	@Lob
	@Column(columnDefinition = "LONGTEXT")
	private String transXML;
	@Column(length = 255)
	private String auditstagecode;
	@Column(length = 255)
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
