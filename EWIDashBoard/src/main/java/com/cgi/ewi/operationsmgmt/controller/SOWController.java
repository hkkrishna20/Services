package com.cgi.ewi.operationsmgmt.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cgi.ewi.dao.SelectionItem;
import com.cgi.ewi.dao.Series_;
import com.cgi.ewi.operationsmgmt.entity.ExpirySummaryFy;
import com.cgi.ewi.operationsmgmt.repository.SOWRepository;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/sowLOB")
public class SOWController {

	@Autowired
	SOWRepository sowRepository;

	@Autowired
	SOWControllerHelper sowControllerHelper;

	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public String get(@PathVariable("id") String id) {
		return "";
	}

	@RequestMapping(value = "/lob/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getlob(@PathVariable("id") String id)
			throws ParseException, JsonParseException, JsonMappingException, IOException {
		List<SelectionItem> nlobs = new ArrayList<>();
		return new ResponseEntity<>(nlobs, HttpStatus.OK);
	}

	@RequestMapping(value = "/year/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getXaxisYear(@PathVariable("id") String id)
			throws ParseException, JsonParseException, JsonMappingException, IOException {
		List<ExpirySummaryFy> masters = sowRepository.findAllByExpirySummaryId_FY("FY" + id);
		Set<String> months = new HashSet<String>();

		for (ExpirySummaryFy masterr : masters) {
			String[] dateArray = masterr.getExpirySummaryId().getBillingEnd().split("-");
			String month = "";
			switch (dateArray[0]) {
			case "January":
				month = "01";
				break;
			case "February":
				month = "02";
				break;
			case "March":
				month = "03";
				break;
			case "April":
				month = "04";
				break;
			case "May":
				month = "05";
				break;
			case "June":
				month = "06";
				break;
			case "July":
				month = "07";
				break;
			case "August":
				month = "08";
				break;
			case "September":
				month = "09";
				break;
			case "October":
				month = "10";
				break;
			case "November":
				month = "11";
				break;
			case "December":
				month = "12";
				break;
			}
			months.add(month);
		}
		String[] arr = { "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEPT", "OCT", "NOV", "DEC" };
		for (String month : months) {
			arr[Integer.valueOf(month) - 1] = month;
		}
		return new ResponseEntity<>(arr, HttpStatus.OK);
	}

	@RequestMapping(value = "/accounts/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getAccounts(@PathVariable("id") String id)
			throws ParseException, JsonParseException, JsonMappingException, IOException {
		List<SelectionItem> accounts = new ArrayList<>();
		Iterator<ExpirySummaryFy> masters = sowRepository.findAll().iterator();
		Set<String> testData = new HashSet<String>();
		while (masters.hasNext()) {
			if (id.equals("Directors")) {
				testData.add(masters.next().getExpirySummaryId().getDirector());
			} else if (id.equals("LOB")) {
				testData.add(masters.next().getExpirySummaryId().getLOB());

			} else if (id.equals("Managers")) {
				testData.add(masters.next().getExpirySummaryId().getManagerName());
			}
		}
		for (String ar : testData) {
			accounts.add(new SelectionItem(ar, ar));
		}
		return new ResponseEntity<>(accounts, HttpStatus.OK);
	}

	@RequestMapping(value = "/getchart/{id}/{id2}/{id3}", method = RequestMethod.GET)
	public ResponseEntity<?> getcreate(@PathVariable("id") String name, @PathVariable("id2") String type,
			@PathVariable("id3") String year)
			throws ParseException, JsonParseException, JsonMappingException, IOException {
		if (type.equals("All")) {
			List<ExpirySummaryFy> director = sowControllerHelper.getExpirySummaryId_FY("FY" + year);
			return new ResponseEntity<List<Series_>>(sowControllerHelper.fillData(director), HttpStatus.OK);
		}
		else if (type.equals("Directors")) {
			List<ExpirySummaryFy> director = sowControllerHelper.getExpirySummaryId_FYDirector("FY" + year, name);
			return new ResponseEntity<List<Series_>>(sowControllerHelper.fillData(director), HttpStatus.OK);
		} else if (type.equals("LOB")) {
			List<ExpirySummaryFy> lob = sowControllerHelper.getExpirySummaryId_FYLOB("FY" + year, name);
			return new ResponseEntity<List<Series_>>(sowControllerHelper.fillData(lob), HttpStatus.OK);
		} else if (type.equals("Managers")) {
			List<ExpirySummaryFy> managers = sowControllerHelper.getExpirySummaryId_FYManager("FY" + year, name);
			return new ResponseEntity<List<Series_>>(sowControllerHelper.fillData(managers), HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

	}

}
