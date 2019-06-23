package com.encomm.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.encomm.models.content.entityTypeP.ParaType;
@Repository
public interface EncommContentRepository extends CrudRepository<ParaType, Integer> {

}