
package com.cgi.ewi.repository;

import java.util.LinkedList;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.cgi.ewi.dao.EwiManagerSummary;

public interface EwiManagerSummaryRepository extends CrudRepository<EwiManagerSummary, Long> {

	List<EwiManagerSummary> findByRMNameIn(List<String> rMName);

	List<EwiManagerSummary> findByRMNameInAndMonth(List<String> rMName, String month);

	List<EwiManagerSummary> findByRMName(String rMName);

	List<EwiManagerSummary> findByMonth(String month);

	List<EwiManagerSummary> findByMonthAndRMNameIn(String month, List<String> rMName);

	@Query(value = "SELECT DISTINCT `RM Name` FROM ewi_details WHERE month = :month", nativeQuery = true)
	LinkedList<String> findProjectMangers(@Param("month") String month);

	@Query(value = "SELECT DISTINCT `RM Name` FROM ewi_manager_summary WHERE month in (:month)", nativeQuery = true)
	LinkedList<String> findProjectMangersIn(@Param("month") List<String> month);

	LinkedList<EwiManagerSummary> findByRMNameAndMonth(String eventname, String string);
	
	@Query(value = "SELECT DISTINCT `month` FROM ewi_manager_summary WHERE month in (:month)", nativeQuery = true)
	LinkedList<String> findDistictMonth(@Param("month") List<String> month);
	
	@Query(value = "SELECT DISTINCT EWI_Category FROM ewi_manager_summary WHERE month in (:month)", nativeQuery = true)
	LinkedList<String> findDistictCategory(@Param("month") List<String> month);

}
