package com.cgi.ewi.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.cgi.ewi.dao.Chart;
import com.cgi.ewi.dao.ChartRequest;

@Service
public interface DashBoardService {

	Set<String> getAllBULeads();

	Set<String> getGroupLeadsByBULead(String buLead);

	Chart getAllDeptData(ChartRequest chartRequest);

	Chart getDrillInnerData(ChartRequest chartRequest, String pyramidAccount);

	Chart getDrillMemberInnerData(ChartRequest chartRequest, String manager);

	Chart getDrilllineInnerData(ChartRequest chartRequest, String eventname);

}
