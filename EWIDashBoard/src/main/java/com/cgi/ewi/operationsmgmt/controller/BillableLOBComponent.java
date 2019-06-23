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

import com.cgi.ewi.dao.MarkerClass;
import com.cgi.ewi.dao.Series_;
import com.cgi.ewi.operationsmgmt.entity.DirectorBillableFy;
import com.cgi.ewi.operationsmgmt.entity.MasterDataSummary;
import com.cgi.ewi.operationsmgmt.repository.DirectorBillableFyRepository;
import com.cgi.ewi.operationsmgmt.repository.MasterDataSummaryRepository;

@Component
public class BillableLOBComponent {
	@Autowired
	DirectorBillableFyRepository reso;

	@Autowired
	MasterDataSummaryRepository master;

	public Set<String> getDirNamesByFy(String id) {
	
		Set<DirectorBillableFy> dir = reso.findDirectorByFY(id);
		System.out.println();
		Set<String> st = new HashSet<String>();
		for (DirectorBillableFy dr : dir) {
			st.add(dr.getDirector());

		}
	
//		return null;
		return st;
	}

	public Set<String> getLOBNamesByFy(String id) {
		Set<MasterDataSummary> dir = master.findMasterDataSummaryIdDirectorByMasterDataSummaryIdFy(id);
		System.out.println();
		Set<String> st = new HashSet<String>();
		for (MasterDataSummary dr : dir) {
			st.add(dr.getMasterDataSummaryId().getLob());
		}
	
//		return null;
		return st;
	}

	public Set<String> getLOBNamesByFy() {
		List<MasterDataSummary> dir = (List<MasterDataSummary>) master.findAll();
		Set<String> st = new HashSet<String>();
		for (MasterDataSummary dr : dir) {
			st.add(dr.getMasterDataSummaryId().getFy());
		}
//		return null;
		return st;
	}

	static List<MasterDataSummary> display(List<MasterDataSummary> sets){
		System.out.println("------------------------");
		List<MasterDataSummary> uniques;
		uniques = removeDuplicates((ArrayList<MasterDataSummary>) sets);
		//disp(uniques);
		System.out.println("------------------------");
		return uniques;
	}
	
	private static void disp(List<MasterDataSummary> uniques) {
		int i =1;
		for(MasterDataSummary master : uniques) {
			System.out.println("i="+i+master);
			i++;
		}
	}

	public static <T> ArrayList<T> removeDuplicates(ArrayList<T> list) 
    { 
		/* List<T> newList = list.stream() 
                 .distinct() 
                 .collect(Collectors.toList());
		 System.out.println(newList);*/
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
	

	public List<MasterDataSummary> getMasterDataSummaryId_FY(String fy) {
		return 	display(master.findByMasterDataSummaryIdFy(fy));
	}

	public List<MasterDataSummary> getMasterDataSummaryId_FYLOB(String fy, String lob) {
		return display(master.findByMasterDataSummaryIdFyAndMasterDataSummaryIdLob(fy, lob));
	}

	public List<MasterDataSummary> getMasterDataSummaryId_FYDirector(String fy, String director) {
		return display(master.findByMasterDataSummaryIdFyAndMasterDataSummaryIdDirector(fy, director));
	}

	public List<MasterDataSummary> getMasterDataSummaryId_FYManager(String fy, String manager) {
		return display(master.findByMasterDataSummaryIdFyAndMasterDataSummaryIdManagername(fy, manager));
	}
	Set<Series_> fillData(List<MasterDataSummary> list) {
		Map<String,Double[]> mapofVal = new TreeMap<String,Double[]>();
		
		for(MasterDataSummary master:list) {
			String billableCategory = master.getMasterDataSummaryId().getBillcategory();
			if(null==mapofVal.get(billableCategory)) {
				Double[] items1 =  {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
				mapofVal.put(billableCategory, items1);
			}
			Double[] items1 = mapofVal.get(billableCategory);
				String subs= master.getMasterDataSummaryId().getMonth().substring(4);
				if(Integer.valueOf(subs) == 10 || Integer.valueOf(subs)==11 ||  Integer.valueOf(subs)==12)  {
					items1[Integer.valueOf(subs)%10] = Double.valueOf(master.getCategoryCount()) + items1[Integer.valueOf(subs)%10];
				}
				else {
					items1[Integer.valueOf(subs)+2] = Double.valueOf(master.getCategoryCount()) + items1[Integer.valueOf(subs)+2];
				}
			mapofVal.put(billableCategory, items1);
		}
		
	
		return populate(mapofVal);
	}
	
	public Set<Series_>  populate(Map<String, Double[]> mapofVal){
		LinkedHashSet<Series_> series = new LinkedHashSet<Series_>();
		if(mapofVal.size()>0) {
		for (Map.Entry<String, Double[]> entry : mapofVal.entrySet()) {
		    System.out.println("Key = " + entry.getKey());
		    if(!(entry.getKey().trim().contains("Billable")  || (entry.getKey().trim().contains("Non-Billabale"))))  {
			    Series_  seriaNB= new Series_();
			    MarkerClass marker = new MarkerClass();
			    marker.setSymbol("circle");
			    seriaNB.setMarker(marker);
			    seriaNB.setVisible(false);
			    if(entry.getKey().equalsIgnoreCase("Bench")) {
			    	 seriaNB.setColor("#FFBF00"); //amber
			    }
			    else   if(entry.getKey().equalsIgnoreCase("PEP")) {
			    	 seriaNB.setColor("	#0000CD");
			    }
			    else   if(entry.getKey().equalsIgnoreCase("PMO")) {
			    	 seriaNB.setColor("#00FA9A");
			    }
			    else if(entry.getKey().equalsIgnoreCase("Graduate")) {
			    	 seriaNB.setColor("yellow"); // amber
			    }
			    else if(entry.getKey().equalsIgnoreCase("ML")) {
			    	 seriaNB.setColor("#BDB76B");
			    }
			    else if(entry.getKey().equalsIgnoreCase("Abscond")) {
			    	 seriaNB.setColor("#000000");
			    } 
			    else if(entry.getKey().equalsIgnoreCase("Pool")) {
			    	 seriaNB.setColor("red");
			    } 
			    seriaNB.setType("line");
				seriaNB.setName(String.valueOf(entry.getKey()));
				seriaNB.setData(Arrays.asList(entry.getValue()));
				series.add(seriaNB);
		    }
		}
			Series_  seriaNB= new Series_();
			seriaNB.setName("Non-Billabale");
			seriaNB.setColor("red");
			seriaNB.setType("line");
			seriaNB.setVisible(true);
			seriaNB.setData(Arrays.asList(mapofVal.get("Non-Billabale")));
			series.add(seriaNB);
			if(null!=mapofVal.get("Non-Billable")) {
				  seriaNB= new Series_();
				seriaNB.setName("Non-Billable");
				seriaNB.setColor("red");
				seriaNB.setType("line");
				seriaNB.setVisible(true);
				seriaNB.setData(Arrays.asList(mapofVal.get("Non-Billable")));
				series.add(seriaNB);
			}
		  seriaNB= new Series_();
		  seriaNB.setName("Billable");
		  seriaNB.setColor("green");
		  seriaNB.setVisible(true);
		  seriaNB.setType("line");
		  seriaNB.setData(Arrays.asList(mapofVal.get("Billable")));
		  series.add(seriaNB);
		}
		if(series.size()==0) {
			Series_   seriaNB= new Series_();
			seriaNB.setName(String.valueOf(""));
			Double [] arr = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
			seriaNB.setData(Arrays.asList(arr));
			series.add(seriaNB);
		}
		return series;
	}
}
