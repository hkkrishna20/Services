package com.cgi.ewi.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cgi.ewi.dao.Chart;
import com.cgi.ewi.dao.ChartRequest;
import com.cgi.ewi.service.DashBoardService;
@CrossOrigin(origins = "*")
@RestController
public class DashBoardController {

	@Autowired
	private DashBoardService dashBoardService;

	@GetMapping(value = "/getAllBULeads")
	public Set<String> getAllBULeads() {

		return dashBoardService.getAllBULeads();
	}

	@PostMapping(value = "/getGroupLeadsByBULead")
	public Set<String> getGroupLeadsByBULead(@RequestBody String buLead) {
		return dashBoardService.getGroupLeadsByBULead(buLead);
	}

	@PostMapping(value = "/getAllDeptData", consumes = "application/json")
	public Chart getAllDeptData(@RequestBody ChartRequest chartRequest) {
		return dashBoardService.getAllDeptData(chartRequest);
	}

	@PostMapping(value = "/getDrillInnerData")
	public Chart getDrillInnerData(@RequestParam("eventname") String eventname,
			@RequestBody ChartRequest chartRequest) {
		return dashBoardService.getDrillInnerData(chartRequest, eventname);
	}

	@PostMapping(value = "/getDrillMemberInnerData")
	public Chart getDrillMemberInnerData(@RequestParam("eventname") String eventname,
			@RequestBody ChartRequest chartRequest) {
		return dashBoardService.getDrillMemberInnerData(chartRequest, eventname);
	}

	@PostMapping(value = "/getDrilllineInnerData")
	public Chart getDrilllineInnerData(@RequestParam("eventname") String eventname,
			@RequestBody ChartRequest chartRequest) {
		return dashBoardService.getDrilllineInnerData(chartRequest, eventname);
	}
}
