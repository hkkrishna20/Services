package com.cgi.ewi.repository;

import java.util.LinkedList;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.cgi.ewi.dao.EWIDetails;

public interface EWIDetailsRepository extends CrudRepository<EWIDetails, Long> {

	List<EWIDetails> findByRmName(String rmName);

	List<EWIDetails> findByMonth(String month);

	List<EWIDetails> findByRmNameInAndMonth(List<String> rmName, String month);

	List<EWIDetails> findBymemberNameInAndMonth(List<String> memberName, String month);

	List<EWIDetails> findByMonthIn(LinkedList<String> dateInverval);

	@Query(value = "SELECT DISTINCT `RM Name` FROM ewi_details WHERE month in (:month)", nativeQuery = true)
	LinkedList<String> findProjectMangersIn(@Param("month") List<String> month);

}
