package com.integration.wc.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.integration.wc.models.ContentTsType;

@Service
public abstract class GenericWCContentServiceImpl<E, K> implements GenericWCContentService<E, K> {

	private GenericWCContentService<E, K> genericImplDao;

	public GenericWCContentServiceImpl(GenericWCContentService<E, K> genericImplDao) {
		this.genericImplDao = genericImplDao;
	}

	public GenericWCContentServiceImpl() {
	}

	@Override
	@Transactional
	public void saveOrUpdate(E entity) {
		genericImplDao.saveOrUpdate(entity);
	}

	@Override
	@Transactional
	public List<ContentTsType> getAll() {
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