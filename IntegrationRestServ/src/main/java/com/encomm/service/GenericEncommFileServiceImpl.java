package com.encomm.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.encomm.models.content.entityTypeP.FileTableType;

@Service
public abstract class GenericEncommFileServiceImpl<E, K> implements GenericEncommFileService<E, K> {

	private GenericEncommFileService<E, K> genericImplDao;

	public GenericEncommFileServiceImpl(GenericEncommFileService<E, K> genericImplDao) {
		this.genericImplDao = genericImplDao;
	}

	public GenericEncommFileServiceImpl() {
	}

	@Override
	@Transactional
	public void saveOrUpdate(E entity) {
		genericImplDao.saveOrUpdate(entity);
	}

	@Override
	@Transactional
	public List<FileTableType> getAll() {
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