package com.encomm.service;

import java.util.List;

import com.encomm.models.content.entityTypeP.ParaType;

public interface GenericEncommContentService<E, K> {
	public void add(E entity);

	public void saveOrUpdate(E entity);

	public void update(E entity);

	public void remove(E entity);

	public E find(K key);

	public List<ParaType> getAll();

	E getById(String id);

	E get(K id);

}