package com.cgi.ewi.operationsmgmt.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cgi.ewi.dao.SelectionItem;
import com.cgi.ewi.operationsmgmt.entity.MasterDataSummary;
import com.cgi.ewi.operationsmgmt.repository.DirectorBillableFyRepository;
import com.cgi.ewi.operationsmgmt.repository.MasterDataSummaryRepository;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/billablelob")
public class BillableLOBController {
	@Autowired
	DirectorBillableFyRepository reso;

	@Autowired
	MasterDataSummaryRepository master;

	@Autowired
	BillableLOBComponent datacomp;

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
		String FY= "FY" + id;
		List<MasterDataSummary> masters = master.findByMasterDataSummaryIdFy(FY);
		Set<String> months = new HashSet<String>();
		for (MasterDataSummary masterr : masters) {
			months.add(masterr.getMasterDataSummaryId().getMonth());
		}
		String[] arr = { "OCT", "NOV", "DEC", "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEPT" };
		for (String month : months) {
			String subs = month.substring(4);
			if (Integer.valueOf(subs) == 10) {
				arr[0] = month;
			} else if (Integer.valueOf(subs) == 11) {

				arr[1] = month;
			} else if (Integer.valueOf(subs) == 12) {

				arr[2] = month;
			} else {
				arr[Integer.valueOf(subs) + 2] = month;
			}
		}
		return new ResponseEntity<>(arr, HttpStatus.OK);
	}

	@RequestMapping(value = "/accounts/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getAccounts(@PathVariable("id") String id)
			throws ParseException, JsonParseException, JsonMappingException, IOException {
		List<SelectionItem> accounts = new ArrayList<>();
		Iterator<MasterDataSummary> masters = master.findAll().iterator();
		Set<String> testData = new HashSet<String>();
		if (!id.equals("All")) {
			while (masters.hasNext()) {
				if (id.equals("Directors")) {
					testData.add(masters.next().getMasterDataSummaryId().getDirector());
				} else if (id.equals("LOB")) {
					testData.add(masters.next().getMasterDataSummaryId().getLob());

				} else if (id.equals("Managers")) {
					testData.add(masters.next().getMasterDataSummaryId().getManagername());
				}
			}
			for (String ar : testData) {
				accounts.add(new SelectionItem(ar, ar));
			}

		}
		return new ResponseEntity<>(accounts, HttpStatus.OK);
	}

	@RequestMapping(value = "/getchart/{id}/{id2}/{id3}", method = RequestMethod.GET)
	public ResponseEntity<?> getcreate(@PathVariable("id") String name, @PathVariable("id2") String type,
			@PathVariable("id3") String year)
			throws ParseException, JsonParseException, JsonMappingException, IOException {
		String arrName[] = name.split(",");
		if (type.equals("All")) {
			List<MasterDataSummary> director = new ArrayList<MasterDataSummary>();
			director.addAll(datacomp.getMasterDataSummaryId_FY("FY" + year));
			return new ResponseEntity<>(datacomp.fillData(director), HttpStatus.OK);
		}
		if (type.equals("Directors")) {
			List<MasterDataSummary> director = new ArrayList<MasterDataSummary>();
			for (String arr : arrName) {
				director.addAll(datacomp.getMasterDataSummaryId_FYDirector("FY" + year, arr));

			}
			return new ResponseEntity<>(datacomp.fillData(director), HttpStatus.OK);
		} else if (type.equals("LOB")) {
			List<MasterDataSummary> lob = new ArrayList<MasterDataSummary>();
			for (String arr : arrName) {
				lob.addAll(datacomp.getMasterDataSummaryId_FYLOB("FY" + year, arr));
			}
			return new ResponseEntity<>(datacomp.fillData(lob), HttpStatus.OK);
		} else if (type.equals("Managers")) {
			List<MasterDataSummary> managers = new ArrayList<MasterDataSummary>();
			for (String arr : arrName) {
				managers.addAll(datacomp.getMasterDataSummaryId_FYManager("FY" + year, arr));
			}
			return new ResponseEntity<>(datacomp.fillData(managers), HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/getchart", method = RequestMethod.POST)
	public ResponseEntity<?> create(@Valid @RequestBody String FileTableType) {
		String test = "{" + "    xAxis: {"
				+ "        categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']"
				+ "    }," + "" + "    plotOptions: {" + "        series: {" + "            stacking: 'normal'"
				+ "        }" + "    }," + "" + "    series: [{"
				+ "        data: [29.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 216.4, 194.1, 95.6, 54.4],"
				+ "        name: 'Meteorological data'" + "    },{" + "      name: 'Meteorological ',"
				+ "        data: [29.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 216.4, 194.1, 95.6, 54.4]"
				+ "    }, {" + "      name: ' data'," + "      "
				+ "        data: [144.0, 176.0, 135.6, 148.5, 216.4, 194.1, 95.6, 54.4, 29.9, 71.5, 106.4, 129.2]"
				+ "    }]" + "}";

		return new ResponseEntity<String>(test, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getTest", method = RequestMethod.GET)
	public ResponseEntity<?> gewt() {
		return new ResponseEntity<>(master.findByMasterDataSummaryIdFy("FY2019" ), HttpStatus.OK);
	}


}
