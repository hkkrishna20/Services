package com.cgi.ewi.operationsmgmt.repository;



import java.util.List;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.cgi.ewi.operationsmgmt.entity.ExpirySummaryFy;
import com.cgi.ewi.operationsmgmt.entity.ExpirySummaryId;

public interface SOWRepository extends CrudRepository<ExpirySummaryFy, ExpirySummaryId> {
	
	Set<ExpirySummaryFy> findExpirySummaryId_DirectorByExpirySummaryId_FY(String id);
	List<ExpirySummaryFy> findAllByExpirySummaryId_FY(String FY);
	List<ExpirySummaryFy> findAllByExpirySummaryId_FYAndExpirySummaryIdDirector(String FY, String director);
	List<ExpirySummaryFy> findAllByExpirySummaryId_FYAndExpirySummaryIdManagerName(String FY, String managerName);
	List<ExpirySummaryFy> findAllByExpirySummaryId_FYAndExpirySummaryId_LOB(String FY, String LOB);
	List<ExpirySummaryFy> findByExpirySummaryId(ExpirySummaryId employeeKey);
	
	
}
