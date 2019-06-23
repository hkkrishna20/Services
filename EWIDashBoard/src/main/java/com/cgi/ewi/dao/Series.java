package com.cgi.ewi.dao;

import java.io.Serializable;
import java.util.HashMap;
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
@JsonPropertyOrder({ "stacking" })
public class Series implements Serializable {

	@JsonProperty("stacking")
	private String stacking;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	private final static long serialVersionUID = 549351806955929464L;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public Series() {
	}

	/**
	 * 
	 * @param stacking
	 */
	public Series(String stacking) {
		super();
		this.stacking = stacking;
	}

	@JsonProperty("stacking")
	public String getStacking() {
		return stacking;
	}

	@JsonProperty("stacking")
	public void setStacking(String stacking) {
		this.stacking = stacking;
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
		return new ToStringBuilder(this).append("stacking", stacking)
				.append("additionalProperties", additionalProperties).toString();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(additionalProperties).append(stacking).toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof Series) == false) {
			return false;
		}
		Series rhs = ((Series) other);
		return new EqualsBuilder().append(additionalProperties, rhs.additionalProperties).append(stacking, rhs.stacking)
				.isEquals();
	}

}
