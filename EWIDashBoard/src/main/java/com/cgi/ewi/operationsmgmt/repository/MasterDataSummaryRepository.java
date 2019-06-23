package com.cgi.ewi.operationsmgmt.repository;



import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cgi.ewi.operationsmgmt.entity.MasterDataSummary;
import com.cgi.ewi.operationsmgmt.entity.MasterDataSummaryId;
@Repository
public interface MasterDataSummaryRepository extends CrudRepository<MasterDataSummary, MasterDataSummaryId> {

//	Set<String> getDirectorNames(String accountGroup);
//	Set<Object[]> getManagerSum(String accountGroup, String engagementDirector);
//	List<Object[]> retrieveIndividualManagerSum(String accountGroup, String projectManager);
//	Set<Candidate> findByPyramidaccounthrbu(String string);
//	
//	Optional<String> findTitleByIdM(@Param("id") Long id);
	Set<MasterDataSummary> findMasterDataSummaryIdDirectorByMasterDataSummaryIdFy(String id);
	@Query(value = "select LOB,Billable_Category,Month,FY,Category_Count from master_data_summary", nativeQuery = true)
	Set<Object[]> retrieveLOBDetails();
	
	@Query(value = "select Director,Billable_Category,Month,FY,Category_Count from master_data_summary", nativeQuery = true)
	Set<Object[]> retrieveDirectorDetails();
	
	@Query(value = "select Manager_Name,Billable_Category,Month,FY,Category_Count from master_data_summary", nativeQuery = true)
	List<Object[]> retrieveManagerDetails();
	
	//Set<MasterDataSummary> findByMasterDataSummaryId_FY(String financeYear);


	//List<MasterDataSummary> findByMasterDataSummaryId_FYAndMasterDataSummaryId_Director(String financeYear, String col2);
	//List<MasterDataSummary> findByMasterDataSummaryId_FYAndMasterDataSummaryId_ManagerName(String financeYear, String col2);
	//List<MasterDataSummary> findByMasterDataSummaryId_FYAndMasterDataSummaryId_LOB(String financeYear, String col2);
	
	List<MasterDataSummary> findByMasterDataSummaryIdFy(String fy);
	List<MasterDataSummary> findByMasterDataSummaryIdFyAndMasterDataSummaryIdDirector(String fy, String director);
	List<MasterDataSummary> findByMasterDataSummaryIdFyAndMasterDataSummaryIdManagername(String fy, String managername);
	List<MasterDataSummary> findByMasterDataSummaryIdFyAndMasterDataSummaryIdLob(String fy, String lob);
	List<MasterDataSummary> findByMasterDataSummaryId(MasterDataSummaryId employeeKey);
	
	
}
