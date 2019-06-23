package com.integration.wc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.integration.wc.models.IntegrationMetadata;
import com.integration.wc.repository.MetadataRepository;

@Service
public class DefaultMetadataService implements MetadataService {

	@Autowired
	 MetadataRepository metadataRepository;


	@Override
	public void add(IntegrationMetadata entity) {
		// TODO Auto-generated method stub
		metadataRepository.save(entity);
	}

	@Override
	public void saveOrUpdate(IntegrationMetadata entity) {
		// TODO Auto-generated method stub
		metadataRepository.save(entity);
	}

	@Override
	public void update(IntegrationMetadata entity) {
		// TODO Auto-generated method stub
		metadataRepository.save(entity);
	}

	@Override
	public void remove(IntegrationMetadata entity) {
		// TODO Auto-generated method stub
		metadataRepository.delete(entity);
	}

	@Override
	public IntegrationMetadata find(String key) {
		// TODO Auto-generated method stub
		return metadataRepository.findById(key).get();
	}

	@Override
	public List<IntegrationMetadata> getAll() {
		// TODO Auto-generated method stub
		return (List<IntegrationMetadata>) metadataRepository.findAll();
	}

	@Override
	public IntegrationMetadata getById(String id) {
		// TODO Auto-generated method stub
		return metadataRepository.findById(id).get();
	}

	@Override
	public IntegrationMetadata get(String id) {
		// TODO Auto-generated method stub
		return metadataRepository.findById(id).get();
		}

}
