package com.task.taskmanager.service;

public interface GenericTaskManagerService<E, K> {
	public void add(E entity);

	public void saveOrUpdate(E entity);

	public void update(E entity);

	public void remove(E entity);

	public E find(K key);
	E getById(String id);

	E get(K id);

}