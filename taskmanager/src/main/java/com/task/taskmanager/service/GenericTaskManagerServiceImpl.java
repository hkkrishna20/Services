package com.task.taskmanager.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.task.taskmanager.models.entity.Task;
import com.task.taskmanager.repository.TaskMangerRepository;

@Service
public  class GenericTaskManagerServiceImpl {

	@Autowired
	private TaskMangerRepository genericImplDao;

	

	public GenericTaskManagerServiceImpl() {
	}


	@Transactional
	public void saveOrUpdate(Task entity) {
		genericImplDao.save(entity);
	}




	@Transactional
	public Optional<Task> get(String id) {
		return genericImplDao.findById(id);
	}


	@Transactional
	public void add(Task entity) {
		genericImplDao.save(entity);
	}


	@Transactional
	public void update(Task entity) {
		genericImplDao.save(entity);
	}


	@Transactional
	public void remove(Task entity) {
		genericImplDao.deleteById(entity.getId());
	}


	public Optional<Task> find(String key) {
		// TODO Auto-generated method stub
		return genericImplDao.findById(key);
		
	}


	public Iterable<Task> getAll() {
		// TODO Auto-generated method stub
		return genericImplDao.findAll();
	}

}