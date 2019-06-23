package com.encomm.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.validation.Valid;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.io.FileUtils;
import org.apache.maven.artifact.versioning.DefaultArtifactVersion;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.encomm.models.content.entityTypeP.CompareList;
import com.encomm.models.content.entityTypeP.ContentInfoStatus;
import com.encomm.models.content.entityTypeP.ContentObj;
import com.encomm.models.content.entityTypeP.FileTableType;
import com.encomm.models.content.entityTypeP.FileTitleInfo;
import com.encomm.models.content.entityTypeP.ListofResponseObject;
import com.encomm.models.content.entityTypeP.ParaType;
import com.encomm.models.content.entityTypeP.Preview;
import com.encomm.models.content.entityTypeP.ResponseObject;
import com.encomm.service.EncommContentService;
import com.encomm.service.EncommFileService;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * @author HDMI
 *
 */
@SuppressWarnings("unused")
@CrossOrigin(origins = "http://localhost:4200")
@ComponentScan(basePackages = "com")
@RestController
public class UpdatedRestController {
	@Autowired
	EncommFileService encommFileTableTypeService;
	@Autowired
	EncommContentService encommContentTypeService;

	@RequestMapping(value = "getCompareParalist", method = RequestMethod.POST)
	@CrossOrigin(origins = { "http://localhost:6003" })
	public CompareList getCompareParalist(@RequestParam String fileName, @RequestParam String baseFileVersion,
			@RequestParam String compareFileVersion) throws IOException, JAXBException {
		List<FileTableType> fileTableTypes = encommFileTableTypeService.getAll();
		System.out.println(baseFileVersion);
		System.out.println(compareFileVersion);
		Integer baseFileId = 0;
		Integer compareFileId = 0;
		CompareList compList = new CompareList();
		for (int i = 0; i < fileTableTypes.size(); i++) {
			if (fileName.equals(fileTableTypes.get(i).getFormTitle())) {
				if (new Float(fileTableTypes.get(i).getVersionNo()).compareTo(Float.valueOf(baseFileVersion)) == 0) {
					baseFileId = fileTableTypes.get(i).getFileId();
					break;
				}

			}
		}
		for (int i = 0; i < fileTableTypes.size(); i++) {
			if (fileName.equals(fileTableTypes.get(i).getFormTitle())) {
				if (new Float(fileTableTypes.get(i).getVersionNo()).compareTo(Float.valueOf(compareFileVersion)) == 0) {
					{
						compareFileId = fileTableTypes.get(i).getFileId();
						break;
					}

				}
			}
		}
		List<String> baseFileVersionList = new ArrayList<String>();
		List<String> compareFileVersionList = new ArrayList<String>();
		FileTableType baseFile = encommFileTableTypeService.get(baseFileId);
		FileTableType compareFile = encommFileTableTypeService.get(compareFileId);
		if (baseFile == null || compareFile == null) {
			System.out.println("");
			compList.setFileName(fileName);
			compList.setBaseVersionParaList(baseFileVersionList);
			compList.setCompareVersionParaList(compareFileVersionList);
			return compList;
		}
		System.out.println(baseFile.getParas().size());
		System.out.println(compareFile.getParas().size());
		List<ParaType> contentTableList = encommContentTypeService.getAll();
		for (int i = 0; i < contentTableList.size(); i++) {
			ParaType para = contentTableList.get(i);
			if (para.getFileId() == baseFileId) {
				System.out.println("Base File Content" + para.getContent());
				String content = para.getContent();
				if (content.contains("\\,")) {
					content = content.replace("\\,", ",");
				}
				if (content.contains("\\'")) {
					content = content.replace("\\'", "'");
				}

				baseFileVersionList.add(content);
			}
			if (para.getFileId() == compareFileId) {
				String content = para.getContent();
				if (content.contains("\\,")) {
					content = content.replace("\\,", ",");
				}
				if (content.contains("\\'")) {
					content = content.replace("\\'", "'");
				}
				System.out.println("compareFileIdContent" + para.getContent());
				compareFileVersionList.add(content);
			}
		}

		for (int j = 0; j < baseFile.getParas().size(); j++) {
			baseFileVersionList.add(baseFile.getParas().get(j).getContent());
		}
		for (int j = 0; j < compareFile.getParas().size(); j++) {
			compareFileVersionList.add(compareFile.getParas().get(j).getContent());
		}
		compList.setFileName(fileName);
		compList.setBaseVersionParaList(baseFileVersionList);
		compList.setCompareVersionParaList(compareFileVersionList);
		System.out.println("");
		CompareList c = new CompareList();
		return compList;
	}

	@RequestMapping(value = "deleteFileAndContentlist/{fileName}", method = RequestMethod.GET)
	@CrossOrigin(origins = { "http://localhost:6003" })
	public ResponseEntity<?> deleteFileAndContentlist(@PathVariable String fileName, UriComponentsBuilder ucBuilder)
			throws IOException {
		List<FileTableType> fileTableTypes = encommFileTableTypeService.getAll();
		for (int i = 0; i < fileTableTypes.size(); i++) {
			if (fileName.equals(fileTableTypes.get(i).getFormTitle())) {
				FileTableType baseFile = encommFileTableTypeService.get(fileTableTypes.get(i).getFileId());
				List<ParaType> list = encommContentTypeService.getAll();
				List<ParaType> contentTable = new ArrayList<ParaType>();
				for (int j = 0; j < list.size(); j++) {
					if (list.get(i).getFileId() == fileTableTypes.get(i).getFileId()) {
						contentTable.add(list.get(i));
					}
				}
				for (ParaType s : contentTable)
					encommContentTypeService.remove(s);
				encommFileTableTypeService.remove(baseFile);
			}

		}

		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "saveList", method = RequestMethod.POST)
	@CrossOrigin(origins = { "http://localhost:6003" })
	public ContentInfoStatus saveList(@RequestParam String fileName, @RequestParam String version,
			@RequestParam List<String> contentList) throws IOException {
		ContentInfoStatus status = new ContentInfoStatus();

		DefaultArtifactVersion minVersion = new DefaultArtifactVersion("");
		DefaultArtifactVersion maxVersion = new DefaultArtifactVersion("");
		DefaultArtifactVersion versionNeww = new DefaultArtifactVersion("");
		int fileId = 0;
		String formId = "";
		float versionNew = 0;
		List<FileTableType> fileTableTypes = encommFileTableTypeService.getAll();
		int count = 0;
		float versionRest = 0;
		if (!(version.equals("new"))) {
			versionRest = Float.valueOf(version);
		} else {
			versionRest = -1;
		}
		for (int i = 0; i < fileTableTypes.size(); i++) {
			if (fileName.equals(fileTableTypes.get(i).getFormTitle())) {
				if (count == 0) {
					minVersion = new DefaultArtifactVersion("" + fileTableTypes.get(i).getVersionNo());
					maxVersion = new DefaultArtifactVersion("" + fileTableTypes.get(i).getVersionNo());
					count++;
				}

				DefaultArtifactVersion testVersion = new DefaultArtifactVersion(
						"" + fileTableTypes.get(i).getVersionNo());
				if (testVersion.compareTo(minVersion) < 0) {
					minVersion = testVersion;
				}
				if (testVersion.compareTo(minVersion) > 0) {
					maxVersion = testVersion;
				}
				if (new Float(fileTableTypes.get(i).getVersionNo()).compareTo(versionRest) == 0) {
					{
						formId = fileTableTypes.get(i).getFormId();
						fileId = fileTableTypes.get(i).getFileId();
					}

				}
			}
		}

		DefaultArtifactVersion testVersion = new DefaultArtifactVersion("");
		float f = 0;
		if (version.equals("new")) {
			double value = Float.valueOf(maxVersion.toString());
			value = value + 0.1;
			version = "" + value;
		}
		String paraList = "";
		for (int i = 0; i < contentList.size(); i++) {
			String fileContent = contentList.get(i);
			if (fileContent.contains("&")) {
				fileContent = fileContent.replaceAll("&", "~");
			}
			paraList = paraList + "<ParaType><paraSeq>" + (i + 1) + "</paraSeq><content>" + fileContent
					+ "</content></ParaType>";

		}

		String testString = "<fileTable><formId>" + formId + "</formId><formTitle>" + fileName
				+ "</formTitle><versionNo>" + version + "</versionNo>" + paraList + "</fileTable>";

		System.out.println(testString);

		String urlSave = "http://localhost:2018/IntegrationRest/encomm/save";
		postClient(urlSave, testString, "application/xml");

		status.setStatus("success");
		status.setVersion(version);
		System.out.println(fileName);

		return status;

	}

	@CrossOrigin(origins = { "http://localhost:4200", "http://10.154.75.96:6003", "http://localhost:6003" })
	@RequestMapping(value = "/encomm/save", method = { RequestMethod.PUT, RequestMethod.POST }, consumes = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> saveToDatabase(@Valid @RequestBody String file, UriComponentsBuilder ucBuilder)
			throws JAXBException {

		List<FileTableType> fileFrmDb1 = encommFileTableTypeService.getAll();
		List<Integer> versionFileIds = new ArrayList<Integer>();
		List<ParaType> contentDbFet = null;
		contentDbFet = encommContentTypeService.getAll();

		if (file.contains("&")) {
			file = file.replaceAll("&", "~");
		}
		if (file.contains("'")) {
			file = file.replace("'", "\\'");
		}
		if (file.contains(",")) {
			file = file.replace(",", "\\,");
		}
		JAXBContext jc = JAXBContext.newInstance(com.encomm.models.content.entityTypeP.ObjectFactory.class);
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		StreamSource sc = new StreamSource(new StringReader(file));
		JAXBElement<FileTableType> bookingElement = (JAXBElement<FileTableType>) unmarshaller.unmarshal(sc);
		FileTableType requestObject = bookingElement.getValue();
		boolean check = false;
		int dbFileID = 0;
		int fileId = 0;
		for (FileTableType fileFrmDb : fileFrmDb1) {
			if (requestObject.getFormTitle().equalsIgnoreCase(fileFrmDb.getFormTitle())) {
				if (new Float(fileFrmDb.getVersionNo()).compareTo(Float.valueOf(requestObject.getVersionNo())) == 0) {
					fileId = fileFrmDb.getFileId();
					dbFileID = fileFrmDb.getFileId();
					// break;
				}
				
				versionFileIds.add(dbFileID);
				// encommFileTableTypeService.remove(request);
			}
		}

		if (requestObject.getParas().size() < 1) {
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/encomm/get/{id}").buildAndExpand("").toUri());
			return new ResponseEntity<String>(headers, HttpStatus.CREATED);
		}
		try {
			if (fileId == 0) {
				fileId = (int) ((Math.random() * 900000000) + 100000000);
				requestObject.setFileId(fileId);
				encommFileTableTypeService.saveOrUpdate(requestObject);
			}
			else {
				requestObject.setFileId(fileId);
				encommFileTableTypeService.saveOrUpdate(requestObject);
			}
		
			for (int i = 0; i < requestObject.getParas().size(); i++) {
				if (0 == requestObject.getParas().get(i).getFileId()) {
					requestObject.getParas().get(i).setFileId(fileId);
				}else {
					requestObject.getParas().get(i).setFileId(dbFileID);
				}
			}

			if (null == contentDbFet || contentDbFet.isEmpty() || (contentDbFet.size() < 1)) {
				for (int i = 0; i < requestObject.getParas().size(); i++) {
					int contentID = 0;
					contentID = (int) ((Math.random() * 900000000) + 100000000);
					requestObject.getParas().get(i).setContentId(contentID);
					encommContentTypeService.saveOrUpdate(requestObject.getParas().get(i));
				}
			} else {
				List<ParaType> contentListForFileID = new ArrayList<ParaType>();
				List<ParaType> contentListForRequest = new ArrayList<ParaType>();
				contentListForRequest = requestObject.getParas();
				for (int i = 0; i < contentDbFet.size(); i++) {
					if (contentDbFet.get(i).getFileId() == requestObject.getFileId()) {
						ParaType para = encommContentTypeService.find(contentDbFet.get(i).getContentId());
						contentListForFileID.add(para);
						
						// encommContentTypeService.remove(para);
					}
				}
				Map<Integer, String> map = new HashMap<>();
				for (int i = 0; i < contentListForFileID.size(); i++) {
					ParaType para = contentListForFileID.get(i);
					map.put(para.getParaSeq(), para.getContent());
				}
				for (int i = 0; i < contentListForRequest.size(); i++) {
					ParaType para = contentListForRequest.get(i);
					map.put(para.getParaSeq(), para.getContent());
				}
				for (int i = 0; i < contentDbFet.size(); i++) {
					if (contentDbFet.get(i).getFileId() == requestObject.getFileId()) {
						ParaType para = encommContentTypeService.find(contentDbFet.get(i).getContentId());
						 encommContentTypeService.remove(para);
					}
				}
				requestObject.setFileId(fileId);
				encommFileTableTypeService.saveOrUpdate(requestObject);
				List<ParaType> contentListAfterSort = new ArrayList<ParaType>();
				System.out.println("Before Sorting:");
				Set set = map.entrySet();
				Iterator iterator = set.iterator();
				while (iterator.hasNext()) {
					Map.Entry me = (Map.Entry) iterator.next();
					System.out.print(me.getKey() + ": ");
					System.out.println(me.getValue());
				}
				Map<Integer, String> hmap = new TreeMap<Integer, String>(map);
				System.out.println("After Sorting:");
				Set set2 = map.entrySet();
				Iterator iterator2 = set2.iterator();
				while (iterator2.hasNext()) {
					Map.Entry me2 = (Map.Entry) iterator2.next();
					System.out.print(me2.getKey() + ": ");
					System.out.println();
					ParaType para = new ParaType();
					para.setContent("" + me2.getValue());
					para.setFileId(fileId);
					para.setFileTableType(requestObject);
					para.setParaSeq(Integer.parseInt(me2.getKey().toString()));
					int contentID = 0;
					contentID = (int) ((Math.random() * 900000000) + 100000000);
					para.setContentId(contentID);

					/*
					 * int contentID = 0; contentID = (int) ((Math.random() * 900000000) +
					 * 100000000); 
					 */
					encommContentTypeService.saveOrUpdate(para);
				}
			}
		} catch (NoSuchElementException e) {
			contentDbFet = null;
			for (int i = 0; i < requestObject.getParas().size(); i++) {
				int contentID = 0;
				contentID = (int) ((Math.random() * 900000000) + 100000000);
				if (0 == requestObject.getParas().get(i).getContentId()) {
					requestObject.getParas().get(i).setContentId(contentID);
					requestObject.getParas().get(i).setFileId(fileId);
					encommContentTypeService.saveOrUpdate(requestObject.getParas().get(i));
				}
			}
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/encomm/get/{id}").buildAndExpand(fileId).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	@CrossOrigin(origins = { "http://localhost:4200", "http://10.154.75.96:6003", "http://localhost:6003" })
	@RequestMapping(value = "/encomm/getAll", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public String getAll(Pageable pageable) throws Exception {
		List<ResponseObject> listofResponses = new ArrayList<ResponseObject>();
		List<FileTableType> fileTableTypes = encommFileTableTypeService.getAll();
		List<ParaType> ParaTypes = encommContentTypeService.getAll();
		if (fileTableTypes == null || fileTableTypes.size() < 0 || fileTableTypes.isEmpty()) {
			return null;
		}
		for (int i = 0; i < fileTableTypes.size(); i++) {
			ResponseObject response = new ResponseObject();
			FileTableType inc = fileTableTypes.get(i);
			response.setFileId(inc.getFileId());
			response.setFormId(inc.getFormId());
			response.setFormTitle(inc.getFormTitle());
			response.setVersionNo(inc.getVersionNo());
			for (int j = 0; j < ParaTypes.size(); j++) {
				ContentObj obj = new ContentObj();
				if (inc.getFileId() == ParaTypes.get(j).getFileId()) {
					String content = ParaTypes.get(j).getContent();
					if (content.contains("\\,")) {
						content = content.replace("\\,", ",");
					}
					if (content.contains("\\'")) {
						content = content.replace("\\'", "'");
					}
					obj.setContent(content);
					obj.setContentId(ParaTypes.get(j).getContentId());
					obj.setParaSeq(ParaTypes.get(j).getParaSeq());
					response.getParaType().add(obj);
				}
			}
			listofResponses.add(response);
		}
		ListofResponseObject obj = new ListofResponseObject();
		obj.setResponses(listofResponses);
		String o = convertObjectToXML(obj);
		return o;
	}

	@CrossOrigin(origins = { "http://localhost:4200", "http://10.154.75.96:6003", "http://localhost:6003" })
	@RequestMapping(value = "/encomm/get/{id}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public String get(@PathVariable("id") String id) throws Exception {
		FileTableType inc = encommFileTableTypeService.find(Integer.parseInt(id));
		if (inc == null)
			return null;
		ResponseObject response = new ResponseObject();
		response.setFileId(inc.getFileId());
		response.setFormId(inc.getFormId());
		response.setFormTitle(inc.getFormTitle());
		response.setVersionNo(inc.getVersionNo());
		List<ParaType> ParaTypes = encommContentTypeService.getAll();
		for (int j = 0; j < ParaTypes.size(); j++) {
			ContentObj obj = new ContentObj();
			if (inc.getFileId() == ParaTypes.get(j).getFileId()) {
				String content = ParaTypes.get(j).getContent();
				if (content.contains("\\,")) {
					content = content.replace("\\,", ",");
				}
				if (content.contains("\\'")) {
					content = content.replace("\\'", "'");
				}

				obj.setContent(content);
				obj.setContentId(ParaTypes.get(j).getContentId());
				obj.setParaSeq(ParaTypes.get(j).getParaSeq());
				response.getParaType().add(obj);
			}
		}
		List<ResponseObject> resp = new ArrayList<ResponseObject>();
		resp.add(response);
		ListofResponseObject obj = new ListofResponseObject();
		obj.setResponses(resp);
		String o = convertObjectToXML(obj);
		return o;
	}

	/**
	 * HTTP DELETE - Delete
	 */
	@CrossOrigin(origins = { "http://localhost:4200", "http://10.154.75.96:6003", "http://localhost:6003" })
	@RequestMapping(value = "/encomm/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deletePost(@PathVariable("id") String id) {
		FileTableType inc = encommFileTableTypeService.find(Integer.parseInt(id));
		Collection<ParaType> contentTable = inc.getParas();
		if (inc != null) {
			for (ParaType s : contentTable)
				encommContentTypeService.remove(s);
		}
		return new ResponseEntity<String>(HttpStatus.GONE);
	}

	/**
	 * @param toEmail
	 * @param ccEmail
	 * @param bccEmail
	 * @param fileName
	 * @param version
	 * @param subject
	 * @param body
	 * @throws Exception
	 */
	@RequestMapping(value = "sendMail", method = RequestMethod.POST)
	@CrossOrigin(origins = { "http://localhost:6003" })
	void send(@RequestParam String toEmail, @RequestParam String ccEmail, @RequestParam String bccEmail,
			@RequestParam String fileName, @RequestParam String version, @RequestParam String subject,
			@RequestParam String body) throws Exception {
		String base64EncodeString = "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O1Kl/Rdh4yueLlaitJDdqDLmrzRpJUkrSQrxbK8gbF1FWSoAxBeRGCCRUIBu0ifIzSyfGsu3fmvm9X3uQWy7Gdh/7tXVaO8lbTIvp+/LzzSdP46H9mVOzfwB2X/O4gplbmRzdHJlYW0KZW5kb2JqCjUgMCBvYmoKPDwvVHlwZS9QYWdlcy9Db3VudCA3L0tpZHNbNiAwIFIgOCAwIFIgMTAgMCBSIDEzIDAgUiAxNSAwIFIgMTggMCBSIDIxIDAgUl0+PgplbmRvYmoKMjIgMCBvYmoKPDwvVHlwZS9DYXRhbG9nL1BhZ2VzIDUgMCBSPj4KZW5kb2JqCjIzIDAgb2JqCjw8L1Byb2R1Y2VyKGlUZXh0riA1LjUuMTAgqTIwMDAtMjAxNSBpVGV4dCBHcm91cCBOViBcKEFHUEwtdmVyc2lvblwpKS9DcmVhdGlvbkRhdGUoRDoyMDE4MDcwNDE1MDc1OSswNSczMCcpL01vZERhdGUoRDoyMDE4MDcwNDE1MDc1OSswNSczMCcpL1RpdGxlKEZvcm0gR2VuZXJhdGUgTW9kdWxlKS9TdWJqZWN0KFVzaW5nIGlUZXh0KS9LZXl3b3JkcyhKYXZhLCBQREYsIGlUZXh0KS9BdXRob3IoSGFyaSBLcmlzaG5hKS9DcmVhdG9yKEhhcmkgS3Jpc2huYSk+PgplbmRvYmoKeHJlZgowIDI0CjAwMDAwMDAwMDAgNjU1MzUgZiAKMDAwMDAwOTM2NyAwMDAwMCBuIAowMDAwMDA5NDU1IDAwMDAwIG4gCjAwMDAwMDk1NDQgMDAwMDAgbiAKMDAwMDAwMDAxNSAwMDAwMCBuIAowMDAwMDEwOTEyIDAwMDAwIG4gCjAwMDAwMDAyNzkgMDAwMDAgbiAKMDAwMDAwMDQwOSAwMDAwMCBuIAowMDAwMDAyMjE5IDAwMDAwIG4gCjAwMDAwMDIzMzEgMDAwMDAgbiAKMDAwMDAwMzkwNiAwMDAwMCBuIAowMDAwMDEwNDg2IDAwMDAwIG4gCjAwMDAwMDQwMTkgMDAwMDAgbiAKMDAwMDAwNDYwMyAwMDAwMCBuIAowMDAwMDA0NzQwIDAwMDAwIG4gCjAwMDAwMDYyNjcgMDAwMDAgbiAKMDAwMDAwOTYzNCAwMDAwMCBuIAowMDAwMDA2MzgxIDAwMDAwIG4gCjAwMDAwMDc3MjUgMDAwMDAgbiAKMDAwMDAxMDA2MCAwMDAwMCBuIAowMDAwMDA3ODYyIDAwMDAwIG4gCjAwMDAwMDkyMzAgMDAwMDAgbiAKMDAwMDAxMTAwNCAwMDAwMCBuIAowMDAwMDExMDUwIDAwMDAwIG4gCnRyYWlsZXIKPDwvU2l6ZSAyNC9Sb290IDIyIDAgUi9JbmZvIDIzIDAgUi9JRCBbPDYxNGRlYjZkYzNmNWY1YTQ2MWI2ODIwYjUyNDZiYjM3Pjw2MTRkZWI2ZGMzZjVmNWE0NjFiNjgyMGI1MjQ2YmIzNz5dPj4KJWlUZXh0LTUuNS4xMApzdGFydHhyZWYKMTEzMjgKJSVFT0YK";
		byte[] decoded = Base64.getDecoder().decode(base64EncodeString);
		String path = "C:\\Users\\Public\\POC Files\\actualFile.pdf";
		Path paths = new File(path).toPath();
		Files.write(paths, decoded);
		if (toEmail == null)
			toEmail = "";
		if (ccEmail == null)
			ccEmail = "";
		if (bccEmail == null)
			bccEmail = "";
		if (body == null)
			body = "";
		if (subject == null)
			subject = "";
		if (ccEmail.trim().isEmpty() && bccEmail.trim().isEmpty() && toEmail.trim().isEmpty()) {
			return;
		}
		String saveFile = "";
		int fileId = 0;
		String formId = "";
		String reqID = "" + generateString();
		String documentID = reqID;
		byte[] pdfBytes = generateFile(version, fileName);
		String outputFile = "C:\\Users\\Public\\Output\\" + reqID + ".pdf";
		// byte[] pdfBytes = byteArrayOutputStream.toByteArray();
		FileUtils.writeByteArrayToFile(new File(outputFile), pdfBytes);
		final String username = "encommpoc@gmail.com";
		final String password = "thunderhead";
		Properties props = new Properties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", "smtp.googlemail.com");
		props.put("mail.smtp.port", "587");
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		try {
			MimeMessage message = new MimeMessage(session);
			String[] bccListArray = bccEmail.split(";");
			String[] ccListArray = ccEmail.split(";");
			String[] toListArray = toEmail.split(";");
			List<String> cclist = Arrays.asList(ccListArray);
			List<String> bcclist = Arrays.asList(bccListArray);
			List<String> tolist = Arrays.asList(toListArray);

			if (!(ccEmail.trim().isEmpty())) {
				if (ccListArray != null && cclist.size() > 0) {
					final Address[] toCcAddress = new InternetAddress[cclist.size()];
					for (int i = 0; i < toCcAddress.length; i++) {
						toCcAddress[i] = new InternetAddress(cclist.get(i));
					}
					message.addRecipients(Message.RecipientType.CC, toCcAddress);
				}

			}
			if (!(bccEmail.trim().isEmpty())) {
				if (bccListArray != null && bcclist.size() > 0) {
					final Address[] tobCcAddress = new InternetAddress[bcclist.size()];
					for (int i = 0; i < tobCcAddress.length; i++) {
						tobCcAddress[i] = new InternetAddress(bcclist.get(i));
					}
					message.addRecipients(Message.RecipientType.BCC, tobCcAddress);
				}
			}
			if (!(toEmail.trim().isEmpty())) {
				if (toListArray != null && tolist.size() > 0) {
					final Address[] toAddress = new InternetAddress[tolist.size()];
					for (int i = 0; i < toAddress.length; i++) {
						toAddress[i] = new InternetAddress(tolist.get(i));
					}
					message.addRecipients(Message.RecipientType.TO, toAddress);
				}
			}
			message.setFrom(new InternetAddress("encommpoc@gmail.com"));
			message.setSubject(subject);
			Multipart multipart = new MimeMultipart();
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText(body, "utf-8", "html");
			multipart.addBodyPart(messageBodyPart);
			MimeBodyPart attachmentBodyPart = new MimeBodyPart();
			if (fileName == null || version == null)
				attachmentBodyPart.attachFile(new File(path), "application/pdf", null);
			else
				attachmentBodyPart.attachFile(new File(outputFile), "application/pdf", null);
			multipart.addBodyPart(attachmentBodyPart);
			message.setContent(multipart);
			Transport.send(message);
			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	@RequestMapping(value = "genFile/{fileName}", method = RequestMethod.GET)
	@CrossOrigin(origins = { "http://localhost:6003", "http://localhost:8086" })
	public String genFile(@PathVariable(name = "fileName") String fileName) throws Exception {
		String[] files = fileName.split("~");
		fileName = files[0];
		String version = files[1];
		String reqID = "" + generateString();
		String documentID = reqID;
		byte[] pdf = generateFile(version, fileName);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.add("Access-Control-Allow-Origin", "*");
		headers.add("Access-Control-Allow-Methods", "GET, POST, PUT");
		headers.add("Access-Control-Allow-Headers", "Content-Type");
		headers.add("Content-Disposition", "filename=" + documentID + ".pdf");
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");
		headers.setContentLength(pdf.length);
		ResponseEntity<byte[]> response1 = new ResponseEntity<byte[]>(pdf, headers, HttpStatus.OK);
		return "Success";
	}

	@RequestMapping(value = "getPDF", method = RequestMethod.POST)
	@CrossOrigin(origins = { "http://localhost:6003" })
	public ResponseEntity<?> getPDF(@RequestParam String fileName, @RequestParam String version) throws Exception {
		String reqID = "" + generateString();
		String documentID = reqID;
		byte[] pdf = generateFile(version, fileName);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.add("Access-Control-Allow-Origin", "*");
		headers.add("Access-Control-Allow-Methods", "GET, POST, PUT");
		headers.add("Access-Control-Allow-Headers", "Content-Type");
		headers.add("Content-Disposition", "filename=" + documentID + ".pdf");
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");
		headers.setContentLength(pdf.length);
		/*
		 * HttpHeaders headers = new HttpHeaders();
		 * headers.setContentType(MediaType.APPLICATION_PDF);
		 * headers.add("Access-Control-Allow-Headers", "Content-Type");
		 * headers.add("Content-Disposition", "filename=" + outputFile); File filess =
		 * new File(outputFile); FileInputStream fileStream = new
		 * FileInputStream(filess); Path pdfPath = Paths.get(outputFile); byte[] pdf =
		 * Files.readAllBytes(pdfPath); String encoded =
		 * Base64.getEncoder().encodeToString(pdf);
		 * headers.setContentLength(pdf.length); ResponseEntity<String> response = new
		 * ResponseEntity<String>(encoded, headers, HttpStatus.OK);
		 */
		FileUtils.writeByteArrayToFile(new File("C:\\Users\\Public\\Output\\" + reqID + ".pdf"), pdf);
		InputStreamResource resource = new InputStreamResource(
				new FileInputStream(new File("C:\\Users\\Public\\Output\\" + reqID + ".pdf")));

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + reqID + ".pdf")
				.contentType(MediaType.APPLICATION_PDF)
				.contentLength(new File("C:\\Users\\Public\\Output\\" + reqID + ".pdf").length()).body(resource);
		// return response;
	}

	byte[] generateFile(String version, String fileName) throws Exception {
		List<FileTableType> fileTableTypes = encommFileTableTypeService.getAll();
		int fileId = 0;
		CompareList compList = new CompareList();
		for (int i = 0; i < fileTableTypes.size(); i++) {
			if (fileName.equals(fileTableTypes.get(i).getFormTitle())) {
				if (new Float(fileTableTypes.get(i).getVersionNo()).compareTo(Float.valueOf(version)) == 0) {
					fileId = fileTableTypes.get(i).getFileId();
					break;
				}

			}
		}

		if (fileId == 0) {
			return null;
		}
		FileTableType file = encommFileTableTypeService.find(fileId);
		String url = "http://localhost:2018/IntegrationRest/encomm/get/" + fileId;
		String output = getClient(url);
		if (output == null) {
			return null;
		}

		JAXBContext jaxbContext = JAXBContext.newInstance(ListofResponseObject.class);
		StringReader reader = new StringReader(output);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		ListofResponseObject objOutput = (ListofResponseObject) jaxbUnmarshaller.unmarshal(reader);
		List<Integer> paraSortList = new ArrayList<Integer>();
		for (int i = 0; i < objOutput.getResponses().get(0).getParaType().size(); i++) {
			paraSortList.add(objOutput.getResponses().get(0).getParaType().get(i).getParaSeq());
		}
		Collections.sort(paraSortList);
		System.out.println(paraSortList);
		String reqID = "" + generateString();
		String documentID = reqID;
		String OutputFolder = "C:\\Users\\Public\\Output\\";
		fileName = fileName.replace(".", "");
		String outputFile = OutputFolder + reqID + ".pdf";
		File fileTest = new File(outputFile);
		FileOutputStream fileout = new FileOutputStream(fileTest);
		Document document = new Document(PageSize.A4);
		PdfWriter writer = PdfWriter.getInstance(document, fileout);
		document.open();
		// document = addMetaData(document);
		// document = addTitlePage(document);
		document = addMetaData(document, writer, paraSortList, objOutput);
		document.close();

		File filess = new File(outputFile);
		FileInputStream fileStream = new FileInputStream(filess);
		Path pdfPath = Paths.get(outputFile);
		byte[] pdf = Files.readAllBytes(pdfPath);
		/*
		 * 
		 * ResponseEntity<String> response = new ResponseEntity<String>( new
		 * String(Base64.getDecoder().decode(fileStream.toString())), headers,
		 * HttpStatus.OK);
		 */
		return pdf;

	}

	/**
	 * @param document
	 * @param writer
	 * @param paraSortList
	 * @param objOutput
	 * @return
	 * @throws DocumentException
	 */
	private Document addMetaData(Document document, PdfWriter writer, List<Integer> paraSortList,
			ListofResponseObject objOutput) throws DocumentException {
		Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
		Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);
		Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
		Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
		for (int i = 0; i < paraSortList.size(); i++) {
			for (int j = 0; j < objOutput.getResponses().get(0).getParaType().size(); j++) {
				if (paraSortList.get(i).equals(objOutput.getResponses().get(0).getParaType().get(j).getParaSeq())) {
					String content = objOutput.getResponses().get(0).getParaType().get(j).getContent();
					if (content.contains("~"))
						content = content.replace("~", "&");
					if (content.contains("\\,"))
						content = content.replace("\\,", ",");
					// paragraph2.setAlignment(Element.ALIGN_LEFT);
					Font f = new Font(FontFamily.TIMES_ROMAN, 14.0f, Font.NORMAL, BaseColor.BLACK);
					Paragraph paragraph = new Paragraph();
					paragraph.add(content);
					paragraph.setAlignment(Element.PARAGRAPH);
					paragraph.setFont(f);
					paragraph = addEmptyLine(paragraph, 1);
					document.add(paragraph);
					break;
				}
			}

		}

		// testDataPDF(document, writer);

		return document;
	}

	@RequestMapping(value = "previewList", method = RequestMethod.POST)
	@CrossOrigin(origins = { "http://localhost:6003" })
	public Preview preview(@RequestParam String fileName) throws IOException, JAXBException {
		List<FileTableType> fileTableTypes = encommFileTableTypeService.getAll();
		List<String> versionList = new ArrayList<String>();
		Preview previW = new Preview();
		for (int i = 0; i < fileTableTypes.size(); i++) {
			System.out.println(fileName + " -> fileName" + "  " + fileTableTypes.size() + "  "
					+ fileTableTypes.get(i).getFormTitle());
			if (fileName.equals(fileTableTypes.get(i).getFormTitle())) {
				/*
				 * if (new Float(fileTableTypes.get(i).getVersionNo()).compareTo(new Float(0))
				 * == 0) { continue; }
				 */
				versionList.add("" + fileTableTypes.get(i).getVersionNo());
			}
		}
		previW.setBaseFileVersion(versionList.get(0));
		previW.setVersions(versionList);
		return previW;
	}

	@RequestMapping(value = "fileNameInfo", method = RequestMethod.GET)
	@CrossOrigin(origins = { "http://localhost:6003", "http://localhost:8086" })
	public FileTitleInfo fileNameInfo() throws Exception {
		Set<String> fileS = new HashSet<String>();
		fileS.add("--select the fileName--");
		List<FileTableType> fileTableTypes = encommFileTableTypeService.getAll();
		String fileName = "";
		for (int i = 0; i < fileTableTypes.size(); i++) {
			FileTableType file = fileTableTypes.get(i);
			fileS.add(file.getFormTitle());
		}
		FileTitleInfo fileTitleInfo = new FileTitleInfo();
		fileTitleInfo.setFileNameList(fileS);
		return fileTitleInfo;

	}

	@RequestMapping(value = "getFileId/{fileName}/{version}", method = RequestMethod.GET)
	@CrossOrigin(origins = { "http://localhost:6003" })
	public String getFileId(@PathVariable String fileName, @PathVariable String version) throws Exception {

		List<FileTableType> fileTableTypes = encommFileTableTypeService.getAll();
		int fileId = 0;
		CompareList compList = new CompareList();
		for (int i = 0; i < fileTableTypes.size(); i++) {
			if (fileName.equals(fileTableTypes.get(i).getFormTitle())) {
				if (new Float(fileTableTypes.get(i).getVersionNo()).compareTo(Float.valueOf(version)) == 0) {
					fileId = fileTableTypes.get(i).getFileId();
					break;
				}

			}
		}

		if (fileId == 0) {
			return null;
		}
		return new String("" + fileId);
	}

	@RequestMapping(value = "sendReport", method = RequestMethod.POST)
	@CrossOrigin(origins = { "http://localhost:6003" })
	public ResponseEntity<?> sendReport(UriComponentsBuilder ucBuilder) throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/encomm/get/{id}").buildAndExpand("1234").toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "reportList", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	@CrossOrigin(origins = { "http://localhost:6003" })
	public static List<String> listFilesr(@RequestParam String fileName) {
		String path = "C:\\Users\\Public\\POC Files\\BaseDir\\Report\\";
		File dir = new File(fileName);
		File[] files = dir.listFiles();
		List<String> fileNames = new ArrayList<String>();
		Arrays.sort(files, new Comparator<File>() {
			public int compare(File f1, File f2) {
				return Long.valueOf(f2.lastModified()).compareTo(f1.lastModified());
			}
		});
		for (int index = 0; index < files.length; index++) {
			// Print out the name of files in the directory
			if (files[index].getName().startsWith("~$")) {
				continue;
			}
			if (files[index].isDirectory()) {
				continue;
			}
			fileNames.add(path + files[index].getName());
			System.out.println(files[index].getName());
		}
		System.out.println(fileNames.get(0));
		return fileNames;
	}

	public static String postClient(String url, String o, String type) {
		Client restClient = Client.create();
		WebResource webResource = restClient.resource(url);
		ClientResponse resp = webResource.type(type).post(ClientResponse.class, o);
		String output = resp.getEntity(String.class);
		return trimXML(output);
	}

	public static String getClient(String url) {
		Client restClient = Client.create();
		WebResource webResource = restClient.resource(url);
		ClientResponse resp = webResource.get(ClientResponse.class);
		String output = resp.getEntity(String.class);
		return trimXML(output);
	}

	public static String getClient(String url, String type) {
		Client restClient = Client.create();
		WebResource webResource = restClient.resource(url);
		ClientResponse resp = webResource.accept(type).get(ClientResponse.class);
		String output = resp.getEntity(String.class);
		// return output;
		return trimXML(output);
	}

	static String generateString() {
		String uuid = UUID.randomUUID().toString();
		return uuid;
	}

	private static Paragraph addEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++)
			paragraph.add(new Paragraph(" "));
		return paragraph;
	}

	static String trimXML(String output) {
		if (output == null)
			return null;
		if (output.contains("<response><response>")) {
			output = output.replace("<response><response>", "<response>");
		}
		if (output.contains("</response></response>")) {
			output = output.replace("</response></response>", "</response>");
		}
		if (output.contains("<responses><responses>")) {
			output = output.replace("<responses><responses>", "<responses>");
		}
		if (output.contains("</responses></responses>")) {
			output = output.replace("</responses></responses>", "</responses>");
		}
		if (output.contains("<ListofResponseObject><ListofResponseObject>")) {
			output = output.replace("<ListofResponseObject><ListofResponseObject>", "<ListofResponseObject>");
		}

		if (output.contains("</ListofResponseObject></ListofResponseObject>")) {
			output = output.replace("</ListofResponseObject></ListofResponseObject>", "</ListofResponseObject>");
		}
		if (output.contains("</paraType></paraType>")) {
			output = output.replace("</paraType></paraType>", "</paraType>");
		}
		if (output.contains("<paraType><paraType>")) {
			output = output.replace("<paraType><paraType>", "<paraType>");
		}
		return output;
	}

	public static String convertObjectToXML(Object object) throws Exception {
		StringWriter stringWriter = new StringWriter();
		JAXBContext jc = JAXBContext.newInstance(
				new Class[] { FileTableType.class, ParaType.class, ResponseObject.class, ListofResponseObject.class });
		Marshaller marshaller = jc.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		marshaller.marshal(object, stringWriter);
		System.out.println(stringWriter.toString());
		return stringWriter.toString();
	}

	public static String marshal(Object object) throws JAXBException {
		StringWriter stringWriter = new StringWriter();
		JAXBContext jc = JAXBContext.newInstance(object.getClass());
		Marshaller m = jc.createMarshaller();
		m.marshal(object, stringWriter);
		System.out.println(stringWriter.toString());
		return stringWriter.toString();
	}

	public static <E> ArrayList<E> toList(Iterable<E> iterable) {
		if (iterable instanceof List) {
			return (ArrayList<E>) iterable;
		}
		ArrayList<E> list = new ArrayList<E>();
		if (iterable != null) {
			for (E e : iterable) {
				list.add(e);
			}
		}
		return list;
	}

	public static String marshalToJson(Object o) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String jsonVal = "";
		jsonVal = mapper.writeValueAsString(o);
		return jsonVal;
	}

	/*
	 * public static void listFilesForFolder(final File folder) { for (final File
	 * fileEntry : folder.listFiles()) { if (fileEntry.isDirectory())
	 * listFilesForFolder(fileEntry); else if (fileEntry.isFile())
	 * filesInDir.add(folder.getAbsolutePath() + "\\" + fileEntry.getName()); } }
	 */

}