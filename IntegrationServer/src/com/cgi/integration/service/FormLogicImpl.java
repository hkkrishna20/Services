package com.cgi.integration.service;

import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.LoggerFactory;

import com.cgi.integration.models.IntegrationMetadataDocument;
import com.cgi.integration.models.LogicTableBRE;
import com.cgi.integration.schema.FormDetailsType;
import com.cgi.integration.schema.IntegrationRequest;
import com.cgi.integration.schema.SourceType;

public class FormLogicImpl {
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(EndpointClass.class);

	private final String auditURL = "http://localhost:2018/IntegrationRest/audit/save";
	private final String auditPointURL = "http://localhost:2018/IntegrationRest/auditPoint/save";
	private final String metadataURL = "http://localhost:2018/IntegrationRest/metadata/save";
	private final String metadataDocumentURL = "http://localhost:2018/IntegrationRest/metadataDocument/save";

	public Object fromJson(String json) throws JsonParseException, JsonMappingException, IOException {
		LogicTableBRE inc = new ObjectMapper().readValue(json, LogicTableBRE.class);
		return inc;
	}

	public SourceType formLogic(SourceType sourceType) throws Exception {
		// sourceType.getPackage().getHeader().setRequestId(sourceType.getPackage().getHeader().getRequestId());

		GregorianCalendar gcal = new GregorianCalendar();
		XMLGregorianCalendar xgcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
		logger.info(" In formLogic  " + xgcal);
		IntegrationRequest ic = new IntegrationRequest();
		ic.setSource(sourceType);
		String inputXML = "" + RoutingServiceImpl.marshal(ic);
		Date createDate = xgcal.toGregorianCalendar().getTime();
		String processFamily = "" + sourceType.getPackage().getHeader().getProductFamily();
		String sourceCorrelationID = "" + sourceType.getPackage().getHeader().getParentId();
		String reqID = "" + sourceType.getPackage().getHeader().getRequestId();
		Date updateDate = xgcal.toGregorianCalendar().getTime();
		logger.info(inputXML + " \n " + createDate + " \n" + processFamily + "\n " + sourceCorrelationID + " \n" + reqID
				+ " \n" + updateDate);

		RoutingServiceImpl.postClient(auditURL, RoutingServiceImpl.setAudit(inputXML, createDate, processFamily, reqID,
				sourceCorrelationID, updateDate));
		RoutingServiceImpl.postClient(metadataURL,
				RoutingServiceImpl.setMetadata(createDate, updateDate, processFamily, reqID, sourceCorrelationID));
		String auditstagecode = "Form Logic Req";
		String setId = "" + RoutingServiceImpl.generateString();
		RoutingServiceImpl.postClient(auditPointURL,
				RoutingServiceImpl.setAuditPoint(inputXML, createDate, updateDate, auditstagecode, setId, reqID, "0"));
		sourceType.getPackage().getHeader().setPassword("");

		sourceType.getPackage().getHeader().setRequestTimeStamp(xgcal);
		String url = "http://localhost:2018/IntegrationRest/form/get/"
				+ sourceType.getPackage().getHeader().getTemplateId();
		logger.info(url);
		String metad = RoutingServiceImpl.getClient(url);
		LogicTableBRE lc = (LogicTableBRE) fromJson(metad);
		String formID = lc.getFormID();

		formID = formID.replace(",", " ");
		formID = formID.trim();
		String[] splitedFormID = formID.split("\\s+");
		String formTitle = lc.getFormTitle();
		formTitle = formTitle.replace(",", " ");
		formTitle = formTitle.trim();
		String[] splitedTitle = formTitle.split("\\s+");
		for (int i = 0; i < splitedFormID.length; i++) {
			FormDetailsType fc = new FormDetailsType();
			fc.setFormId(splitedFormID[i]);
			fc.setFormSeq(Integer.toString(i));
			fc.setFormTitle(splitedTitle[0]);
			sourceType.getPackage().getHeader().getFormDetails().add(fc);
		}
		logger.info(formID + " \n " + splitedFormID + " \n" + formTitle + "\n " + splitedTitle);

		ic.setSource(sourceType);
		inputXML = RoutingServiceImpl.marshal(ic);
		logger.info("input Response XML" + inputXML);
		auditstagecode = "Form Logic Response";
		setId = RoutingServiceImpl.generateString();
		RoutingServiceImpl.postClient(auditPointURL,
				RoutingServiceImpl.setAuditPoint(inputXML, createDate, updateDate, auditstagecode, setId, reqID, "0"));

		logger.info("Form Logic Done");
		return sourceType;

	}

}
