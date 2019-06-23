package com.encomm.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.encomm.models.content.entityTypeP.FileTableType;
@Repository
public interface EncommFileRepository extends CrudRepository<FileTableType, Integer> {

}