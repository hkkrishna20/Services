package com.cgi.integration.service;

import java.io.StringWriter;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.slf4j.LoggerFactory;

import com.cgi.integration.schema.IntegrationRequest;
import com.cgi.integration.schema.SourceType;

public class InterfaceImpl {
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(EndpointClass.class);

	private final String auditURL="http://localhost:2018/IntegrationRest/audit/save";
	private final String auditPointURL="http://localhost:2018/IntegrationRest/auditPoint/save";
	private final String metadataURL="http://localhost:2018/IntegrationRest/metadata/save";
	private final String metadataDocumentURL="http://localhost:2018/IntegrationRest/metadataDocument/save";
	
	
	public SourceType interfaceP(SourceType sourceType) throws Exception {
		// sourceType.getPackage().getHeader().setRequestId(sourceType.getPackage().getHeader().getRequestId());
		
		GregorianCalendar gcal = new GregorianCalendar();
		XMLGregorianCalendar xgcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
		logger.info(" In InterfaceLogic  " + xgcal);
		sourceType.getPackage().getHeader().setRequestTimeStamp(xgcal);
		IntegrationRequest ic=new IntegrationRequest();
		ic.setSource(sourceType);
		String inputXML = RoutingServiceImpl.marshal(ic);
		Date createDate = xgcal.toGregorianCalendar().getTime();
		String processFamily = sourceType.getPackage().getHeader().getProductFamily();
		String sourceCorrelationID = sourceType.getPackage().getHeader().getParentId();
		String reqID = sourceType.getPackage().getHeader().getRequestId();
		Date updateDate = xgcal.toGregorianCalendar().getTime();
		logger.info(inputXML +" \n " +createDate +" \n"+processFamily +"\n "+sourceCorrelationID +" \n"+ reqID +" \n"+ updateDate );
		
		RoutingServiceImpl.postClient(auditURL,
				RoutingServiceImpl.setAudit(inputXML, createDate, processFamily, reqID, sourceCorrelationID, updateDate));
		RoutingServiceImpl.postClient(metadataURL,
				RoutingServiceImpl.setMetadata(createDate, updateDate, processFamily, reqID, sourceCorrelationID));
		String auditstagecode = "Interface Req";
		String setId = RoutingServiceImpl.generateString();
		RoutingServiceImpl.postClient(auditPointURL,
				RoutingServiceImpl.setAuditPoint(inputXML, createDate, updateDate, auditstagecode, setId, reqID, "0"));
		sourceType.getPackage().getHeader().setPassword("");
		xgcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
		sourceType.getPackage().getHeader().setRequestTimeStamp(xgcal);
		ic=new IntegrationRequest();
		ic.setSource(sourceType);
		inputXML = RoutingServiceImpl.marshal(ic);
		auditstagecode = "Interface Response";
		setId = RoutingServiceImpl.generateString();
		RoutingServiceImpl.postClient(auditPointURL,
				RoutingServiceImpl.setAuditPoint(inputXML, createDate, updateDate, auditstagecode, setId, reqID, "0"));
		logger.info("Inteface Java Done");
		return sourceType;
	}
}
