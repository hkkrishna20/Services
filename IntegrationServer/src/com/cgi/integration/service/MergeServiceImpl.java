package com.cgi.integration.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.slf4j.LoggerFactory;

import com.cgi.integration.models.IntegrationMetadataDocument;
import com.cgi.integration.schema.IntegrationRequest;
import com.cgi.integration.schema.SourceType;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

public class MergeServiceImpl {
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(EndpointClass.class);

	private final String auditURL = "http://localhost:2018/IntegrationRest/audit/save";
	private final String auditPointURL = "http://localhost:2018/IntegrationRest/auditPoint/save";
	private final String metadataURL = "http://localhost:2018/IntegrationRest/metadata/save";
	private final String metadataDocumentURL = "http://localhost:2018/IntegrationRest/metadataDocument/save";
	private final String metadataDocumentFetchURL = "http://localhost:2018/IntegrationRest/metadataDocument/get";

	private List<String> getDocPaths(List<String> docId) {
		List<String> docPaths = new ArrayList<String>();
		Map<String, String> mMap = new HashMap<String, String>();
		logger.info("Inside getDocPaths Function");
		for (int i = 0; i < docId.size(); i++) {
			if (mMap.containsKey(docId.get(i))) {
				docPaths.add(mMap.get(docId.get(i)));
			} else {
			}
		}
		logger.info("Get DocPaths Done");
		return docPaths;
	}

	public static void doMerge(List<InputStream> list, OutputStream outputStream) throws Exception {
		if (list.size() < 1) {

			throw new DetailsNotFoundException("BRE error Required documents are not found for merge ");
		}
		logger.info("Inside doMergeFunction");

		Document document = new Document();
		PdfWriter writer = PdfWriter.getInstance(document, outputStream);
		document.open();
		PdfContentByte cb = writer.getDirectContent();

		for (InputStream in : list) {
			PdfReader reader = new PdfReader(in);
			for (int i = 1; i <= reader.getNumberOfPages(); i++) {
				document.newPage();
				// import the page from source pdf
				PdfImportedPage page = writer.getImportedPage(reader, i);
				// add the page to the destination pdf
				cb.addTemplate(page, 0, 0);
			}
		}

		outputStream.flush();
		document.close();
		outputStream.close();
		logger.info("doMerge Function Done");
	}

	public SourceType merge(SourceType sourceType) throws DatatypeConfigurationException, Exception {
		String documentID = RoutingServiceImpl.generateString();
		GregorianCalendar gcal = new GregorianCalendar();
		XMLGregorianCalendar xgcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
		IntegrationRequest ic = new IntegrationRequest();
		ic.setSource(sourceType);
		String inputXML = "" + RoutingServiceImpl.marshal(ic);
		System.out.println(inputXML);
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
		String auditstagecode = "Merge Gen Req";
		String setId = "" + RoutingServiceImpl.generateString();
		RoutingServiceImpl.postClient(auditPointURL,
				RoutingServiceImpl.setAuditPoint(inputXML, createDate, updateDate, auditstagecode, setId, reqID, "0"));
		sourceType.getPackage().getHeader().setPassword("");
		xgcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
		sourceType.getPackage().getHeader().setRequestTimeStamp(xgcal);

		List<String> docPaths = new ArrayList<String>();
		List<String> docId = sourceType.getPackage().getBody().getMergeDetails().getDocId();
		System.out.println(docId);
		for (int i = 0; i < docId.size(); i++) {
			String input = RoutingServiceImpl
					.getClientXML(metadataDocumentFetchURL + "/" + docId.get(i));
			if(input.toLowerCase().contains("metadatadocument")) {
				System.out.println(input);
				IntegrationMetadataDocument inputFil = (IntegrationMetadataDocument) RoutingServiceImpl
						.convertXMLToObject(IntegrationMetadataDocument.class, input);
				String inputFile = inputFil.getDocpath();
				docPaths.add(inputFile);
			}
		}

		String OutputFolder = "C:\\Users\\Public\\Output\\";
		String outputFile = OutputFolder + documentID + ".pdf";
		List<InputStream> list = new ArrayList<InputStream>();
		for (int i = 0; i < docPaths.size(); i++) {
			list.add(new FileInputStream(new File(docPaths.get(i))));
		}
		OutputStream out = new FileOutputStream(new File(outputFile));
		doMerge(list, out);

		sourceType.getPackage().getHeader().setRequestTimeStamp(xgcal);
		sourceType.getPackage().getHeader().setDocId(documentID);
		sourceType.getPackage().getHeader().setDocPathUrl(outputFile);
		sourceType.getPackage().getHeader().setChannel(sourceType.getPackage().getHeader().getChannel());
		sourceType.getPackage().getHeader().setChannel("PDF");

		ic = new IntegrationRequest();
		ic.setSource(sourceType);
		inputXML = RoutingServiceImpl.marshal(ic);
		auditstagecode = "Merge Response";
		setId = RoutingServiceImpl.generateString();
		RoutingServiceImpl.postClient(auditPointURL,
				RoutingServiceImpl.setAuditPoint(inputXML, createDate, updateDate, auditstagecode, setId, reqID, "0"));
		RoutingServiceImpl.postClient(metadataDocumentURL, RoutingServiceImpl.setIntegrationMetadataDocument(createDate,
				updateDate, documentID, outputFile, "", "", reqID));

		logger.info("Merge Function Done");
		return sourceType;
	}

}
