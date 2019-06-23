package com.integration.wc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.integration.wc.models.IntegrationAudit;
import com.integration.wc.repository.AuditRepository;

@Service
public class DefaultIntegrationAuditService implements AuditService {


	@Autowired
	 AuditRepository auditRepository;

	@Override
	public void add(IntegrationAudit entity) {
		// TODO Auto-generated method stub
		auditRepository.save(entity);
	}

	@Override
	public void saveOrUpdate(IntegrationAudit entity) {
		// TODO Auto-generated method stub
		auditRepository.save(entity);
	}

	@Override
	public void update(IntegrationAudit entity) {
		// TODO Auto-generated method stub
		auditRepository.save(entity);
	}

	public void remove(IntegrationAudit entity) {
		// TODO Auto-generated method stub
		auditRepository.delete(entity);

	}

	@Override
	public IntegrationAudit find(String key) {
		// TODO Auto-generated method stub
		return auditRepository.findById(key).get();
		}

	
	@Override
	public List<IntegrationAudit> getAll() {
		// TODO Auto-generated method stub
		return (List<IntegrationAudit>) auditRepository.findAll();
	}


	@Override
	public IntegrationAudit getById(String id) {
		// TODO Auto-generated method stub
		return auditRepository.findById(id).get();
	}

	@Override
	public IntegrationAudit get(String id) {
		// TODO Auto-generated method stub
		return auditRepository.findById(id).get();
	}


}
