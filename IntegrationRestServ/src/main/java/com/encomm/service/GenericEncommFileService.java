package com.encomm.service;

import java.util.List;

import com.encomm.models.content.entityTypeP.FileTableType;

public interface GenericEncommFileService<E, K> {
	public void add(E entity);

	public void saveOrUpdate(E entity);

	public void update(E entity);

	public void remove(E entity);

	public E find(K key);

	public List<FileTableType> getAll();

	E getById(String id);

	E get(K id);

}