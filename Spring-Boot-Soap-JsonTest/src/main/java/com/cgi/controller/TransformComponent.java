package com.cgi.controller;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
@Component
public class TransformComponent {
		public static String marshal(Object object) throws JAXBException {
		StringWriter stringWriter = new StringWriter();
		JAXBContext jc = JAXBContext.newInstance(object.getClass());
		Marshaller m = jc.createMarshaller();
		m.marshal(object, stringWriter);
		System.out.println(stringWriter.toString());
		return stringWriter.toString();
	}

	public static String marshalToJson(Object o)
			throws JAXBException, JsonGenerationException, JsonMappingException, IOException {

		ObjectMapper mapper = new ObjectMapper();
		String jsonVal = "";
		jsonVal = mapper.writeValueAsString(o);
		return jsonVal;
	}

	public static String convertObjectToXML(Object object) {
		try {
			StringWriter stringWriter = new StringWriter();
			JAXBContext context = JAXBContext.newInstance(object.getClass());
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.marshal(object, stringWriter);
			System.out.println(stringWriter.toString());
			return stringWriter.toString();
		} catch (JAXBException e) {
			System.err.println(String.format("Exception while marshalling: %s", e.getMessage()));
		}
		return null;
	}

	public <T> String marshallXml(T object) throws JAXBException {
		StringWriter stringWriter = new StringWriter();
		JAXBContext jc = JAXBContext.newInstance(object.getClass());
		Marshaller m = jc.createMarshaller();
		m.marshal(object, stringWriter);
		System.out.println(stringWriter.toString());
		return stringWriter.toString();
	}

	protected static <T> T convertXMLToObject(Class<T> clazz, String xml) throws JAXBException {
		JAXBContext jc = JAXBContext.newInstance(clazz);
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		return unmarshaller.unmarshal(new StreamSource(new StringReader(xml)), clazz).getValue();
	}

	public <T> T unMarshal(String content, Class<T> clasz) {
		try {
			JAXBContext jc = JAXBContext.newInstance(clasz);
			Unmarshaller u = jc.createUnmarshaller();
			return u.unmarshal(new StreamSource(new StringReader(content)), clasz).getValue();
		} catch (JAXBException e) {
			//logger.error(String.format("Exception while unmarshalling: %s", e.getMessage()));
		}
		return null;
	}

	public static String getClient(String url) {
		Client restClient = Client.create();
		WebResource webResource = restClient.resource(url);
		ClientResponse resp = webResource.accept("application/json").get(ClientResponse.class);
		String output = resp.getEntity(String.class);
		System.out.println(output);
		//logger.info("response: " + output);
		return output;
	}

	public static String postClientXML(String url, Object o) throws JAXBException {
		String jsonInput = convertObjectToXML(o);
		Client restClient = Client.create();
		WebResource webResource = restClient.resource(url);
		ClientResponse resp = webResource.type("application/json").post(ClientResponse.class, jsonInput);
		String output = resp.getEntity(String.class);
		System.out.println(output);
		return output;
	}

	public static String postClient(String url, Object o)
			throws JAXBException, JsonGenerationException, JsonMappingException, IOException {
		String jsonInput = marshalToJson(o);

		Client restClient = Client.create();
		WebResource webResource = restClient.resource(url);
		ClientResponse resp = webResource.type("application/json").post(ClientResponse.class, jsonInput);
		String output = resp.getEntity(String.class);
		System.out.println(output);
		return output;
	}

	public static String getClientXML(String url) {
		Client restClient = Client.create();
		WebResource webResource = restClient.resource(url);
		ClientResponse resp = webResource.accept("application/xml").get(ClientResponse.class);
		String output = resp.getEntity(String.class);
		System.out.println(output);
		//logger.info("response: " + output);
		return output;
	}

}
