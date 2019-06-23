
package com.cgi.ewi.repository;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.cgi.ewi.dao.Department;

public interface DepartmentRepository extends CrudRepository<Department, Long> {

	List<Department> findBybuLeadAndGroupLeadAndProjectMangerIn(String buLead, String groupLead,
			List<String> projectManger);

	List<Department> findBybuLeadAndGroupLead(String buLead, String groupLead);

	LinkedList<Department> findByPyramidAccount(String pyramidAccount);

	HashSet<Department> findByProjectManger(String projectManger);

	HashSet<Department> findByDirectorIn(List<String> pyramidAccount);

	LinkedList<Department> findByDirectorAndAndProjectMangerIn(String pyramidAccount, List<String> projectManger);

	LinkedList<Department> findByPyramidAccountAndProjectMangerIn(String pyramidAccount,
			LinkedList<String> ProjectManger);

	@Query(value = "SELECT DISTINCT `Project Manager` FROM departments WHERE Director in (:director)", nativeQuery = true)
	LinkedList<String> findDistinctProjectManagers(@Param("director") LinkedList<String> director);

	@Query(value = "SELECT DISTINCT `Project Manager` FROM departments WHERE Director = :director  and `Project Manager` IS NOT NULL", nativeQuery = true)
	LinkedList<String> findDistinctProjectManager(@Param("director") String director);

	@Query(value = "SELECT DISTINCT `Pyramid Account` FROM departments WHERE `Group Lead` = :groupLead and `BU Lead` = :buLead and `Pyramid Account` IS NOT NULL ", nativeQuery = true)
	HashSet<String> findPyramidAccount(@Param("buLead") String buLead, @Param("groupLead") String groupLead);

	@Query(value = "SELECT DISTINCT Director FROM departments WHERE `Group Lead` = :groupLead and `BU Lead` = :buLead and  `Pyramid Account` = :pyramidAccount and `Pyramid Account` IS NOT NULL ", nativeQuery = true)
	HashSet<String> findDistinctDirectors(@Param("buLead") String buLead, @Param("groupLead") String groupLead,
			@Param("pyramidAccount") String pyramidAccount);

	@Query(value = "SELECT DISTINCT Director FROM departments WHERE `Project Manager` = :projectManger and `Pyramid Account` IS NOT NULL ", nativeQuery = true)
	LinkedList<String> findUniqDirectorsByRm(@Param("projectManger") String projectManger);

}