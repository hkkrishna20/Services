package com.cgi.integration.service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.xml.transform.StringResult;
import org.springframework.xml.transform.StringSource;

public class MarshallingService {

	// JAXBContext is thread safe and can be created once
	private JAXBContext jaxbContext;

	@Autowired
	private Jaxb2Marshaller marshaller;


	public MarshallingService() {
		try {
			// create context with ":" separated list of packages that
			// contain your JAXB ObjectFactory classes
			jaxbContext = JAXBContext.newInstance("com.cgi.integration.schema" + ":com.cgi.integration.service" + ":com.cgi.integration.models");
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}
	public JAXBElement<?> unmarshal(String xmlString) {
		return (JAXBElement<?>) marshaller.unmarshal(new StringSource(xmlString));
	}

	/*public String marshal(JAXBElement<?> jaxbElement) {
		try {
			// Marshallers are not thread-safe. Create a new one every time.
			Marshaller marshaller = jaxbContext.createMarshaller();
			StringWriter stringWriter = new StringWriter();
			marshaller.marshal(jaxbElement, stringWriter);
			return stringWriter.toString();
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}*/

	public String marshal(JAXBElement<?> jaxbElement) {
		try {
			StringResult result = new StringResult();
			marshaller.marshal(jaxbElement, result);
			return result.toString();
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}
}