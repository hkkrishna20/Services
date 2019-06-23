package com.encomm.models.content.entityTypeP;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "mail")
@XmlAccessorType(XmlAccessType.FIELD)
public class MailObject {
	@XmlElement
	protected String toEmail;
	@XmlElement
	protected String ccEmail;
	@XmlElement
	protected String bccEmail;

	/**
	 * @return the toEmail
	 */
	public String getToEmail() {
		return toEmail;
	}

	/**
	 * @param toEmail
	 *            the toEmail to set
	 */
	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}

	/**
	 * @return the ccEmail
	 */
	public String getCcEmail() {
		return ccEmail;
	}

	/**
	 * @param ccEmail
	 *            the ccEmail to set
	 */
	public void setCcEmail(String ccEmail) {
		this.ccEmail = ccEmail;
	}

	/**
	 * @return the bccEmail
	 */
	public String getBccEmail() {
		return bccEmail;
	}

	/**
	 * @param bccEmail
	 *            the bccEmail to set
	 */
	public void setBccEmail(String bccEmail) {
		this.bccEmail = bccEmail;
	}

}
