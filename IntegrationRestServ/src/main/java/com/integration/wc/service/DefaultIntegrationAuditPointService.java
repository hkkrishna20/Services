package com.integration.wc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.integration.wc.models.IntegrationAuditPoint;
import com.integration.wc.repository.AuditPointRepository;



@Service
public class DefaultIntegrationAuditPointService implements AuditPointService {

	@Autowired
	 AuditPointRepository auditPointRepository;


	@Override
	public void add(IntegrationAuditPoint entity) {
		// TODO Auto-generated method stub
		auditPointRepository.save(entity);
	}

	@Override
	public void saveOrUpdate(IntegrationAuditPoint entity) {
		// TODO Auto-generated method stub
		auditPointRepository.save(entity);
	}

	@Override
	public void update(IntegrationAuditPoint entity) {
		// TODO Auto-generated method stub
		auditPointRepository.save(entity);
	}

	@Override
	public void remove(IntegrationAuditPoint entity) {
		// TODO Auto-generated method stub
		auditPointRepository.delete(entity);
	}

	@Override
	public IntegrationAuditPoint find(String key) {
		// TODO Auto-generated method stub
		return auditPointRepository.findById(key).get();
	}

	@Override
	public List<IntegrationAuditPoint> getAll() {
		// TODO Auto-generated method stub
		return (List<IntegrationAuditPoint>) auditPointRepository.findAll();
	}

	@Override
	public IntegrationAuditPoint getById(String id) {
		// TODO Auto-generated method stub
		return auditPointRepository.findById(id).get();
	}

	@Override
	public IntegrationAuditPoint get(String id) {
		// TODO Auto-generated method stub
		return auditPointRepository.findById(id).get();
		}

}
