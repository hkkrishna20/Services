package com.cgi.ewi.dao;

import java.util.Arrays;

public class InnerChart {
	
	private String name;
	private String stack;
	private Integer[] data;
	private String type;
	private String color;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStack() {
		return stack;
	}
	public void setStack(String stack) {
		this.stack = stack;
	}
	public Integer[] getData() {
		return data;
	}
	public void setData(Integer[] data) {
		this.data = data;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	@Override
	public String toString() {
		return "InnerChart [name=" + name + ", stack=" + stack + ", data=" + Arrays.toString(data) + ", type=" + type
				+ ", color=" + color + "]";
	}
	
	
	public InnerChart(String name, String stack, Integer[] data, String type, String color) {
		super();
		this.name = name;
		this.stack = stack;
		this.data = data;
		this.type = type;
		this.color = color;
	}
}