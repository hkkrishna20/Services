package com.cgi.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
	"customerName",
    "loanApplicationNumber",
    "status"
})
@XmlRootElement(name = "CustomerResponse")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"customerName", "LoanApplicationNumber", "Status" })
public class CustomerResponse {
	@JsonProperty("customerName")
	@XmlElement(name = "customerName", required = true)
	protected String customerName;
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	@XmlElement(name = "LoanApplicationNumber", required = true)
	@JsonProperty("LoanApplicationNumber")
	private String loanApplicationNumber;
	@XmlElement(name = "Status", required = true)
	@JsonProperty("Status")
	private String status;

	@Override
	public String toString() {
		return "CustomerResponse [customerName=" + customerName + ", loanApplicationNumber=" + loanApplicationNumber
				+ ", status=" + status + "]";
	}

	@JsonProperty("LoanApplicationNumber")
	public String getLoanApplicationNumber() {
		return loanApplicationNumber;
	}

	@JsonProperty("LoanApplicationNumber")
	public void setLoanApplicationNumber(String loanApplicationNumber) {
		this.loanApplicationNumber = loanApplicationNumber;
	}

	@JsonProperty("Status")
	public String getStatus() {
		return status;
	}

	@JsonProperty("Status")
	public void setStatus(String status) {
		this.status = status;
	}

}