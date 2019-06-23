package com.cgi.ewi.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "categories" })
public class XAxis implements Serializable {

	@JsonProperty("categories")
	private List<String> categories = null;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	private final static long serialVersionUID = -2958095500218108764L;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public XAxis() {
	}

	/**
	 * 
	 * @param categories
	 */
	public XAxis(List<String> categories) {
		super();
		this.categories = categories;
	}

	@JsonProperty("categories")
	public List<String> getCategories() {
		return categories;
	}

	@JsonProperty("categories")
	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("categories", categories)
				.append("additionalProperties", additionalProperties).toString();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(additionalProperties).append(categories).toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof XAxis) == false) {
			return false;
		}
		XAxis rhs = ((XAxis) other);
		return new EqualsBuilder().append(additionalProperties, rhs.additionalProperties)
				.append(categories, rhs.categories).isEquals();
	}

}