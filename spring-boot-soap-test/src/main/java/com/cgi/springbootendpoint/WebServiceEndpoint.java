package com.cgi.springbootendpoint;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;

import springboot.cgi.com.CustomerRequest;
import springboot.cgi.com.CustomerResponse;
import springboot.cgi.com.ObjectFactory;

@Endpoint
public class WebServiceEndpoint {
	private SaajSoapMessageFactory saajMessageFactory;
	private static final String NAMESPACE_URI = "urn://com.cgi.springboot";

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "CustomerRequest")
	@ResponsePayload
	public CustomerResponse hello(@RequestPayload CustomerRequest request) {

		// String outputString = "Hello " + request.getCustomerName() + "!";
		String customerName = request.getCustomerName();
		String CustomerAccountNo = request.getCustomerAccountNo();
		String Salary = request.getSalary();
		String requestedAmount = request.getRequestedAmount();
		String LoanOfferId = request.getLoanOfferId();

		System.out.println("Customer Name: " + customerName);
		System.out.println("CustomerAccountNo: " + CustomerAccountNo);
		ObjectFactory factory = new ObjectFactory();
		CustomerResponse response = factory.createCustomerResponse();
		response.setCustomerName(customerName);
		response.setLoanApplicationNumber("123456");
		response.setStatus("Active");

		return response;
	}

	public void setSaajMessageFactory(SaajSoapMessageFactory saajMessageFactory) {
		this.saajMessageFactory = saajMessageFactory;
	}

	public SaajSoapMessageFactory getSaajMessageFactory() {
		return saajMessageFactory;
	}
}
