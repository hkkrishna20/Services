package com.cgi.integration.models;

public class IntegrationAudit extends DocumentModel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String reqid;
    private String sourcecorelid;
    private String processfamily;
    private String content;

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}