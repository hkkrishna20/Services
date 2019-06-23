package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "blacklist")
public class BlackList {
	@Id
	@Column(name = "bemail_id")
	private String bemailId;

	public BlackList(String bemailId) {
		super();
		this.bemailId = bemailId;
	}

	public BlackList() {
		super();
	}

	public String getBemailId() {
		return bemailId;
	}

	public void setBemailId(String bemailId) {
		this.bemailId = bemailId;
	}

	@Override
	public String toString() {
		return "BlackList [bemailId=" + bemailId + "]";
	}
	
	
}
