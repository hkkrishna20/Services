package com.task.taskmanager.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.task.taskmanager.models.entity.Label;
import com.task.taskmanager.repository.LabelMangerRepository;

@Service
public class GenericLabelManagerServiceImpl {

	@Autowired
	private LabelMangerRepository genericImplDao;

	public GenericLabelManagerServiceImpl() {
	}

	@Transactional
	public void saveOrUpdate(Label entity) {
		genericImplDao.save(entity);
	}

	@Transactional
	public Optional<Label> get(String id) {
		return genericImplDao.findById(id);
	}

	@Transactional
	public void add(Label entity) {
		genericImplDao.save(entity);
	}

	@Transactional
	public void update(Label entity) {
		genericImplDao.save(entity);
	}

	@Transactional
	public void remove(Label entity) {
		genericImplDao.deleteById(entity.getId());
	}

	public Optional<Label> find(String key) {
		// TODO Auto-generated method stub
		return genericImplDao.findById(key);

	}

	public Iterable<Label> getAll() {
		// TODO Auto-generated method stub
		return genericImplDao.findAll();
	}

}