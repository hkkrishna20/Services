package com.integration.wc.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.integration.wc.models.RegisterBean;
@Repository
public interface RegisterRepository extends  CrudRepository<RegisterBean, String> {

}