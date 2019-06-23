package com.cgi.controller;

import javax.xml.bind.JAXBException;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.cgi.models.CustomerRequest;
import com.cgi.models.CustomerResponse;
public class SoapTestClient extends WebServiceGatewaySupport {
	public CustomerResponse client(CustomerRequest request) throws JAXBException {
		CustomerResponse response = (CustomerResponse) getWebServiceTemplate().marshalSendAndReceive(request,
				new SoapActionCallback("http://localhost:8141/javainuse/ws/CustomerResponse"));
		System.out.println(response);
		return response;
	}

}
