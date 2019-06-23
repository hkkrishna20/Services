package com.integration.wc.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.integration.wc.client.IntegrationClient;
@Configuration
public class ClientAppConfig {

	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("com.App.integration.schema");
		return marshaller;
	}
	@Bean
	public IntegrationClient integrationClient(Jaxb2Marshaller marshaller) {
		IntegrationClient client = new IntegrationClient();
		client.setDefaultUri("http://localhost:9080/IntegrationServer/integrationService.wsdl");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}
} 