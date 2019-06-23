package com.task.taskmanager.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.task.taskmanager.models.entity.User;
import com.task.taskmanager.repository.UserRepository;

@Service
public  class GenericUserServiceImpl {

	@Autowired
	private UserRepository genericImplDao;

	

	public GenericUserServiceImpl() {
	}


	@Transactional
	public void saveOrUpdate(User entity) {
		genericImplDao.save(entity);
	}




	@Transactional
	public Optional<User> get(String id) {
		return genericImplDao.findById(id);
	}


	@Transactional
	public void add(User entity) {
		genericImplDao.save(entity);
	}


	@Transactional
	public void update(User entity) {
		genericImplDao.save(entity);
	}


	@Transactional
	public void remove(User entity) {
		genericImplDao.deleteById(entity.getId());
	}


	public Optional<User> find(String key) {
		// TODO Auto-generated method stub
		return genericImplDao.findById(key);
		
	}


	public Iterable<User> getAll() {
		// TODO Auto-generated method stub
		return genericImplDao.findAll();
	}

}