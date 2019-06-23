package com.encomm.models.content.entityTypeP;

import java.util.List;

public class CompareList {

	private String fileName;
	List<String> baseVersionParaList;
	List<String> compareVersionParaList;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public List<String> getBaseVersionParaList() {
		return baseVersionParaList;
	}

	public void setBaseVersionParaList(List<String> baseVersionParaList) {
		this.baseVersionParaList = baseVersionParaList;
	}

	public List<String> getCompareVersionParaList() {
		return compareVersionParaList;
	}

	public void setCompareVersionParaList(List<String> compareVersionParaList) {
		this.compareVersionParaList = compareVersionParaList;
	}

	

}
