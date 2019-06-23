package com.integration.wc.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.integration.wc.models.IntegrationAudit;
@Repository
public interface AuditRepository extends CrudRepository<IntegrationAudit, String> {

}