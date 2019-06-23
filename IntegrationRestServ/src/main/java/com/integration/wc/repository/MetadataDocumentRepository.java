package com.integration.wc.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.integration.wc.models.IntegrationMetadataDocument;
@Repository
public interface MetadataDocumentRepository extends CrudRepository<IntegrationMetadataDocument, String> {

}