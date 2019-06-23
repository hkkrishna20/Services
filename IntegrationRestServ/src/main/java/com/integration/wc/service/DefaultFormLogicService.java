package com.integration.wc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.integration.wc.models.LogicTableBRE;
import com.integration.wc.repository.FormLogicRepository;



@Service
public class DefaultFormLogicService implements FormLogicService {

	@Autowired
	 FormLogicRepository formLogicRepository;



	@Override
	public void add(LogicTableBRE entity) {
		// TODO Auto-generated method stub
		formLogicRepository.save(entity);
		
	}

	@Override
	public void saveOrUpdate(LogicTableBRE entity) {
		// TODO Auto-generated method stub
		formLogicRepository.save(entity);
		
	}

	@Override
	public void update(LogicTableBRE entity) {
		// TODO Auto-generated method stub
		formLogicRepository.save(entity);
		
	}

	@Override
	public void remove(LogicTableBRE entity) {
		// TODO Auto-generated method stub
		formLogicRepository.delete(entity);
		
	}

	@Override
	public LogicTableBRE find(String key) {
		// TODO Auto-generated method stub
		return formLogicRepository.findById(key).get();
	}

	@Override
	public List<LogicTableBRE> getAll() {
		// TODO Auto-generated method stub
		return (List<LogicTableBRE>) formLogicRepository.findAll();
	}

	@Override
	public LogicTableBRE getById(String id) {
		// TODO Auto-generated method stub
		return formLogicRepository.findById(id).get();
		
	}

	@Override
	public LogicTableBRE get(String id) {
		// TODO Auto-generated method stub
		return formLogicRepository.findById(id).get();
	}

}
