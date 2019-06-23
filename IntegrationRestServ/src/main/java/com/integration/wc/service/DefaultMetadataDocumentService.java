package com.integration.wc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.integration.wc.models.IntegrationMetadataDocument;
import com.integration.wc.repository.MetadataDocumentRepository;

@Service
public class DefaultMetadataDocumentService implements MetadataDocumentService {


	@Autowired
	 MetadataDocumentRepository metadataDocumentRepository;
	

	@Override
	public void add(IntegrationMetadataDocument entity) {
		// TODO Auto-generated method stub
		metadataDocumentRepository.save(entity);
	}

	@Override
	public void saveOrUpdate(IntegrationMetadataDocument entity) {
		// TODO Auto-generated method stub
		metadataDocumentRepository.save(entity);
	}

	@Override
	public void update(IntegrationMetadataDocument entity) {
		// TODO Auto-generated method stub
		metadataDocumentRepository.save(entity);
	}

	@Override
	public void remove(IntegrationMetadataDocument entity) {
		// TODO Auto-generated method stub
		metadataDocumentRepository.delete(entity);
	}

	@Override
	public IntegrationMetadataDocument find(String key) {
		// TODO Auto-generated method stub
		return metadataDocumentRepository.findById(key).get();
	}

	@Override
	public List<IntegrationMetadataDocument> getAll() {
		// TODO Auto-generated method stub
		return (List<IntegrationMetadataDocument>) metadataDocumentRepository.findAll();
	}

	@Override
	public IntegrationMetadataDocument getById(String id) {
		// TODO Auto-generated method stub
		return metadataDocumentRepository.findById(id).get();
	}

	@Override
	public IntegrationMetadataDocument get(String id) {
		// TODO Auto-generated method stub
		return metadataDocumentRepository.findById(id).get();
		}

}
