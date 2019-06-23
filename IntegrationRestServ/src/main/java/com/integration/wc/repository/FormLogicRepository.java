package com.integration.wc.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.integration.wc.models.LogicTableBRE;

@Repository
public interface FormLogicRepository extends CrudRepository<LogicTableBRE, String> {

}