package com.encomm.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.encomm.models.content.entityTypeP.ParaType;

@Service
public abstract class GenericEncommContentServiceImpl<E, K> implements GenericEncommContentService<E, K> {

	private GenericEncommContentService<E, K> genericImplDao;

	public GenericEncommContentServiceImpl(GenericEncommContentService<E, K> genericImplDao) {
		this.genericImplDao = genericImplDao;
	}

	public GenericEncommContentServiceImpl() {
	}

	@Override
	@Transactional
	public void saveOrUpdate(E entity) {
		genericImplDao.saveOrUpdate(entity);
	}

	@Override
	@Transactional
	public List<ParaType> getAll() {
		return genericImplDao.getAll();
	}

	@Override
	@Transactional
	public E get(K id) {
		return genericImplDao.find(id);
	}

	@Override
	@Transactional
	public void add(E entity) {
		genericImplDao.add(entity);
	}

	@Override
	@Transactional
	public void update(E entity) {
		genericImplDao.update(entity);
	}

	@Override
	@Transactional
	public void remove(E entity) {
		genericImplDao.remove(entity);
	}
}