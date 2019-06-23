package com.integration.wc.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBException;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.integration.wc.client.IntegrationClient;
import com.integration.wc.config.ClientAppConfig;
import com.integration.wc.models.ContentTsType;
import com.integration.wc.models.LogicTableBRE;
import com.integration.wc.models.RegisterBean;
import com.integration.wc.models.ResponseTable;
import com.integration.wc.models.UserBean;
import com.integration.wc.schema.AppDetailsType;
import com.integration.wc.schema.BodyType;
import com.integration.wc.schema.CoappDetailsType;
import com.integration.wc.schema.DocFetchRequest;
import com.integration.wc.schema.DocFetchResponse;
import com.integration.wc.schema.FormDetailsType;
import com.integration.wc.schema.HeaderType;
import com.integration.wc.schema.IntegrationRequest;
import com.integration.wc.schema.IntegrationResponse;
import com.integration.wc.schema.MergeDetailsType;
import com.integration.wc.schema.PackageType;
import com.integration.wc.schema.SourceType;
import com.integration.wc.schema.SplitDetailsType;
import com.integration.wc.service.FormLogicService;
import com.integration.wc.service.LoginService;
import com.integration.wc.service.RegisterService;
import com.integration.wc.service.WCContentService;

@ComponentScan(basePackages = "com")
@Controller
public class WCController {
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(WCController.class);
	@Autowired
	LoginService loginService;
	@Autowired
	RegisterService registerService;
	@Autowired
	FormLogicService formLogicService;
	@Autowired
	WCContentService contentService;
	

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String ind() {
		return "integrationLandingPage";
	}

	@RequestMapping(value = "/doLogin", method = RequestMethod.GET)
	public String toLogin(Model model) {
		model.addAttribute("userBean", new UserBean());
		return "integrationLogin";
	}

	private boolean validate(String userName, String password) {
		UserBean userBean = new UserBean();
		userBean.setUserId(userName);
		userBean.setPassword(password);
		return loginService.authenticateUser(userBean);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String get(@PathVariable String id) {
		return id;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false); // Fetch session object
		if (session != null) // If session is not null
		{
			session.invalidate(); // removes all session attributes bound to the session
			request.setAttribute("errMessage", "You have logged out successfully");
		}
		return "integrationLandingPage";
	}

	@RequestMapping(value = "/registerProcess", method = RequestMethod.POST)
	public ModelAndView addUser(HttpServletRequest request, HttpServletResponse response, Model model) {
		String userName = request.getParameter("registerUserId");
		String password = request.getParameter("registerPassword");
		String name = request.getParameter("registerName");
		String email = request.getParameter("registerEmail");
		RegisterBean entity = new RegisterBean();
		UserBean userBean = new UserBean();
		userBean.setPassword(password);
		userBean.setUserId(userName);
		model.addAttribute("userBean", userBean);
		model.addAttribute("registerBean", entity);
		entity.setEmail(email);
		entity.setName(name);
		entity.setPassword(password);
		entity.setUserId(userName);
		if (validate(userName, password)) {
			return new ModelAndView("integrationLogin");
		} else {
			registerService.save(entity);
			loginService.save(userBean);
			return new ModelAndView("home");
		}
	}

	public DocFetchResponse responseDocFetch(DocFetchRequest increquest) throws JAXBException {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		DocFetchResponse response = null;
		try {
			ctx.register(ClientAppConfig.class);
			ctx.refresh();
			IntegrationClient client = ctx.getBean(IntegrationClient.class);
			DocFetchRequest request = new DocFetchRequest();
			request.setSource(increquest.getSource());
			response = client.docFetchClient(request);
		} finally {
			ctx.close();
		}
		return response;
	}

	public IntegrationResponse responseT(IntegrationRequest increquest) throws JAXBException {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		IntegrationResponse incresponse = null;
		try {
			ctx.register(ClientAppConfig.class);
			ctx.refresh();
			IntegrationClient client = ctx.getBean(IntegrationClient.class);
			incresponse = client.integrationClient(increquest);
		} finally {
			ctx.close();
		}
		return incresponse;
	}

	@RequestMapping(value = "/docFetchReq", method = RequestMethod.POST, produces = "application/pdf")
	public ResponseEntity<byte[]> getPDF(HttpServletRequest request, HttpServletResponse response)
			throws JAXBException {

		SourceType source = new SourceType();
		PackageType packageT = new PackageType();
		HeaderType header = new HeaderType();
		BodyType body = new BodyType();
		String testType = request.getParameter("testType");
		logger.info("This is info  testType: " + testType);
		String userId = request.getParameter("userId");
		logger.info("This is info  userId: " + userId);
		String password = request.getParameter("password");
		logger.info("This is info  password: " + password);
		String environment = request.getParameter("environment");
		logger.info("This is info  environment: " + environment);
		String channel = request.getParameter("channel");
		logger.info("This is info  channel: " + channel);
		String docID = request.getParameter("docId");
		header.setDocId(docID);
		header.setChannel(channel);
		header.setEnvironment(environment);
		header.setPassword(password);
		header.setTestType(testType);
		header.setUserId(userId);
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH_mm_ss");
		sdf.format(cal.getTime());
		if (testType.equals("DocFetchService")) {
			packageT.setHeader(header);
			packageT.setBody(body);
			source.setPackage(packageT);
			DocFetchRequest dc = new DocFetchRequest();
			dc.setSource(source);
			DocFetchResponse rc = responseDocFetch(dc);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/pdf"));
			String filename = rc.getSource().getPackage().getHeader().getRequestId() + "_" + sdf.format(cal.getTime())
					+ ".pdf";
			headers.setContentDispositionFormData(filename, filename);
			headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
			ResponseEntity<byte[]> responseNew = new ResponseEntity<byte[]>(
					Base64.decodeBase64(rc.getBase64EncodedString()), headers, HttpStatus.OK);
			return responseNew;
		}
		return null;

	}

	@RequestMapping(value = "/slRequest", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ModelAndView process(HttpServletRequest request, HttpServletResponse response)
			throws JAXBException, JsonProcessingException {
		IntegrationRequest increquest = new IntegrationRequest();
		IntegrationResponse inc = null;
		SourceType source = new SourceType();
		PackageType packageT = new PackageType();
		HeaderType header = new HeaderType();
		BodyType body = new BodyType();
		String testType = request.getParameter("testType");
		logger.info("This is info  testType: " + testType);
		String userId = request.getParameter("userId");
		logger.info("This is info  userId: " + userId);
		String password = request.getParameter("password");
		logger.info("This is info  password: " + password);
		String environment = request.getParameter("environment");
		logger.info("This is info  environment: " + environment);
		String channel = request.getParameter("channel");
		logger.info("This is info  channel: " + channel);

		String appName = request.getParameter("appName");
		String addressLine1 = request.getParameter("addressLine1");
		String addressLine2 = request.getParameter("addressLine2");
		String mobile = request.getParameter("mobile");
		String zipCode = request.getParameter("zipCode");
		String coAppName = request.getParameter("coAppName");
		String coAddressLine1 = request.getParameter("coAddressLine1");
		String coAddressLine2 = request.getParameter("coAddressLine2");
		String comobile = request.getParameter("comobile");
		String cozipCode = request.getParameter("cozipCode");
		String docID = request.getParameter("docId");
		String parentId = request.getParameter("parentId");
		String templateId = request.getParameter("templateId");
		String productFamily = request.getParameter("productFamily");
		String formTitle[] = request.getParameterValues("formTitleFormGen");
		String formID[] = request.getParameterValues("formIDFormGen");
		String formSeqFormGen[] = request.getParameterValues("formSeqFormGen");
		String mergeDocIDs[] = request.getParameterValues("mergeDocID");
		String startPageNo = request.getParameter("startPageNo");
		String endPageNo = request.getParameter("endPageNo");

		header.setChannel(channel);
		header.setEnvironment(environment);
		header.setPassword(password);
		header.setTestType(testType);
		header.setUserId(userId);

		if (testType.equals("FormDeterminationServie")) {
			header.setTemplateId(templateId);
			packageT.setHeader(header);
			packageT.setBody(body);
			source.setPackage(packageT);
			increquest.setSource(source);
			inc = responseT(increquest);
			logger.info(marshalToJson(inc));
		}
		if (testType.equals("FormCreationService")) {
			for (int i = 0; i < formID.length; i++) {
				FormDetailsType fc = new FormDetailsType();
				fc.setFormId(formID[i]);
				fc.setFormSeq(formSeqFormGen[i]);
				fc.setFormTitle(formTitle[i]);
				header.getFormDetails().set(i, fc);
			}
			packageT.setHeader(header);
			packageT.setBody(body);
			source.setPackage(packageT);
			increquest.setSource(source);
			inc = responseT(increquest);
			logger.info(marshalToJson(inc));
		}
		List<String> docIds = new ArrayList<String>();
		if (testType.equals("FormMergeService")) {
			for (int i = 0; i < mergeDocIDs.length; i++) {
				System.out.println(mergeDocIDs[i]);
				docIds.add(mergeDocIDs[i]);
			}
			System.out.println(docIds);
			MergeDetailsType mc = new MergeDetailsType();
			mc.getDocId().addAll(docIds);
			packageT.setHeader(header);
			packageT.setBody(body);
			source.setPackage(packageT);
			source.getPackage().getBody().setMergeDetails(mc);
			packageT.setHeader(header);
			source.setPackage(packageT);
			increquest.setSource(source);
			inc = responseT(increquest);
			logger.info(marshalToJson(inc));
		}
		if (testType.equals("RoutingService")) {
			header.setParentId(parentId);
			header.setProductFamily(productFamily);
			header.setTemplateId(templateId);
			packageT.setHeader(header);
			packageT.setBody(body);
			source.setPackage(packageT);
			increquest.setSource(source);
			inc = responseT(increquest);
			logger.info(marshalToJson(inc));
		}

		if (testType.equals("FormSplitterService")) {
			header.setDocId(docID);
			SplitDetailsType sc = new SplitDetailsType();
			sc.setDocId(docID);
			sc.setStartPageNo(Integer.parseInt(startPageNo));
			sc.setEndPageNo(Integer.parseInt(endPageNo));
			packageT.setHeader(header);
			body.setSplitDetails(sc);
			packageT.setBody(body);
			source.setPackage(packageT);
			increquest.setSource(source);
			inc = responseT(increquest);
			logger.info(marshalToJson(inc));
		}
		if (testType.equals("InterfaceService")) {
			AppDetailsType ac = new AppDetailsType();
			ac.setAddressLine1(addressLine1);
			ac.setAddressLine2(addressLine2);
			ac.setFullName(appName);
			ac.setZipCode(zipCode);
			ac.setPhoneNumber(mobile);
			CoappDetailsType co = new CoappDetailsType();
			co.setAddressLine1(coAddressLine1);
			co.setAddressLine2(coAddressLine2);
			co.setFullName(coAppName);
			co.setZipCode(cozipCode);
			co.setPhoneNumber(comobile);
			body.setAppDetails(ac);
			body.setCoappDetails(co);
			packageT.setHeader(header);
			packageT.setBody(body);
			source.setPackage(packageT);
			increquest.setSource(source);
			inc = responseT(increquest);
			logger.info(marshalToJson(inc));
		}
		if (testType.equals("SourceFormGenerationService")) {
			header.setTemplateId(templateId);
			header.setProductFamily(productFamily);
			header.setParentId(parentId);
			packageT.setHeader(header);
			packageT.setBody(body);
			source.setPackage(packageT);
			increquest.setSource(source);
			inc = responseT(increquest);
			logger.info(marshalToJson(inc));
		}
		ResponseTable rc = new ResponseTable();
		rc.setChannel(inc.getSource().getPackage().getHeader().getChannel());
		rc.setDocId(inc.getSource().getPackage().getHeader().getDocId());
		rc.setDocPathUrl(inc.getSource().getPackage().getHeader().getDocPathUrl());
		rc.setRequestId(inc.getSource().getPackage().getHeader().getRequestId());
		rc.setRequestTimeStamp(
				convertXmlGregorianToString(inc.getSource().getPackage().getHeader().getRequestTimeStamp()));
		rc.setTestType(inc.getSource().getPackage().getHeader().getTestType());
		return new ModelAndView("responseJson", "response", rc);
	}

	public static String convertXmlGregorianToString(XMLGregorianCalendar xc) {
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy hh:mm a z");
		GregorianCalendar gCalendar = xc.toGregorianCalendar();
		Date date = gCalendar.getTime();
		String dateString = df.format(date);
		return dateString;
	}

	@RequestMapping(value = "/doLogin", method = RequestMethod.POST)
	public ModelAndView doLogin(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view = new ModelAndView("integrationLogin");
		String userName = request.getParameter("loginUserId");
		String password = request.getParameter("loginPassword");
		if (validate(userName, password)) {
			view.setViewName("home");
		}
		return view;
	}

	@RequestMapping(value = "/formInsert", method = RequestMethod.POST)
	public ModelAndView doFormLogic(HttpServletRequest request, HttpServletResponse response) {
		LogicTableBRE bre = new LogicTableBRE();
		String parentId = request.getParameter("parentId");
		bre.setParentId(parentId);
		String templateId = request.getParameter("templateId");
		bre.setTemplateId(templateId);
		String productFamily = request.getParameter("productFamily");
		bre.setProductFamily(productFamily);
		String formTitle[] = request.getParameterValues("formTitle");
		String formID[] = request.getParameterValues("formID");
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < formTitle.length; i++) {
			sb.append(formTitle[i]);
			if (i < (formTitle.length - 2)) {
				sb.append(',');
			}
		}
		String formTitlelist = sb.toString();
		bre.setFormTitle(formTitlelist);
		sb = new StringBuilder();
		for (int i = 0; i < formID.length; i++) {
			sb.append(formID[i]);
			if (i < (formID.length - 2)) {
				sb.append(',');
			}
		}
		String formIDlist = sb.toString();
		bre.setFormID(formIDlist);
		formLogicService.add(bre);
		ModelAndView view = new ModelAndView("integrationLogin");
		view.setViewName("insertFormID");
		return view;
	}

	@RequestMapping(value = "/contentInsert", method = RequestMethod.POST)
	public ModelAndView doContentInsert(HttpServletRequest request, HttpServletResponse response) {
		String formTitle[] = request.getParameterValues("formTitle");
		String formID[] = request.getParameterValues("formID");
		List<String> formTitlelist = new ArrayList<String>(Arrays.asList(formTitle));
		List<String> formIDlist = new ArrayList<String>(Arrays.asList(formID));
		for (int i = 0; i < formTitlelist.size(); i++) {
			if (formTitlelist.get(i).trim().isEmpty())
				continue;
			else if (formIDlist.get(i).trim().isEmpty())
				continue;

			ContentTsType bre = new ContentTsType();
			bre.setContent(formTitlelist.get(i));
			bre.setFormID(formIDlist.get(i));
			contentService.add(bre);
		}
		ModelAndView view = new ModelAndView("integrationLogin");
		view.setViewName("integrationContentEnrich");
		return view;
	}

	@RequestMapping(value = "/integration", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<SourceType> integration(@RequestBody SourceType sourceRequest) throws JAXBException {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		try {
			ctx.register(ClientAppConfig.class);
			ctx.refresh();
			IntegrationClient client = ctx.getBean(IntegrationClient.class);
			IntegrationRequest request = new IntegrationRequest();
			request.setSource(sourceRequest);
			IntegrationResponse response = client.integrationClient(request);
			return ResponseEntity.accepted().body(response.getSource());
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			// TODO: handle exception
		} finally {
			ctx.close();
		}
	}

	@RequestMapping(value = "/docFetch", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<DocFetchResponse> docFetch(@RequestBody SourceType sourceRequest) throws JAXBException {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		try {
			ctx.register(ClientAppConfig.class);
			ctx.refresh();
			IntegrationClient client = ctx.getBean(IntegrationClient.class);
			DocFetchRequest request = new DocFetchRequest();
			new DocFetchRequest().setSource(sourceRequest);
			DocFetchResponse response = client.docFetchClient(request);
			return ResponseEntity.accepted().body(response);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			// TODO: handle exception
		} finally {
			ctx.close();
		}
	}

	@RequestMapping(value = "/docFetchDetails", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_OCTET_STREAM_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE })
	public HttpEntity<byte[]> docFetchDetails() throws JAXBException, IOException {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		try {
			ctx.register(ClientAppConfig.class);
			ctx.refresh();
			IntegrationClient client = ctx.getBean(IntegrationClient.class);
			DocFetchRequest request = new DocFetchRequest();
			PackageType pc = new PackageType();
			BodyType bc = new BodyType();
			bc.setLineOfBusiness("Lending");
			pc.setBody(bc);
			HeaderType hc = new HeaderType();
			hc.setUserId("test");
			hc.setPassword("test");
			hc.setTestType("InterfaceService");
			pc.setHeader(hc);
			SourceType sc = new SourceType();
			sc.setPackage(pc);
			request.setSource(sc);
			DocFetchResponse response = client.docFetchClient(request);
			String encodedString = response.getBase64EncodedString();
			byte[] decoded = Base64.decodeBase64(encodedString);

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(new MediaType("application", "pdf"));
			headers.setContentDispositionFormData("attachment", "x.pdf");
			headers.setContentLength(decoded.length);
			return new ResponseEntity<byte[]>(decoded, headers, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			// TODO: handle exception
		} finally {
			ctx.close();
		}
	}

	@RequestMapping(value = "/integrationDetails", method = RequestMethod.GET)
	public SourceType userdetails() throws JAXBException {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		try {
			ctx.register(ClientAppConfig.class);
			ctx.refresh();
			IntegrationClient client = ctx.getBean(IntegrationClient.class);
			IntegrationRequest request = new IntegrationRequest();
			PackageType pc = new PackageType();
			BodyType bc = new BodyType();
			bc.setLineOfBusiness("Lending");
			pc.setBody(bc);
			HeaderType hc = new HeaderType();
			hc.setUserId("test");
			hc.setPassword("test");
			hc.setTestType("InterfaceService");
			pc.setHeader(hc);
			SourceType sc = new SourceType();
			sc.setPackage(pc);
			request.setSource(sc);
			return client.integrationClient(request).getSource();
		} catch (Exception e) {
			return null;
			// TODO: handle exception
		} finally {
			ctx.close();
		}

	}

	@RequestMapping(value = "/integrationGet", method = RequestMethod.GET)
	public SourceType integrationClient(@RequestParam(value = "name", defaultValue = "World") String name)
			throws JAXBException {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		try {
			ctx.register(ClientAppConfig.class);
			ctx.refresh();
			IntegrationClient client = ctx.getBean(IntegrationClient.class);
			IntegrationRequest request = new IntegrationRequest();
			PackageType pc = new PackageType();
			BodyType bc = new BodyType();
			bc.setLineOfBusiness("Lending");
			pc.setBody(bc);
			HeaderType hc = new HeaderType();
			hc.setUserId("test");
			hc.setPassword("test");
			hc.setTestType("InterfaceService");
			pc.setHeader(hc);
			SourceType sc = new SourceType();
			sc.setPackage(pc);
			request.setSource(sc);
			return client.integrationClient(request).getSource();
		} catch (Exception e) {
			return null;
			// TODO: handle exception
		} finally {
			ctx.close();
		}

	}

	@RequestMapping(value = "/integrationP", method = RequestMethod.POST)
	public ResponseEntity<SourceType> integrationgetP(@RequestBody SourceType request) {
		return new ResponseEntity<SourceType>(request, HttpStatus.OK);
	}

	@RequestMapping(value = "/docFetchP", method = RequestMethod.POST)
	public ResponseEntity<DocFetchResponse> docFetchP(@RequestBody SourceType request) {
		return null;
	}

	public static String marshalToJson(Object o) throws JAXBException, JsonProcessingException {
		return new ObjectMapper().writeValueAsString(o);
	}

}