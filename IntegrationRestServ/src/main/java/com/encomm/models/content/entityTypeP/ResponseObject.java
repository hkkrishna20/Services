package com.encomm.models.content.entityTypeP;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "response")
@XmlAccessorType(XmlAccessType.FIELD)
public class ResponseObject {

	public int getFileId() {
		return fileId;
	}
	public void setFileId(int fileId) {
		this.fileId = fileId;
	}
	public String getFormId() {
		return formId;
	}
	public void setFormId(String formId) {
		this.formId = formId;
	}
	public String getFormTitle() {
		return formTitle;
	}
	public void setFormTitle(String formTitle) {
		this.formTitle = formTitle;
	}
	public float getVersionNo() {
		return versionNo;
	}
	public void setVersionNo(float versionNo) {
		this.versionNo = versionNo;
	}
	
	@XmlElement
	protected int fileId = 100;
	@XmlElement
	protected String formId;
	@XmlElement
	protected String formTitle;
	@XmlElement
	protected float versionNo;
	@XmlElement
	private List<ContentObj> paraType = new ArrayList<ContentObj>();
	public List<ContentObj> getParaType() {
		return paraType;
	}
	public void setParaType(List<ContentObj> paraType) {
		this.paraType = paraType;
	}

}
