package com.cgi.ewi.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgi.ewi.dao.Chart;
import com.cgi.ewi.dao.ChartRequest;
import com.cgi.ewi.dao.Department;
import com.cgi.ewi.dao.EWIDetails;
import com.cgi.ewi.dao.EwiManagerSummary;
import com.cgi.ewi.dao.InnerChart;
import com.cgi.ewi.operationsmgmt.entity.EwiAccountSummary;
import com.cgi.ewi.repository.DepartmentRepository;
import com.cgi.ewi.repository.EWIDetailsRepository;
import com.cgi.ewi.repository.EwiAccountSummaryRepository;
import com.cgi.ewi.repository.EwiManagerSummaryRepository;
import com.cgi.ewi.service.DashBoardService;

@Service
public class DashBoardServiceImpl implements DashBoardService {

	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private EwiManagerSummaryRepository ewiManagerSummaryRepository;

	@Autowired
	private EWIDetailsRepository ewiDetailsRepository;

	@Autowired
	EwiAccountSummaryRepository ewiAccountSummaryRepository;

	private static List<Department> ALL_DEPTS = new ArrayList<>();

	private void setAllDepts(List<Department> allDepts) {
		ALL_DEPTS = allDepts;
	}
	
	private static final String[]  COLORS = new String[] {"#258e25","#cc0000","#cccc00"};

	SimpleDateFormat format = new SimpleDateFormat("MMM-yy");
	SimpleDateFormat format1 = new SimpleDateFormat("yyyyMM");

	@Override
	public Set<String> getAllBULeads() {

		Set<String> uniqueBuLeads = new HashSet<>();
		setAllDepts((List<Department>) departmentRepository.findAll());

		for (Department dept : ALL_DEPTS) {
			uniqueBuLeads.add(dept.getBuLead());
		}

		return uniqueBuLeads;
	}

	@Override
	public Set<String> getGroupLeadsByBULead(String buLead) {

		Set<String> uniqueGroupLeads = new HashSet<>();

		for (Department dept : ALL_DEPTS) {

			if (dept.getBuLead().equals(buLead)) {
				uniqueGroupLeads.add(dept.getGroupLead());
			}
		}
		return uniqueGroupLeads;
	}

	@Override
	public Chart getAllDeptData(ChartRequest chartRequest) {

		LinkedList<String> uniquePyramidAccs = new LinkedList<>(departmentRepository.findPyramidAccount(chartRequest.getBuLead(), chartRequest.getGroupLead()));
		List<String> dateInverval = chartRequest.getDateInterval().stream().map(e -> String.valueOf(e)).collect(Collectors.toList());
		LinkedList<InnerChart> innerChartkst = new LinkedList<>();
		
		for (int i = 0; i < dateInverval.size(); i++) {

			TreeMap<String, TreeMap<String, Integer[]>> CategoryMap = new TreeMap<>();
			LinkedList<EwiAccountSummary> findByMonth = ewiAccountSummaryRepository.findByMonth(dateInverval.get(i));
			for (EwiAccountSummary ewiAccountSummary : findByMonth) {

				if (CategoryMap.containsKey(ewiAccountSummary.getPyramidAccount())) {
					TreeMap<String, Integer[]> hashMap = CategoryMap.get(ewiAccountSummary.getPyramidAccount());

					if (hashMap.containsKey(ewiAccountSummary.getEWICategory())) {
						Integer[] integers = hashMap.get(ewiAccountSummary.getEWICategory());
						integers[uniquePyramidAccs.indexOf(ewiAccountSummary.getPyramidAccount())] = ewiAccountSummary
								.getCategoryCount();
						hashMap.put(ewiAccountSummary.getEWICategory(), integers);

					} else {
						Integer[] dataArray = new Integer[uniquePyramidAccs.size()];
						Arrays.fill(dataArray, 0);
						dataArray[uniquePyramidAccs.indexOf(ewiAccountSummary.getPyramidAccount())] = ewiAccountSummary
								.getCategoryCount();
						hashMap.put(ewiAccountSummary.getEWICategory(), dataArray);
						CategoryMap.put(ewiAccountSummary.getPyramidAccount(), hashMap);

					}

				} else {
					Integer[] dataArray = new Integer[uniquePyramidAccs.size()];
					Arrays.fill(dataArray, 0);
					TreeMap<String, Integer[]> direcPyramid = new TreeMap<>();
					dataArray[uniquePyramidAccs.indexOf(ewiAccountSummary.getPyramidAccount())] = ewiAccountSummary
							.getCategoryCount();
					direcPyramid.put(ewiAccountSummary.getEWICategory(), dataArray);
					CategoryMap.put(ewiAccountSummary.getPyramidAccount(), direcPyramid);
				}
			}
                 int colorCount =  0;
			Iterator<Entry<String, TreeMap<String, Integer[]>>> iterator = CategoryMap.entrySet().iterator();
			if (iterator.hasNext()) {
				Iterator<Entry<String, Integer[]>> iterator2 = iterator.next().getValue().entrySet().iterator();
				while (iterator2.hasNext()) {
					Entry<String, Integer[]> next = iterator2.next();
					try {
						innerChartkst.add(new InnerChart(next.getKey(),
								format.format(format1.parse(dateInverval.get(i))), next.getValue(),"column",COLORS[colorCount]));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					
					colorCount++;
				}
			}
		}

		return new Chart(uniquePyramidAccs.toArray(new String[uniquePyramidAccs.size()]),
				innerChartkst.toArray(new InnerChart[innerChartkst.size()]));

	}

	@Override
	public Chart getDrillInnerData(ChartRequest chartRequest, String pyramidAccount) {

		List<String> dateInverval = chartRequest.getDateInterval().stream().map(e -> String.valueOf(e)).collect(Collectors.toList());
		LinkedList<String> findDistinctDirectors = new LinkedList<>(departmentRepository.findDistinctDirectors(chartRequest.getBuLead(), chartRequest.getGroupLead(), pyramidAccount));
		LinkedList<String> findDistinctProjectManagers = departmentRepository.findDistinctProjectManagers(findDistinctDirectors);
		LinkedList<InnerChart> innerChartkst = new LinkedList<>();

		for (int i = 0; i < dateInverval.size(); i++) {
			TreeMap<String, Integer[]> CategoryMap = new TreeMap<>();
			List<EwiManagerSummary> findByMonth = ewiManagerSummaryRepository
					.findByMonthAndRMNameIn(dateInverval.get(i), findDistinctProjectManagers);

			for (EwiManagerSummary ewiManagerSummary : findByMonth) {
				LinkedList<String> findByProjectMangerLst = departmentRepository
						.findUniqDirectorsByRm(ewiManagerSummary.getrMName());

				if (CategoryMap.containsKey(ewiManagerSummary.geteWICategory())) {
					Integer[] integers = CategoryMap.get(ewiManagerSummary.geteWICategory());
					for (String findByProjectManger : findByProjectMangerLst) {
						integers[findDistinctDirectors.indexOf(
								findByProjectManger)] = integers[findDistinctDirectors.indexOf(findByProjectManger)]
										+ Integer.parseInt(ewiManagerSummary.getCategoryCount());
					}
					CategoryMap.put(ewiManagerSummary.geteWICategory(), integers);

				} else {

					Integer[] dataArray = new Integer[findDistinctDirectors.size()];
					Arrays.fill(dataArray, 0);

					for (String findByProjectManger : findByProjectMangerLst) {
						dataArray[findDistinctDirectors.indexOf(findByProjectManger)] = Integer
								.parseInt(ewiManagerSummary.getCategoryCount());
					}
					CategoryMap.put(ewiManagerSummary.geteWICategory(), dataArray);
				}
			}
			Iterator<Entry<String, Integer[]>> iterator = CategoryMap.entrySet().iterator();
             int colorCount = 0 ;
			while (iterator.hasNext()) {
				Entry<String, Integer[]> next = iterator.next();

				try {
					innerChartkst.add(new InnerChart(next.getKey(), format.format(format1.parse(dateInverval.get(i))),
							next.getValue(),"column",COLORS[colorCount]));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				 colorCount ++;
			}
		}

		return new Chart(findDistinctDirectors.toArray(new String[findDistinctDirectors.size()]),
				innerChartkst.toArray(new InnerChart[innerChartkst.size()]));

	}

	@Override
	public Chart getDrillMemberInnerData(ChartRequest chartRequest, String director) {

		List<String> dateInverval = chartRequest.getDateInterval().stream().map(e -> String.valueOf(e)).collect(Collectors.toList());
		LinkedList<String> uniqueManagersLst = departmentRepository.findDistinctProjectManager(director);
		for (String options : uniqueManagersLst) {
			if (null == options) {
				uniqueManagersLst.remove(options);
			}
		}
		LinkedList<InnerChart> innerChartkst = new LinkedList<>();

		for (int j = 0; j < dateInverval.size(); j++) {

			List<EWIDetails> findByRmName = ewiDetailsRepository.findByRmNameInAndMonth(uniqueManagersLst,
					dateInverval.get(j));
			TreeMap<String, Integer[]> ManagersMap = new TreeMap<>();

			for (EWIDetails eWIDetails : findByRmName) {

				if (ManagersMap.containsKey(eWIDetails.getEwiCategory())) {
					Integer[] integers = ManagersMap.get(eWIDetails.getEwiCategory());
					integers[uniqueManagersLst.indexOf(
							eWIDetails.getRmName())] = integers[uniqueManagersLst.indexOf(eWIDetails.getRmName())] + 1;
					ManagersMap.put(eWIDetails.getEwiCategory(), integers);

				} else {

					Integer[] innerArray = new Integer[uniqueManagersLst.size()];
					Arrays.fill(innerArray, 0);
					innerArray[uniqueManagersLst.indexOf(eWIDetails.getRmName())] = 1;
					ManagersMap.put(eWIDetails.getEwiCategory(), innerArray);
				}
			}

			Iterator<Entry<String, Integer[]>> iterator = ManagersMap.entrySet().iterator();
			int colorCount = 0;
			while (iterator.hasNext()) {
				Entry<String, Integer[]> next = iterator.next();
				try {
					innerChartkst.add(new InnerChart(next.getKey(), format.format(format1.parse(dateInverval.get(j))),
							next.getValue(),"column",COLORS[colorCount]));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				colorCount ++;
			}
		}
		List<InnerChart> values = new ArrayList<InnerChart>();
		for (InnerChart data : innerChartkst) {
			if (data != null) {
				values.add(data);
			}
		}
		return new Chart(uniqueManagersLst.toArray(new String[uniqueManagersLst.size()]),
				values.toArray(new InnerChart[values.size()]));
	}

	@Override 
	public Chart getDrilllineInnerData(ChartRequest chartRequest, String eventname) {
		
		List<String> findDistictMonth = chartRequest.getDateInterval().stream().map(e -> String.valueOf(e)).collect(Collectors.toList());
		LinkedList<String> dateInverval = ewiManagerSummaryRepository.findDistictMonth(findDistictMonth);
		//LinkedList<String> findDistictCategory = ewiManagerSummaryRepository.findDistictCategory(findDistictMonth);
		
		LinkedList<InnerChart> innerChartkst = new LinkedList<>();
		TreeMap<String, Integer[]> managersMap = new TreeMap<>();

        for(int  i = 0 ; i<dateInverval.size(); i++) {
			
			LinkedList<EwiManagerSummary> findByRMNameAndMonth = ewiManagerSummaryRepository.findByRMNameAndMonth(eventname,dateInverval.get(i));
			
			for (EwiManagerSummary ewiManagerSummary : findByRMNameAndMonth) {

				if (managersMap.containsKey(ewiManagerSummary.geteWICategory())) {

					Integer[] integers = managersMap.get(ewiManagerSummary.geteWICategory());
					integers[dateInverval.indexOf(ewiManagerSummary.getMonth())] =  Integer.parseInt(ewiManagerSummary.getCategoryCount());
					managersMap.put(ewiManagerSummary.geteWICategory(), integers);

				} else {

					Integer[] dataArray = new Integer[dateInverval.size()];
					Arrays.fill(dataArray, 0);
					dataArray[dateInverval.indexOf(ewiManagerSummary.getMonth())] = Integer.parseInt(ewiManagerSummary.getCategoryCount());
					managersMap.put(ewiManagerSummary.geteWICategory(), dataArray);

				}
			}
		}
			Iterator<Entry<String, Integer[]>> iterator = managersMap.entrySet().iterator();
            int colorCount = 0 ;
            
		
			while (iterator.hasNext()) {
				Entry<String, Integer[]> next = iterator.next();
				
					Integer[] value = next.getValue();
					if (0 != Arrays.stream(Arrays.stream(value).mapToInt(Integer::intValue).toArray()).sum()) {

						innerChartkst.add(new InnerChart(next.getKey(), "",next.getValue(), "line", COLORS[colorCount]));
                    }
				
				colorCount++;
			}
			List<String> formatedDate = new ArrayList<>();
		for(int i = 0 ;i<dateInverval.size();i++) {
			
			try {
				formatedDate.add(format.format(format1.parse(dateInverval.get(i))));
			} catch (ParseException e1) {
				
			}
		}
		 return new Chart(formatedDate.toArray(new String[formatedDate.size()]),innerChartkst.toArray(new InnerChart[innerChartkst.size()]));
	
	}
}
