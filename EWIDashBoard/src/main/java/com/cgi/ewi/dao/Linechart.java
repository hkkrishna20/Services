
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
@JsonPropertyOrder({ "xAxis", "plotOptions", "series" })
public class Linechart implements Serializable {

	@JsonProperty("xAxis")
	private XAxis xAxis;
	@JsonProperty("plotOptions")
	private PlotOptions plotOptions;
	@JsonProperty("series")
	private List<Series_> series = null;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	private final static long serialVersionUID = -5300942870487540668L;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public Linechart() {
	}

	/**
	 * 
	 * @param plotOptions
	 * @param series
	 * @param xAxis
	 */
	public Linechart(XAxis xAxis, PlotOptions plotOptions, List<Series_> series) {
		super();
		this.xAxis = xAxis;
		this.plotOptions = plotOptions;
		this.series = series;
	}

	@JsonProperty("xAxis")
	public XAxis getXAxis() {
		return xAxis;
	}

	@JsonProperty("xAxis")
	public void setXAxis(XAxis xAxis) {
		this.xAxis = xAxis;
	}

	@JsonProperty("plotOptions")
	public PlotOptions getPlotOptions() {
		return plotOptions;
	}

	@JsonProperty("plotOptions")
	public void setPlotOptions(PlotOptions plotOptions) {
		this.plotOptions = plotOptions;
	}

	@JsonProperty("series")
	public List<Series_> getSeries() {
		return series;
	}

	@JsonProperty("series")
	public void setSeries(List<Series_> series) {
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
		return new ToStringBuilder(this).append("xAxis", xAxis).append("plotOptions", plotOptions)
				.append("series", series).append("additionalProperties", additionalProperties).toString();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(plotOptions).append(series).append(additionalProperties).append(xAxis)
				.toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof Linechart) == false) {
			return false;
		}
		Linechart rhs = ((Linechart) other);
		return new EqualsBuilder().append(plotOptions, rhs.plotOptions).append(series, rhs.series)
				.append(additionalProperties, rhs.additionalProperties).append(xAxis, rhs.xAxis).isEquals();
	}

}
