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
    "customerAccountNo",
    "salary",
    "requestedAmount",
    "loanOfferId"
})
@XmlRootElement(name = "CustomerRequest")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "CustomerName", "CustomerAccountNo", "Salary", "RequestedAmount", "LoanOfferId" })
public class CustomerRequest {
    @XmlElement(name = "CustomerName", required = true)
	@JsonProperty("CustomerName")
	private String customerName;
    @XmlElement(name = "CustomerAccountNo", required = true)
	@JsonProperty("CustomerAccountNo")
	private String customerAccountNo;
    @XmlElement(name = "Salary", required = true)
	@JsonProperty("Salary")
	private String salary;
    @XmlElement(name = "RequestedAmount", required = true)
	@JsonProperty("RequestedAmount")
	private String requestedAmount;
    @XmlElement(name = "LoanOfferId", required = true)
	@JsonProperty("LoanOfferId")
	private String loanOfferId;
	
	@JsonProperty("CustomerName")
	public String getCustomerName() {
		return customerName;
	}

	@JsonProperty("CustomerName")
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	@JsonProperty("CustomerAccountNo")
	public String getCustomerAccountNo() {
		return customerAccountNo;
	}

	@JsonProperty("CustomerAccountNo")
	public void setCustomerAccountNo(String customerAccountNo) {
		this.customerAccountNo = customerAccountNo;
	}

	@JsonProperty("Salary")
	public String getSalary() {
		return salary;
	}

	@JsonProperty("Salary")
	public void setSalary(String salary) {
		this.salary = salary;
	}

	@JsonProperty("RequestedAmount")
	public String getRequestedAmount() {
		return requestedAmount;
	}

	@JsonProperty("RequestedAmount")
	public void setRequestedAmount(String requestedAmount) {
		this.requestedAmount = requestedAmount;
	}

	@JsonProperty("LoanOfferId")
	public String getLoanOfferId() {
		return loanOfferId;
	}

	@JsonProperty("LoanOfferId")
	public void setLoanOfferId(String loanOfferId) {
		this.loanOfferId = loanOfferId;
	}

	

}