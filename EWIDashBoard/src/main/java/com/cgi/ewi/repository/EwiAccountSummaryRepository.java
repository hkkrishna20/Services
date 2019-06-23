package com.cgi.ewi.repository;

import java.util.LinkedList;

import org.springframework.data.repository.CrudRepository;

import com.cgi.ewi.operationsmgmt.entity.EwiAccountSummary;

public interface EwiAccountSummaryRepository extends CrudRepository<EwiAccountSummary, Long> {

	LinkedList<EwiAccountSummary> findByMonth(String string);
}
