package com.cgi.integration.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.GregorianCalendar;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.codec.binary.Base64;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;

import com.cgi.integration.models.IntegrationMetadataDocument;
import com.cgi.integration.restcontroller.Clients;
import com.cgi.integration.schema.BodyType;
import com.cgi.integration.schema.DocFetchRequest;
import com.cgi.integration.schema.DocFetchResponse;
import com.cgi.integration.schema.HeaderType;
import com.cgi.integration.schema.IntegrationRequest;
import com.cgi.integration.schema.IntegrationResponse;
import com.cgi.integration.schema.PackageType;
import com.cgi.integration.schema.SourceType;

@Endpoint
public class EndpointClass {
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(EndpointClass.class);

	private static final String NAMESPACE_URI = "urn://com.cgi.services/schema";
	private WcService wcService;
	private SaajSoapMessageFactory saajMessageFactory;

	@Autowired
	public EndpointClass(WcService wcService) {
		this.wcService = wcService;

	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "IntegrationRequest")
	public @ResponsePayload IntegrationResponse handleRequest(@RequestPayload IntegrationRequest request)
			throws Exception {
		String reqID = RoutingServiceImpl.generateString();
		IntegrationResponse bp = new IntegrationResponse();
		bp.setSource(request.getSource());
		String username = request.getSource().getPackage().getHeader().getUserId().trim();
		String password = request.getSource().getPackage().getHeader().getPassword().trim();
		if (!((username.equals("FormDeterminationServie") && password.equals("FormDeterminationServie"))
				|| (username.equals("FormCreationService") && password.equals("FormCreationService"))
				|| (username.equals("InterfaceService") && password.equals("InterfaceService"))
				|| (username.equals("RoutingService") && password.equals("RoutingService"))
				|| (username.equals("FormMergeService") && password.equals("FormMergeService"))
				|| (username.equals("FormSplitterService") && password.equals("FormSplitterService"))
				|| (username.equals("SourceFormGenerationService") && password.equals("SourceFormGenerationService"))
				|| (username.equals("DocFetchService") && password.equals("DocFetchService"))
				|| (username.equals("test") && password.equals("testPassword"))
				||(username.equals("EncommPoc") && password.equals("EncommPoc")))) {
			throw new DetailsNotFoundException("Could not find  with ID " + username + " || password" + password);
		}
		String testType = bp.getSource().getPackage().getHeader().getTestType();
		bp.getSource().getPackage().getHeader().setRequestId(reqID);
		if (testType.equals("FormDeterminationServie")) {
	logger.info("username : "+username +" FormDeterminationServie ");
			bp.setSource(wcService.formLogic(bp.getSource()));
		} else if (testType.equals("FormCreationService")) {
			logger.info("username : "+username +" FormCreationService ");
			bp.setSource(wcService.formGen(bp.getSource()));
		} else if (testType.equals("InterfaceService")) {
			logger.info("username : "+username +" InterfaceService ");			
			bp.setSource(wcService.interfaceP(bp.getSource()));
		} else if (testType.equals("RoutingService")) {
			logger.info("username : "+username +" RoutingService ");
			bp.setSource(wcService.enroute(bp.getSource()));
		} else if (testType.equals("FormMergeService")) {
			logger.info("username : "+username +" FormMergeService ");
			bp.setSource(wcService.merge(bp.getSource()));
		} else if (testType.equals("FormSplitterService")) {
			logger.info("username : "+username +" FormSplitterService ");
			bp.setSource(wcService.split(bp.getSource()));
		} else if (testType.equals("SourceFormGenerationService")) {
			logger.info("username : "+username +" SourceFormGenerationService ");
			bp.setSource(wcService.endtoEnd(bp.getSource()));
		}
		 else if (testType.equals("EncommFormGenerationService")) {
				logger.info("username : "+username +" EncommFormGenerationService ");
				bp.setSource(wcService.encomm(bp.getSource()));
			}else {
			logger.info("username : "+username +" Unable to Find Service test Type ");
			throw new DetailsNotFoundException("Could not find  with testType =" + testType);
		}
		bp.getSource().getPackage().getHeader().setPassword("");
		return bp;
	}

	public Object fromJson(String json) throws JsonParseException, JsonMappingException, IOException {
		IntegrationMetadataDocument inc = new ObjectMapper().readValue(json, IntegrationMetadataDocument.class);
		return inc;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "DocFetchRequest")
	public @ResponsePayload DocFetchResponse handleReq(@RequestPayload DocFetchRequest request) throws Exception {
		String username = request.getSource().getPackage().getHeader().getUserId();
		String password = request.getSource().getPackage().getHeader().getPassword();
		if (!((username.equals("test") && password.equals("testPassword"))
				|| (username.equals("FormDeterminationServie") && password.equals("FormDeterminationServie"))
				|| (username.equals("FormCreationService") && password.equals("FormCreationService"))
				|| (username.equals("InterfaceService") && password.equals("InterfaceService"))
				|| (username.equals("RoutingService") && password.equals("RoutingService"))
				|| (username.equals("FormMergeService") && password.equals("FormMergeService"))
				|| (username.equals("FormSplitterService") && password.equals("FormSplitterService"))
				|| (username.equals("SourceFormGenerationService") && password.equals("SourceFormGenerationService"))
				|| (username.equals("DocFetchService") && password.equals("DocFetchService"))))

		{
			throw new DetailsNotFoundException("Could not find  with ID " + username + " || password" + password);
		}
		logger.info("username : "+username +" DocFetch Servie ");

		String url = "http://localhost:2018/IntegrationRest/metadataDocument/get/"
				+ request.getSource().getPackage().getHeader().getDocId();
		String metad = RoutingServiceImpl.getClient(url);
		IntegrationMetadataDocument inc = (IntegrationMetadataDocument) fromJson(metad);
		GregorianCalendar gcal = new GregorianCalendar();
		XMLGregorianCalendar xgcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
		logger.info(" In DocFetchLogic  " + xgcal);
		File pdfFile = null;
		if (inc.getDocpath() != null) {
			pdfFile = new File(inc.getDocpath());
		} else {
			pdfFile = new File("C:\\Users\\Public\\test.pdf");
		}
		String encodedBase64 = null;
		FileInputStream fileInputStreamReader = new FileInputStream(pdfFile);
		byte[] bytes = new byte[(int) pdfFile.length()];
		fileInputStreamReader.read(bytes);
		encodedBase64 = new String(Base64.encodeBase64(bytes));
		DataSource fds = new FileDataSource(pdfFile);
		DataHandler handler = new DataHandler(fds);
		
		HeaderType hc = new HeaderType();
		hc.setRequestId("1234");
		hc.setRequestTimeStamp(xgcal);
		hc.setUserId("test");
		hc.setDocId(request.getSource().getPackage().getHeader().getDocId());
		hc.setDocPathUrl(inc.getDocpath());
		BodyType bc = new BodyType();
		PackageType pc = new PackageType();
		pc.setHeader(hc);
		pc.setBody(bc);
		SourceType sc = new SourceType();
		sc.setPackage(pc);
		DocFetchResponse bpq = new DocFetchResponse();
		bpq.setSource(sc);
		bpq.setArg0(handler);
		bpq.setA(bytes);
		bpq.setBase64EncodedString(encodedBase64);
		return bpq;
	}

	public void setSaajMessageFactory(SaajSoapMessageFactory saajMessageFactory) {
		this.saajMessageFactory = saajMessageFactory;
	}

	public SaajSoapMessageFactory getSaajMessageFactory() {
		return saajMessageFactory;
	}

}