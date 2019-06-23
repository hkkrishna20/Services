package com.integration.wc.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.integration.wc.models.ContentTsType;
@Repository
public interface WCContentRepository extends CrudRepository<ContentTsType, Integer> {

}