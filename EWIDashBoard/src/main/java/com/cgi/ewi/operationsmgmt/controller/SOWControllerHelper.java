package com.cgi.ewi.operationsmgmt.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cgi.ewi.dao.Series_;
import com.cgi.ewi.operationsmgmt.entity.ExpirySummaryFy;
import com.cgi.ewi.operationsmgmt.repository.SOWRepository;

@Component
public class SOWControllerHelper {

	@Autowired
	SOWRepository sowRepository;

	public Set<String> getLOBNamesByFy(String id) {
		System.out.println(id);
		Set<ExpirySummaryFy> dir = sowRepository.findExpirySummaryId_DirectorByExpirySummaryId_FY(id);
		System.out.println();
		Set<String> st = new HashSet<String>();
		for (ExpirySummaryFy dr : dir) {
			st.add(dr.getExpirySummaryId().getLOB());
		}
		System.out.println(st);
// return null;
		return st;
	}

	public Set<String> getLOBNamesByFy() {
		List<ExpirySummaryFy> dir = (List<ExpirySummaryFy>) sowRepository.findAll();
		Set<String> st = new HashSet<String>();
		for (ExpirySummaryFy dr : dir) {
			st.add(dr.getExpirySummaryId().getfY());
		}
		System.out.println(st);
// return null;
		return st;
	}

	static List<ExpirySummaryFy> display(List<ExpirySummaryFy> sets) {
		System.out.println("------------------------");
		List<ExpirySummaryFy> uniques;
		uniques = removeDuplicates((ArrayList<ExpirySummaryFy>) sets);
		disp(uniques);
		System.out.println("------------------------");
		return uniques;
	}

	private static void disp(List<ExpirySummaryFy> uniques) {
		int i = 1;
		for (ExpirySummaryFy master : uniques) {
			i++;
		}
	}

	public static <T> ArrayList<T> removeDuplicates(ArrayList<T> list) {
		/*
		 * List<T> newList = list.stream() .distinct() .collect(Collectors.toList());
		 * System.out.println(newList);
		 */
		// Create a new LinkedHashSet
		Set<T> set = new LinkedHashSet<>();
		// Add the elements to set
		set.addAll(list);
		// Clear the list
		list.clear();
		// add the elements of set
		// with no duplicates to the list
		list.addAll(set);
		// return the list
		return list;
	}

	public List<ExpirySummaryFy> getExpirySummaryId_FY(String finaYear) {
		return display(sowRepository.findAllByExpirySummaryId_FY(finaYear));
	}

	public List<ExpirySummaryFy> getExpirySummaryId_FYLOB(String finaYear, String lob) {
		return display(sowRepository.findAllByExpirySummaryId_FYAndExpirySummaryId_LOB(finaYear, lob));
	}

	public List<ExpirySummaryFy> getExpirySummaryId_FYDirector(String finaYear, String director) {
		return display(sowRepository.findAllByExpirySummaryId_FYAndExpirySummaryIdDirector(finaYear, director));
	}

	public List<ExpirySummaryFy> getExpirySummaryId_FYManager(String finaYear, String manager) {
		return display(sowRepository.findAllByExpirySummaryId_FYAndExpirySummaryIdManagerName(finaYear, manager));
	}

	List<Series_> fillData(List<ExpirySummaryFy> list) {
		Map<String, Double[]> mapofVal = new TreeMap<String, Double[]>();
		Double[] items1 = { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
		Double[] items2 = { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
		mapofVal.put("SOW_EXPIRY", items1);
		mapofVal.put("SOW_RENEWAL", items2);
		for (ExpirySummaryFy master : list) {

			String[] dateArray = master.getExpirySummaryId().getBillingEnd().split("-");
			String month = "";
			switch (dateArray[0]) {
			case "January":
				month = "04";
				break;
			case "February":
				month = "05";
				break;
			case "March":
				month = "06";
				break;
			case "April":
				month = "07";
				break;
			case "May":
				month = "08";
				break;
			case "June":
				month = "09";
				break;
			case "July":
				month = "10";
				break;
			case "August":
				month = "11";
				break;
			case "September":
				month = "12";
				break;
			case "October":
				month = "01";
				break;
			case "November":
				month = "02";
				break;
			case "December":
				month = "03";
				break;
			}
			Double[] items3 = mapofVal.get("SOW_EXPIRY");
			items3[Integer.valueOf(month) - 1] = master.getsOWExpires() + items3[Integer.valueOf(month) - 1];
			mapofVal.put("SOW_EXPIRY", items3);
			Double[] items4 = mapofVal.get("SOW_RENEWAL");
			items4[Integer.valueOf(month) - 1] = master.getsOWRenewals() + items4[Integer.valueOf(month) - 1];
			mapofVal.put("SOW_RENEWAL", items4);
		}
		return populate(mapofVal);
	}

	public List<Series_> populate(Map<String, Double[]> mapofVal) {
		List<Series_> series = new ArrayList<Series_>();
		for (Map.Entry<String, Double[]> entry : mapofVal.entrySet()) {
			Series_ seriaNB = new Series_();
			seriaNB.setVisible(true);
			seriaNB.setName(String.valueOf(entry.getKey()));
			seriaNB.setData(Arrays.asList(entry.getValue()));
			series.add(seriaNB);
		}
		if (series.size() == 0) {
			
			Series_ seriaNB = new Series_();
			seriaNB.setVisible(true);
			seriaNB.setName(String.valueOf(""));
			Double[] arr = { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
			seriaNB.setData(Arrays.asList(arr));
			series.add(seriaNB);
		}
		return series;

	}
}