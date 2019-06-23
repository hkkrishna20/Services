/*package com.encomm.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.transform.stream.StreamSource;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.LoggerFactory;

import com.integration.wc.models.IntegrationAudit;
import com.integration.wc.models.IntegrationAuditPoint;
import com.integration.wc.models.IntegrationMetadata;
import com.integration.wc.schema.FormDetailsType;
import com.integration.wc.schema.IntegrationRequest;
import com.integration.wc.schema.SourceType;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class EncommPDFGenerationImpl {
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(EncommPDFGenerationImpl.class);
	private final String auditURL = "http://localhost:2018/IntegrationRest/audit/save";
	private final String auditPointURL = "http://localhost:2018/IntegrationRest/auditPoint/save";
	private final String metadataURL = "http://localhost:2018/IntegrationRest/metadata/save";
	private final String metadataDocumentURL = "http://localhost:2018/IntegrationRest/metadataDocument/save";
	private final String contentFetchURL = "http://localhost:2018/IntegrationRest/content/getAll";
	static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
	static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);
	static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
	static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
	public static  String marshal(Object object) throws JAXBException {
		  StringWriter stringWriter = new StringWriter();
		  JAXBContext jc = JAXBContext.newInstance( object.getClass());
		  Marshaller m = jc.createMarshaller();
		  m.marshal(object, stringWriter);
		  System.out.println(stringWriter.toString());
		  return stringWriter.toString();
		}

	public static IntegrationAudit setAudit(String inputXML, Date createDate, String processFamily, String reqID,
			String sourceCorrelationID, Date updateDate) throws ParseException {
		IntegrationAudit ia = new IntegrationAudit();
		ia.setContent(inputXML);
		ia.setCreatedAt((Timestamp) createDate);
		ia.setProcessfamily(processFamily);
		ia.setSourcecorelid(sourceCorrelationID);
		ia.setReqid(reqID);
		ia.setUpdatedAt((Timestamp) updateDate);
		return ia;
	}

	public static IntegrationAuditPoint setAuditPoint(String inputXML, Date createDate, Date updateDate,
			String auditstagecode, String setId, String setReqid_FK, String setStatus) throws ParseException {
		IntegrationAuditPoint iap = new IntegrationAuditPoint();
		iap.setAuditstagecode(auditstagecode);
		iap.setCreatedAt((Timestamp) createDate);
		iap.setId(setId);
		iap.setReqid_FK(setReqid_FK);
		iap.setStatus(setStatus);
		iap.setUpdatedAt((Timestamp) updateDate);
		iap.setTransXML(inputXML);
		return iap;
	}

	public static IntegrationMetadata setMetadata(Date createDate, Date updateDate, String processFamily, String reqID,
			String sourceCorrelationID) throws ParseException {
		IntegrationMetadata ia = new IntegrationMetadata();
		ia.setCreatedAt((Timestamp) createDate);
		ia.setUpdatedAt((Timestamp) updateDate);
		ia.setProcessfamily(processFamily);
		ia.setReqid(reqID);
		ia.setSourcecorelid(sourceCorrelationID);
		return ia;
	}

	public static com.integration.wc.models.IntegrationMetadataDocument setIntegrationMetadataDocument(Date createDate, Date updateDate,
			String docID, String docpath, String ecmpath, String filenetpath, String reqid_FK) throws ParseException {
		com.integration.wc.models.IntegrationMetadataDocument iap = new com.integration.wc.models.IntegrationMetadataDocument();
		iap.setCreatedAt((Timestamp) createDate);
		iap.setUpdatedAt((Timestamp) updateDate);
		iap.setDocID(docID);
		iap.setDocpath(docpath);
		iap.setEcmpath(ecmpath);
		iap.setFilenetpath(filenetpath);
		iap.setReqid_FK(reqid_FK);
		return iap;
	}


	public SourceType formGen(SourceType sourceType) throws Exception {
		GregorianCalendar gcal = new GregorianCalendar();
		XMLGregorianCalendar xgcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
		logger.info("Inside form Generation Function" + xgcal);
		IntegrationRequest ic = new IntegrationRequest();
		ic.setSource(sourceType);
		String inputXML = "" + marshal(ic);
		Date createDate = xgcal.toGregorianCalendar().getTime();
		String processFamily = "" + sourceType.getPackage().getHeader().getProductFamily();
		String sourceCorrelationID = "" + sourceType.getPackage().getHeader().getParentId();
		String reqID = "" + sourceType.getPackage().getHeader().getRequestId();
		String documentID = reqID;
		Date updateDate = xgcal.toGregorianCalendar().getTime();
		logger.info(inputXML + " \n " + createDate + " \n" + processFamily + "\n " + sourceCorrelationID + " \n" + reqID
				+ " \n" + updateDate);
		postClient(auditURL, setAudit(inputXML, createDate, processFamily, reqID,
				sourceCorrelationID, updateDate));
		postClient(metadataURL,
				setMetadata(createDate, updateDate, processFamily, reqID, sourceCorrelationID));
		String auditstagecode = "Form Gen Req";
		String setId = "" + generateString();
		postClient(auditPointURL,
				setAuditPoint(inputXML, createDate, updateDate, auditstagecode, setId, reqID, "0"));
		sourceType.getPackage().getHeader().setPassword("");
		xgcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
		sourceType.getPackage().getHeader().setRequestTimeStamp(xgcal);
		List<FormDetailsType> fr = sourceType.getPackage().getHeader().getFormDetails();
		String userId = sourceType.getPackage().getHeader().getUserId();
		Document document = new Document(PageSize.A4);
		String OutputFolder = "C:\\Users\\Public\\Output\\";
		String outputFile = OutputFolder + documentID + ".pdf";
		
		
		Map<String, String> map = new HashMap<>();
		for (int j = 0; j < content.getHeader().getContentTs().size(); j++) {
			if (null != content.getHeader().getContentTs().get(j).getFormId().trim()) {
				System.out.println(content.getHeader().getContentTs().get(j).getFormId().trim());
				System.out.println(content.getHeader().getContentTs().get(j).getContent());
				map.put(content.getHeader().getContentTs().get(j).getFormId().trim(),
						content.getHeader().getContentTs().get(j).getContent());
			}
		}
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(outputFile));
		document.open();
		document = addMetaData(document);
		document = addTitlePage(document);
		for (int i = 0; i < fr.size(); i++) {
			FormDetailsType f1 = fr.get(i);
			String formTemplateID = f1.getFormId();
			if (null != formTemplateID)
				System.out.println(formTemplateID);
			else {
				formTemplateID = "";
			}
			String formTitles = f1.getFormTitle();
			if (null != formTitles)
				System.out.println(formTitles);
			else 
				formTitles = "";
			if (formTemplateID.trim().isEmpty())
				continue;
			String value = map.get(formTemplateID.trim());
			System.out.println(value);
			document = addMetaData(document, formTemplateID, userId, formTitles, value, writer);
		}
		document.close();
		sourceType.getPackage().getHeader().setRequestTimeStamp(xgcal);
		sourceType.getPackage().getHeader().setDocId(documentID);
		sourceType.getPackage().getHeader().setDocPathUrl(outputFile);
		sourceType.getPackage().getHeader().setRequestId(sourceType.getPackage().getHeader().getRequestId());
		sourceType.getPackage().getHeader().setChannel(sourceType.getPackage().getHeader().getChannel());
		sourceType.getPackage().getHeader().setChannel("PDF");
		ic = new IntegrationRequest();
		ic.setSource(sourceType);
		inputXML = marshal(ic);
		auditstagecode = "Form Gen Response";
		setId = generateString();
		postClient(auditPointURL,
				setAuditPoint(inputXML, createDate, updateDate, auditstagecode, setId, reqID, "0"));
		postClient(metadataDocumentURL, setIntegrationMetadataDocument(createDate,
				updateDate, documentID, outputFile, "", "", reqID));
		String email = "encommpoc@outlook.com";
		new SendMail().sendMailMain(outputFile, "application/pdf", email);
		logger.info("Form Generation DOne");
		return sourceType;
	}

	static String generateString() {
		String uuid = UUID.randomUUID().toString();
		return uuid;
	}

	private Document addMetaData(Document document, String formTitle, String userId, String formTitles,
			String contentPrint, PdfWriter writer) throws DocumentException {
		logger.info("Inside addMetadata Function");
		System.out.println("Inside addMetadata Function -------------------------------- \n" + contentPrint + "\n");
		Rectangle page = document.getPageSize();
		PdfPTable foot = new PdfPTable(1);
		foot.addCell(formTitles + " \t\t" + formTitle);
		foot.setTotalWidth(page.getWidth() - document.leftMargin() - document.rightMargin());
		foot.writeSelectedRows(0, -1, document.leftMargin(), document.bottomMargin(), writer.getDirectContent());
		PdfPTable head = new PdfPTable(1);
		foot.addCell(formTitles + " \t\t" + formTitle);
		head.setTotalWidth(page.getWidth() - document.leftMargin() - document.rightMargin());
		head.writeSelectedRows(0, -1, document.leftMargin(),
				page.getHeight() - document.topMargin() + head.getTotalHeight(), writer.getDirectContent());
		Paragraph paragraph2 = new Paragraph();
		paragraph2.setAlignment(Element.ALIGN_LEFT);
		Chunk chunk = new Chunk(contentPrint);
		paragraph2.add(chunk);
		document.add(paragraph2);
		PdfContentByte cb = writer.getDirectContent();
		String code402 = "" + Barcode128.FNC1;
		String code90 = "EncommPOC" + Barcode128.FNC1;
		String code421 = "";
		String data = code402 + code90 + code421;
		Barcode128 shipBarCode = new Barcode128();
		shipBarCode.setX(0.75f);
		shipBarCode.setN(1.5f);
		shipBarCode.setChecksumText(true);
		shipBarCode.setGenerateChecksum(true);
		shipBarCode.setSize(10f);
		shipBarCode.setTextAlignment(Element.ALIGN_CENTER);
		shipBarCode.setBaseline(10f);
		shipBarCode.setCode(data);
		shipBarCode.setBarHeight(50f);
		Image imgShipBarCode = shipBarCode.createImageWithBarcode(cb, BaseColor.BLACK, BaseColor.BLUE);
		document.add(imgShipBarCode);
		document.newPage();
		return document;
	}

	private static Document addTitlePage(Document document) throws DocumentException {
		Paragraph preface = new Paragraph();
		// We add one empty line
		addEmptyLine(preface, 1);
		// Lets write a big header
		preface.add(new Paragraph("Encomm POC  document", catFont));
		addEmptyLine(preface, 1);
		// Will create: Report generated by: _name, _date
		preface.add(new Paragraph("Report generated by: Encomm POC user Team" + ", " + new Date(), //$NON-NLS-1$ //$NON-NLS-2$
																									// //$NON-NLS-3$
				smallBold));
		addEmptyLine(preface, 8);
		preface.add(new Paragraph("This document is a preliminary version.", redFont));
		document.add(preface);
		// Start a new page
		document.newPage();
		return document;
	}

	private static Document addMetaData(Document document) {
		document.addTitle("Form Generate Module");
		document.addSubject("Using iText");
		document.addKeywords("Java, PDF, iText");
		document.addAuthor("Hari Krishna");
		document.addCreator("Hari Krishna");
		return document;
	}

	private static void addEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
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


}
*/