package com.cgi.integration.restcontroller;

import org.slf4j.LoggerFactory;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class Clients {
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Clients.class);

	void postClient() {
		 String url = "http://localhost:2018/IntegrationRest/form/getAll";
     String jsonInput = "{\"custmer\":\"Java2novice\",\"address\":\"Bangalore\","+
             "\"bill-amount\":\"$2000\"}";
     Client restClient = Client.create();
     WebResource webResource = restClient.resource(url);
     ClientResponse resp = webResource.type("application/json")
                                 .post(ClientResponse.class, jsonInput);
     if(resp.getStatus() != 200){
         System.err.println("Unable to connect to the server");
     }
     String output = resp.getEntity(String.class);
     logger.info("response: "+output);
}
	void getClient() {
		String url = "http://localhost:2018/IntegrationRest/form/getAll";
		Client restClient = Client.create();
		WebResource webResource = restClient.resource(url);
		ClientResponse resp = webResource.accept("application/json").get(ClientResponse.class);
		if(resp.getStatus() != 200){
		System.err.println("Unable to connect to the server");
		}
		String output = resp.getEntity(String.class);
		   logger.info("response: "+output);
	}
}
