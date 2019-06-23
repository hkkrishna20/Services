package com.integration.wc.models;

public class ResponseTable {
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getRequestTimeStamp() {
		return requestTimeStamp;
	}
	public void setRequestTimeStamp(String requestTimeStamp) {
		this.requestTimeStamp = requestTimeStamp;
	}
	public String getTestType() {
		return testType;
	}
	public void setTestType(String testType) {
		this.testType = testType;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getDocId() {
		return docId;
	}
	public void setDocId(String docId) {
		this.docId = docId;
	}
	public String getDocPathUrl() {
		return docPathUrl;
	}
	public void setDocPathUrl(String docPathUrl) {
		this.docPathUrl = docPathUrl;
	}
	String requestId;
	String requestTimeStamp;
	String testType;
	String channel;
	String docId;
	String docPathUrl;

}
