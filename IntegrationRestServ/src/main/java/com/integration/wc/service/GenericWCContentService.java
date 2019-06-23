package com.integration.wc.service;

import java.util.List;

import com.integration.wc.models.ContentTsType;

public interface GenericWCContentService<E, K> {
	public void add(E entity);

	public void saveOrUpdate(E entity);

	public void update(E entity);

	public void remove(E entity);

	public E find(K key);

	public List<ContentTsType> getAll();

	E getById(String id);

	E get(K id);

}