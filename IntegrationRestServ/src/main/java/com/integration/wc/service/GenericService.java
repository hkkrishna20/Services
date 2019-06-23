package com.integration.wc.service;

import java.util.List;

public interface GenericService<E, K> {
	public void add(E entity);

	public void saveOrUpdate(E entity);

	public void update(E entity);

	public void remove(E entity);

	public E find(K key);

	public List<E> getAll();

	E getById(String id);

	E get(K id);

}