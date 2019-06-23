package com.encomm.models.content.entityTypeP;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "contents")
@XmlAccessorType(XmlAccessType.FIELD)

public class ContentObj {
	
	public int getContentId() {
		return contentId;
	}
	public void setContentId(int contentId) {
		this.contentId = contentId;
	}
	public int getParaSeq() {
		return paraSeq;
	}
	public void setParaSeq(int paraSeq) {
		this.paraSeq = paraSeq;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@XmlElement
	protected int contentId;
	@XmlElement
	protected int paraSeq;
	@XmlElement
	protected String content;


}
