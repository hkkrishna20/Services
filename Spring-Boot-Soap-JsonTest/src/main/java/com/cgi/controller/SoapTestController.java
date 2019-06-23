package com.cgi.controller;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cgi.models.CustomerRequest;
import com.cgi.models.CustomerResponse;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class SoapTestController {
	@Autowired
	TransformComponent transformComponent;

	@RequestMapping(value = "/endpointsoaprest", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String loanDet(@RequestBody String CustomerName)
			throws JsonParseException, JsonMappingException, IOException, JAXBException {
		ObjectMapper mapper = new ObjectMapper();
		// Convert JSON string to Object
		CustomerRequest request = mapper.readValue(CustomerName, CustomerRequest.class);
		String url = "http://localhost:8141/javainuse/ws/CustomerResponse";
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		CustomerResponse incresponse = null;
		String resp = null;
		try {
			ctx.register(ClientAppConfig.class);
			ctx.refresh();
			SoapTestClient client = ctx.getBean(SoapTestClient.class);
			incresponse = client.client(request);
			resp = transformComponent.marshalToJson(incresponse);
			System.out.println(resp);
		} finally {
			ctx.close();
		}
		return resp;

	}
}
