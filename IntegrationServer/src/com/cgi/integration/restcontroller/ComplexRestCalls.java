package com.cgi.integration.restcontroller;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.LoggerFactory;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class ComplexRestCalls {
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ComplexRestCalls.class);

	public static String marshalToJson(Object o) throws JAXBException {

		ObjectMapper mapper = new ObjectMapper();
		String jsonVal = "";
		try {
			jsonVal = mapper.writeValueAsString(o);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonVal;
	}

	public void postClient(String url, Object o) throws JAXBException {
		String jsonInput = marshalToJson(o);
		Client restClient = Client.create();
		WebResource webResource = restClient.resource(url);
		ClientResponse resp = webResource.type("application/json").post(ClientResponse.class, jsonInput);
		String output = resp.getEntity(String.class);
	}

	public void getClient(String url) {
		Client restClient = Client.create();
		WebResource webResource = restClient.resource(url);
		ClientResponse resp = webResource.accept("application/json").get(ClientResponse.class);
		String output = resp.getEntity(String.class);
		   logger.info("response: "+output);
	}
}
