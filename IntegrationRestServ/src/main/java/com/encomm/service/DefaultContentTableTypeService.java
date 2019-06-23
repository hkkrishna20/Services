package com.encomm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.encomm.models.content.entityTypeP.ParaType;
import com.encomm.repository.EncommContentRepository;

@Service
public class DefaultContentTableTypeService implements EncommContentService {

	@Autowired
	EncommContentRepository FileTableTypeRepository;

	@Override
	public ParaType find(Integer key) {
		// TODO Auto-generated method stub
		return FileTableTypeRepository.findById(key).get();
	}

	@Override
	public List<ParaType> getAll() {
		// TODO Auto-generated method stub
		return toList(FileTableTypeRepository.findAll());
	}

	public static <E> List<E> toList(Iterable<E> iterable) {
		if (iterable instanceof List) {
			return (List<E>) iterable;
		}
		ArrayList<E> list = new ArrayList<E>();
		if (iterable != null) {
			for (E e : iterable) {
				list.add(e);
			}
		}
		return list;
	}

	@Override
	public ParaType getById(Integer id) {
		// TODO Auto-generated method stub
		return FileTableTypeRepository.findById(id).get();

	}

	@Override
	public ParaType get(Integer id) {
		// TODO Auto-generated method stub
		return FileTableTypeRepository.findById(id).get();
	}

	@Override
	public void add(ParaType entity) {
		// TODO Auto-generated method stub
		FileTableTypeRepository.save(entity);

	}

	@Override
	public void saveOrUpdate(ParaType entity) {
		// TODO Auto-generated method stub
		FileTableTypeRepository.save(entity);

	}

	@Override
	public void update(ParaType entity) {
		// TODO Auto-generated method stub
		FileTableTypeRepository.save(entity);

	}

	@Override
	public void remove(ParaType entity) {
		// TODO Auto-generated method stub
		FileTableTypeRepository.delete(entity);

	}

	@Override
	public ParaType getById(String id) {
		// TODO Auto-generated method stub
		return FileTableTypeRepository.findById(Integer.parseInt(id)).get();
	}

}
