package com.integration.wc.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

@Service
public abstract class GenericServiceImpl<E, K> implements GenericService<E, K> {
	
	private GenericService<E, K> genericDao;

	public GenericServiceImpl(GenericService<E, K> genericDao) {
		this.genericDao = genericDao;
	}

	public GenericServiceImpl() {
	}

	@Override
	@Transactional
	public void saveOrUpdate(E entity) {
		genericDao.saveOrUpdate(entity);
	}

	@Override
	@Transactional
	public List<E> getAll() {
		return genericDao.getAll();
	}

	@Override
	@Transactional
	public E get(K id) {
		return genericDao.find(id);
	}

	@Override
	@Transactional
	public void add(E entity) {
		genericDao.add(entity);
	}

	@Override
	@Transactional
	public void update(E entity) {
		genericDao.update(entity);
	}

	@Override
	@Transactional
	public void remove(E entity) {
		genericDao.remove(entity);
	}
}