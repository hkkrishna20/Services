package com.cgi.ewi.dao;

public class SelectionItem {
	private String value;
	private String viewValue;

	public SelectionItem(String string, String string2) {
		
		this.setValue(string);
		this.setViewValue(string2);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getViewValue() {
		return viewValue;
	}

	public void setViewValue(String viewValue) {
		this.viewValue = viewValue;
	}
}