package com.cgi.integration.restcontroller;

import java.text.ParseException;
import java.util.Date;

import com.cgi.integration.models.IntegrationAudit;
import com.cgi.integration.models.IntegrationAuditPoint;
import com.cgi.integration.models.IntegrationMetadata;
import com.cgi.integration.models.IntegrationMetadataDocument;

public class ComplexDBMethods {

	public IntegrationAudit setAudit(String inputXML, Date createDate, String processFamily, String reqID,
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

	public IntegrationAuditPoint setAuditPoint(String inputXML, Date createDate, Date updateDate, String auditstagecode,
			String setId, String setReqid_FK, String setStatus) throws ParseException {
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

	public IntegrationMetadata setMetadata(Date createDate, Date updateDate, String processFamily, String reqID,
			String sourceCorrelationID) throws ParseException {
		IntegrationMetadata ia = new IntegrationMetadata();
		ia.setCreatedAt(createDate);
		ia.setUpdatedAt(updateDate);
		ia.setProcessfamily(processFamily);
		ia.setReqid(reqID);
		ia.setSourcecorelid(sourceCorrelationID);
		return ia;
	}

	public IntegrationMetadataDocument setIntegrationMetadataDocument(Date createDate, Date updateDate, String setReqid_FK,
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

}
