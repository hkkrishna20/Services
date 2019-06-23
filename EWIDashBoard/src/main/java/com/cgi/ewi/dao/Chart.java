package com.cgi.ewi.dao;

import java.util.Arrays;

public class Chart {
	
	private String[] dynamicXaxi;
    private InnerChart[] innerChart;
    
	public String[] getDynamicXaxi() {
		return dynamicXaxi;
	}
	public void setDynamicXaxi(String[] dynamicXaxi) {
		this.dynamicXaxi = dynamicXaxi;
	}
	public InnerChart[] getInnerChart() {
		return innerChart;
	}
	public void setInnerChart(InnerChart[] innerChart) {
		this.innerChart = innerChart;
	}
	public Chart(String[] dynamicXaxi, InnerChart[] innerChart) {
		super();
		this.dynamicXaxi = dynamicXaxi;
		this.innerChart = innerChart;
	}
	@Override
	public String toString() {
		return "Chart [dynamicXaxi=" + Arrays.toString(dynamicXaxi) + ", innerChart=" + Arrays.toString(innerChart)
				+ "]";
	}
	
	
 }

