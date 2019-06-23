package com.cgi.integration.service;

import java.io.FileOutputStream;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.slf4j.LoggerFactory;

import com.cgi.integration.models.WcType;
import com.cgi.integration.schema.FormDetailsType;
import com.cgi.integration.schema.IntegrationRequest;
import com.cgi.integration.schema.SourceType;
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

public class EncommPDFGenerationImpl {
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(EndpointClass.class);
	private final String auditURL = "http://localhost:2018/IntegrationRest/audit/save";
	private final String auditPointURL = "http://localhost:2018/IntegrationRest/auditPoint/save";
	private final String metadataURL = "http://localhost:2018/IntegrationRest/metadata/save";
	private final String metadataDocumentURL = "http://localhost:2018/IntegrationRest/metadataDocument/save";
	private final String contentFetchURL = "http://localhost:2018/IntegrationRest/content/getAll";
	static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
	static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);
	static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
	static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
	public SourceType formGen(SourceType sourceType) throws Exception {
		GregorianCalendar gcal = new GregorianCalendar();
		XMLGregorianCalendar xgcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
		logger.info("Inside form Generation Function" + xgcal);
		IntegrationRequest ic = new IntegrationRequest();
		ic.setSource(sourceType);
		String inputXML = "" + RoutingServiceImpl.marshal(ic);
		Date createDate = xgcal.toGregorianCalendar().getTime();
		String processFamily = "" + sourceType.getPackage().getHeader().getProductFamily();
		String sourceCorrelationID = "" + sourceType.getPackage().getHeader().getParentId();
		String reqID = "" + sourceType.getPackage().getHeader().getRequestId();
		String documentID = reqID;
		Date updateDate = xgcal.toGregorianCalendar().getTime();
		logger.info(inputXML + " \n " + createDate + " \n" + processFamily + "\n " + sourceCorrelationID + " \n" + reqID
				+ " \n" + updateDate);
		RoutingServiceImpl.postClient(auditURL, RoutingServiceImpl.setAudit(inputXML, createDate, processFamily, reqID,
				sourceCorrelationID, updateDate));
		RoutingServiceImpl.postClient(metadataURL,
				RoutingServiceImpl.setMetadata(createDate, updateDate, processFamily, reqID, sourceCorrelationID));
		String auditstagecode = "Form Gen Req";
		String setId = "" + RoutingServiceImpl.generateString();
		RoutingServiceImpl.postClient(auditPointURL,
				RoutingServiceImpl.setAuditPoint(inputXML, createDate, updateDate, auditstagecode, setId, reqID, "0"));
		sourceType.getPackage().getHeader().setPassword("");
		xgcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
		sourceType.getPackage().getHeader().setRequestTimeStamp(xgcal);
		List<FormDetailsType> fr = sourceType.getPackage().getHeader().getFormDetails();
		String userId = sourceType.getPackage().getHeader().getUserId();
		Document document = new Document(PageSize.A4);
		String OutputFolder = "C:\\Users\\Public\\Output\\";
		String outputFile = OutputFolder + documentID + ".pdf";
		WcType content = RoutingServiceImpl.getContentAll(contentFetchURL, fr);
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
		inputXML = RoutingServiceImpl.marshal(ic);
		auditstagecode = "Form Gen Response";
		setId = RoutingServiceImpl.generateString();
		RoutingServiceImpl.postClient(auditPointURL,
				RoutingServiceImpl.setAuditPoint(inputXML, createDate, updateDate, auditstagecode, setId, reqID, "0"));
		RoutingServiceImpl.postClient(metadataDocumentURL, RoutingServiceImpl.setIntegrationMetadataDocument(createDate,
				updateDate, documentID, outputFile, "", "", reqID));
		String email = "encommpoc@outlook.com";
		new SendMail().sendMailMain(outputFile, "application/pdf", email);
		logger.info("Form Generation DOne");
		return sourceType;
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

}
