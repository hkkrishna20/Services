package com.integration.wc.client;

import javax.xml.bind.JAXBException;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.integration.wc.schema.DocFetchRequest;
import com.integration.wc.schema.DocFetchResponse;
import com.integration.wc.schema.IntegrationRequest;
import com.integration.wc.schema.IntegrationResponse;

public class IntegrationClient extends WebServiceGatewaySupport {
	public IntegrationResponse integrationClient(IntegrationRequest request) throws JAXBException {
		IntegrationResponse response = (IntegrationResponse) getWebServiceTemplate().marshalSendAndReceive(request,
				new SoapActionCallback("http://localhost:9080/IntegrationServer/IntegrationResponse"));
		return response;

	}

	public DocFetchResponse docFetchClient(DocFetchRequest request) throws JAXBException {
		DocFetchResponse response = (DocFetchResponse) getWebServiceTemplate().marshalSendAndReceive(request,
				new SoapActionCallback("http://localhost:9080/IntegrationServer/DocFetchResponse"));
		return response;

	}
}
