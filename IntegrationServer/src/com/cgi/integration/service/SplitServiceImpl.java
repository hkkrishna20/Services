package com.cgi.integration.service;

import java.io.FileOutputStream;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.slf4j.LoggerFactory;

import com.cgi.integration.models.IntegrationMetadataDocument;
import com.cgi.integration.schema.IntegrationRequest;
import com.cgi.integration.schema.SourceType;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;

public class SplitServiceImpl {
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(EndpointClass.class);

	private final String auditURL = "http://localhost:2018/IntegrationRest/audit/save";
	private final String auditPointURL = "http://localhost:2018/IntegrationRest/auditPoint/save";
	private final String metadataURL = "http://localhost:2018/IntegrationRest/metadata/save";
	private final String metadataDocumentURL = "http://localhost:2018/IntegrationRest/metadataDocument/save";
	private final String metadataDocumentFetchURL = "http://localhost:2018/IntegrationRest/metadataDocument/get";

	public SourceType split(SourceType sourceType) throws Exception {

		String documentID = RoutingServiceImpl.generateString();
		GregorianCalendar gcal = new GregorianCalendar();
		XMLGregorianCalendar xgcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
		logger.info(" In Split Logic  " + xgcal);

		IntegrationRequest ic = new IntegrationRequest();
		ic.setSource(sourceType);
		String inputXML = "" + RoutingServiceImpl.marshal(ic);
		Date createDate = xgcal.toGregorianCalendar().getTime();
		String processFamily = "" + sourceType.getPackage().getHeader().getProductFamily();
		String sourceCorrelationID = "" + sourceType.getPackage().getHeader().getParentId();
		String reqID = "" + sourceType.getPackage().getHeader().getRequestId();
		Date updateDate = xgcal.toGregorianCalendar().getTime();
		logger.info(inputXML +" \n " +createDate +" \n"+processFamily +"\n "+sourceCorrelationID +" \n"+ reqID +" \n"+ updateDate );

		RoutingServiceImpl.postClient(auditURL, RoutingServiceImpl.setAudit(inputXML, createDate, processFamily,
				reqID, sourceCorrelationID, updateDate));
		RoutingServiceImpl.postClient(metadataURL,
				RoutingServiceImpl.setMetadata(createDate, updateDate, processFamily, reqID, sourceCorrelationID));
		String auditstagecode = "Routing Service Req";
		String setId = "" + RoutingServiceImpl.generateString();
		RoutingServiceImpl.postClient(auditPointURL, RoutingServiceImpl.setAuditPoint(inputXML, createDate,
				updateDate, auditstagecode, setId, reqID, "0"));

		sourceType.getPackage().getHeader().setRequestTimeStamp(xgcal);
		String input = RoutingServiceImpl.getClientXML(metadataDocumentFetchURL+"/"+sourceType.getPackage().getHeader().getDocId());
		System.out.println(input);
		IntegrationMetadataDocument inputFil=(IntegrationMetadataDocument)RoutingServiceImpl.convertXMLToObject(IntegrationMetadataDocument.class, input);
		String inputFile=inputFil.getDocpath();
		String OutputFolder = "C:\\Users\\Public\\Output\\";
		String outputFile = OutputFolder + documentID + ".pdf";
		PdfReader reader = new PdfReader(inputFile);
		int n = reader.getNumberOfPages();
		int startPage = sourceType.getPackage().getBody().getSplitDetails().getStartPageNo();
		int endPage = sourceType.getPackage().getBody().getSplitDetails().getEndPageNo();
		if (n < startPage || n < endPage) {
			throw new DetailsNotFoundException("BRE error Split tags values are not found ");
		} else {
			Document document1 = new Document(reader.getPageSizeWithRotation(1));
			PdfCopy writerN = new PdfCopy(document1, new FileOutputStream(outputFile));
			document1.open();
			for (int i = startPage; i <= endPage; i++) {
				PdfImportedPage page = writerN.getImportedPage(reader, i);
				writerN.addPage(page);
			}
			document1.close();
			writerN.close();
		}

		sourceType.getPackage().getHeader().setDocId(documentID);
		sourceType.getPackage().getHeader().setDocPathUrl(outputFile);
		sourceType.getPackage().getHeader().setRequestId(sourceType.getPackage().getHeader().getRequestId());
		sourceType.getPackage().getHeader().setChannel(sourceType.getPackage().getHeader().getChannel());
		sourceType.getPackage().getHeader().setChannel("PDF");
		sourceType.getPackage().getHeader().setPassword("");
		xgcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
		sourceType.getPackage().getHeader().setRequestTimeStamp(xgcal);

		ic = new IntegrationRequest();
		ic.setSource(sourceType);
		inputXML = RoutingServiceImpl.marshal(ic);
		auditstagecode = "Split  Response";
		setId = RoutingServiceImpl.generateString();
		RoutingServiceImpl.postClient(auditPointURL, RoutingServiceImpl.setAuditPoint(inputXML, createDate,
				updateDate, auditstagecode, setId, reqID, "0"));
		RoutingServiceImpl.postClient(metadataDocumentURL, RoutingServiceImpl
				.setIntegrationMetadataDocument(createDate, updateDate, documentID, outputFile, "", "", reqID));
		
		logger.info("Split Function Done");
		return sourceType;
	}

}
