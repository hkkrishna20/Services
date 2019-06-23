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
@JsonPropertyOrder({ "series" })
public class PlotOptions implements Serializable {

	@JsonProperty("series")
	private Series series;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	private final static long serialVersionUID = -3521790089056725866L;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public PlotOptions() {
	}

	/**
	 * 
	 * @param series
	 */
	public PlotOptions(Series series) {
		super();
		this.series = series;
	}

	@JsonProperty("series")
	public Series getSeries() {
		return series;
	}

	@JsonProperty("series")
	public void setSeries(Series series) {
		this.series = series;
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
		return new ToStringBuilder(this).append("series", series).append("additionalProperties", additionalProperties)
				.toString();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(series).append(additionalProperties).toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof PlotOptions) == false) {
			return false;
		}
		PlotOptions rhs = ((PlotOptions) other);
		return new EqualsBuilder().append(series, rhs.series).append(additionalProperties, rhs.additionalProperties)
				.isEquals();
	}

}
