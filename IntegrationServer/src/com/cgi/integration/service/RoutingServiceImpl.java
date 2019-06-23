package com.cgi.integration.service;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.ParseException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.transform.stream.StreamSource;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.LoggerFactory;

import com.cgi.integration.models.IntegrationAudit;
import com.cgi.integration.models.IntegrationAuditPoint;
import com.cgi.integration.models.IntegrationMetadata;
import com.cgi.integration.models.IntegrationMetadataDocument;
import com.cgi.integration.models.ObjectFactory;
import com.cgi.integration.models.WcType;
import com.cgi.integration.schema.FormDetailsType;
import com.cgi.integration.schema.IntegrationRequest;
import com.cgi.integration.schema.SourceType;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class RoutingServiceImpl {
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(EndpointClass.class);

	private final String auditURL = "http://localhost:2018/IntegrationRest/audit/save";
	private final String auditPointURL = "http://localhost:2018/IntegrationRest/auditPoint/save";
	private final String metadataURL = "http://localhost:2018/IntegrationRest/metadata/save";
	private final String metadataDocumentURL = "http://localhost:2018/IntegrationRest/metadataDocument/save";
	public static  String marshal(Object object) throws JAXBException {
		  StringWriter stringWriter = new StringWriter();
		  JAXBContext jc = JAXBContext.newInstance( object.getClass());
		  Marshaller m = jc.createMarshaller();
		  m.marshal(object, stringWriter);
		  System.out.println(stringWriter.toString());
		  return stringWriter.toString();
		}

	public static String marshalToJson(Object o) throws JAXBException, JsonGenerationException, JsonMappingException, IOException {

		ObjectMapper mapper = new ObjectMapper();
		String jsonVal = "";
			jsonVal = mapper.writeValueAsString(o);
		return jsonVal;
	}

	public static  String convertObjectToXML(Object object) {
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
		  JAXBContext jc = JAXBContext.newInstance( object.getClass());
		  Marshaller m = jc.createMarshaller();
		  m.marshal(object, stringWriter);
		  System.out.println(stringWriter.toString());
		  return stringWriter.toString();
		}
	protected static <T> T convertXMLToObject( Class<T> clazz,String xml)
	        throws JAXBException {
	    JAXBContext jc = JAXBContext.newInstance(clazz);
	    Unmarshaller unmarshaller = jc.createUnmarshaller();
	    return unmarshaller.unmarshal(new StreamSource( new StringReader( xml )), clazz).getValue();
	}
	public <T> T unMarshal(String content, Class<T> clasz) {
		 try {
		  JAXBContext jc = JAXBContext.newInstance( clasz );
		  Unmarshaller u = jc.createUnmarshaller();
		  return u.unmarshal(new StreamSource( new StringReader( content )), clasz).getValue();
		 } catch (JAXBException e) {
		  logger.error(String.format("Exception while unmarshalling: %s", e.getMessage()));
		 }  
		 return null;
		}
	public static String getClient(String url) {
		Client restClient = Client.create();
		WebResource webResource = restClient.resource(url);
		ClientResponse resp = webResource.accept("application/json").get(ClientResponse.class);
		String output = resp.getEntity(String.class);
		System.out.println(output);
		logger.info("response: " + output);
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

	public static String postClient(String url, Object o) throws JAXBException, JsonGenerationException, JsonMappingException, IOException {
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
		logger.info("response: " + output);
		return output;
	}


	public static WcType getContentAll(String url, List<FormDetailsType> fr)
			throws JsonParseException, JsonMappingException, IOException, JAXBException {
		String output = null;
		Client restClient = Client.create();
		WebResource webResource = restClient.resource(url);
		ClientResponse resp = webResource.accept("application/xml").get(ClientResponse.class);
		output = resp.getEntity(String.class);
		System.out.println(output);
		String topString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		if (!output.contains("xml version"))
			output = topString + "\n" + output;
		if (output.contains("<contentTs><contentTs>"))
			output = output.replace("<contentTs><contentTs>", "<contentTs>");
		if (output.contains("</contentTs></contentTs>"))
			output = output.replace("</contentTs></contentTs>", "</contentTs>");
		System.out.println(output);
		StringReader sr = new StringReader(output);
		JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		WcType content = (WcType) jaxbUnmarshaller.unmarshal(sr);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		jaxbMarshaller.marshal(content, System.out);

		return content;

	}

	public static IntegrationAudit setAudit(String inputXML, Date createDate, String processFamily, String reqID,
			String sourceCorrelationID, Date updateDate) throws ParseException {
		IntegrationAudit ia = new IntegrationAudit();
		ia.setContent(inputXML);
		ia.setCreatedAt(createDate);
		ia.setProcessfamily(processFamily);
		ia.setSourcecorelid(sourceCorrelationID);
		ia.setReqid(reqID);
		ia.setUpdatedAt(updateDate);
		return ia;
	}

	public static IntegrationAuditPoint setAuditPoint(String inputXML, Date createDate, Date updateDate,
			String auditstagecode, String setId, String setReqid_FK, String setStatus) throws ParseException {
		IntegrationAuditPoint iap = new IntegrationAuditPoint();
		iap.setAuditstagecode(auditstagecode);
		iap.setCreatedAt(createDate);
		iap.setId(setId);
		iap.setReqid_FK(setReqid_FK);
		iap.setStatus(setStatus);
		iap.setUpdatedAt(updateDate);
		iap.setTransXML(inputXML);
		return iap;
	}

	public static IntegrationMetadata setMetadata(Date createDate, Date updateDate, String processFamily, String reqID,
			String sourceCorrelationID) throws ParseException {
		IntegrationMetadata ia = new IntegrationMetadata();
		ia.setCreatedAt(createDate);
		ia.setUpdatedAt(updateDate);
		ia.setProcessfamily(processFamily);
		ia.setReqid(reqID);
		ia.setSourcecorelid(sourceCorrelationID);
		return ia;
	}

	public static IntegrationMetadataDocument setIntegrationMetadataDocument(Date createDate, Date updateDate,
			String docID, String docpath, String ecmpath, String filenetpath, String reqid_FK) throws ParseException {
		IntegrationMetadataDocument iap = new IntegrationMetadataDocument();
		iap.setCreatedAt(createDate);
		iap.setUpdatedAt(updateDate);
		iap.setDocID(docID);
		iap.setDocpath(docpath);
		iap.setEcmpath(ecmpath);
		iap.setFilenetpath(filenetpath);
		iap.setReqid_FK(reqid_FK);
		return iap;
	}

	static String generateString() {
		String uuid = UUID.randomUUID().toString();
		return uuid;
	}

	public SourceType routeServ(SourceType sourceType) throws Exception {
		GregorianCalendar gcal = new GregorianCalendar();
		XMLGregorianCalendar xgcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
		logger.info(" In Routing Logic  " + xgcal);

		IntegrationRequest ic = new IntegrationRequest();
		ic.setSource(sourceType);
		String inputXML = "" + marshal(ic);
		Date createDate = xgcal.toGregorianCalendar().getTime();
		String processFamily = "" + sourceType.getPackage().getHeader().getProductFamily();
		String sourceCorrelationID = "" + sourceType.getPackage().getHeader().getParentId();
		String reqID = "" + sourceType.getPackage().getHeader().getRequestId();
		Date updateDate = xgcal.toGregorianCalendar().getTime();
		logger.info(inputXML + " \n " + createDate + " \n" + processFamily + "\n " + sourceCorrelationID + " \n" + reqID
				+ " \n" + updateDate);

		postClient(auditURL, setAudit(inputXML, createDate, processFamily, reqID, sourceCorrelationID, updateDate));
		postClient(metadataURL, setMetadata(createDate, updateDate, processFamily, reqID, sourceCorrelationID));
		String auditstagecode = "Routing Service Req";
		String setId = "" + RoutingServiceImpl.generateString();
		postClient(auditPointURL, setAuditPoint(inputXML, createDate, updateDate, auditstagecode, setId, reqID, "0"));
		sourceType.getPackage().getHeader().setPassword("");
		xgcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
		sourceType.getPackage().getHeader().setRequestTimeStamp(xgcal);

		ic = new IntegrationRequest();
		ic.setSource(sourceType);
		inputXML =marshal(ic);
		auditstagecode = "Routing Service Response";
		setId = RoutingServiceImpl.generateString();
		postClient(auditPointURL, setAuditPoint(inputXML, createDate, updateDate, auditstagecode, setId, reqID, "0"));
		logger.info("In Routing Logic Done");

		return sourceType;
	}

}
