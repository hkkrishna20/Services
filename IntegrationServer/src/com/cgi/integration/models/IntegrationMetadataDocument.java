package com.cgi.integration.models;

public class IntegrationMetadataDocument extends DocumentModel {

	private String docID;

	private String docpath;

	private String reqid_FK;

	public String getReqid_FK() {
		return reqid_FK;
	}

	public void setReqid_FK(String reqid_FK) {
		this.reqid_FK = reqid_FK;
	}

	private String ecmpath;

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

	private String filenetpath;

}
