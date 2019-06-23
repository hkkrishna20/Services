package com.integration.wc.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.integration.wc.models.UserBean;
@Repository
public interface LoginRepository extends  CrudRepository<UserBean, Long> {

}