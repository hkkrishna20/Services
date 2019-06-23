package com.cgi.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class ClientAppConfig {

	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("com.cgi.models");
		return marshaller;
	}

	@Bean
	public SoapTestClient integrationClient(Jaxb2Marshaller marshaller) {
		SoapTestClient client = new SoapTestClient();
		client.setDefaultUri("http://localhost:8141/javainuse/ws/wsdl/helloworld.wsdl");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}
}
