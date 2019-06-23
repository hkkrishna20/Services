package com.cgi.ewi.operationsmgmt.repository;



import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.cgi.ewi.operationsmgmt.entity.DirectorBillableFy;

public interface DirectorBillableFyRepository extends CrudRepository<DirectorBillableFy, String> {

//	Set<String> getDirectorNames(String accountGroup);
//	Set<Object[]> getManagerSum(String accountGroup, String engagementDirector);
//	List<Object[]> retrieveIndividualManagerSum(String accountGroup, String projectManager);
//	Set<Candidate> findByPyramidaccounthrbu(String string);
//	
	//Set<DirectorBillableFy> findDirectorByFY(String id);

	Set<DirectorBillableFy> findDirectorByFY(String id);


}
