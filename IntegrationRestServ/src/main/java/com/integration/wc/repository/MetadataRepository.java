package com.integration.wc.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.integration.wc.models.IntegrationMetadata;

@Repository
public interface MetadataRepository extends CrudRepository<IntegrationMetadata, String> {

}