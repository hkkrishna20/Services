package com.integration.wc.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.integration.wc.models.IntegrationAuditPoint;
@Repository
public interface AuditPointRepository extends CrudRepository<IntegrationAuditPoint, String> {

}