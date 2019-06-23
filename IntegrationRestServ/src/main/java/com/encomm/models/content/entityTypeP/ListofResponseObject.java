package com.encomm.models.content.entityTypeP;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "responses")
@XmlAccessorType(XmlAccessType.FIELD)
public class ListofResponseObject {
	@XmlElement
	private List<ResponseObject> responses = new ArrayList<ResponseObject>();

	public List<ResponseObject> getResponses() {
		return responses;
	}

	public void setResponses(List<ResponseObject> response) {
		this.responses = response;
	}

}
