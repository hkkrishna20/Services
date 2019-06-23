package com.integration.wc.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "metadatadocument")
public class IntegrationMetadataDocument extends DocumentModel {

	@Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 255)
	private String docID;
	@Column(length = 255)
	private String reqid_FK;
	@Column(columnDefinition = "TEXT")
	private String docpath;

	@Column(columnDefinition = "TEXT")
	private String filenetpath;
	@Column(columnDefinition = "TEXT")
	private String ecmpath;
	public String getReqid_FK() {
		return reqid_FK;
	}

	public void setReqid_FK(String reqid_FK) {
		this.reqid_FK = reqid_FK;
	}


	public String getDocID() {
		return docID;
	}

	public void setDocID(String docID) {
		this.docID = docID;
	}

	public String getDocpath() {
		return docpath;
	}

	public void setDocpath(String docpath) {
		this.docpath = docpath;
	}

	public String getEcmpath() {
		return ecmpath;
	}

	public void setEcmpath(String ecmpath) {
		this.ecmpath = ecmpath;
	}

	public String getFilenetpath() {
		return filenetpath;
	}

	public void setFilenetpath(String filenetpath) {
		this.filenetpath = filenetpath;
	}



}
