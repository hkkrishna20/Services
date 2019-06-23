package com.encomm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.encomm.models.content.entityTypeP.FileTableType;
import com.encomm.repository.EncommFileRepository;

@Service
public class DefaultEncommFileTableTypeService implements EncommFileService {

	@Autowired
	EncommFileRepository FileTableTypeRepository;
	
	
	@Override
	public FileTableType find(Integer key) {
		// TODO Auto-generated method stub
		return FileTableTypeRepository.findById(key).get();
	}

	@Override
	public List<FileTableType> getAll() {
		// TODO Auto-generated method stub
		return toList(FileTableTypeRepository.findAll()) ;
	}
	public static <E> List<E> toList(Iterable<E> iterable) {
	    if(iterable instanceof List) {
	      return (List<E>) iterable;
	    }
	    ArrayList<E> list = new ArrayList<E>();
	    if(iterable != null) {
	      for(E e: iterable) {
	        list.add(e);
	      }
	    }
	    return list;
	  }
	@Override
	public FileTableType getById(Integer id) {
		// TODO Auto-generated method stub
		return FileTableTypeRepository.findById(id).get();
		
	}

	@Override
	public FileTableType get(Integer id) {
		// TODO Auto-generated method stub
		return FileTableTypeRepository.findById(id).get();
	}

	@Override
	public void add(FileTableType entity) {
		// TODO Auto-generated method stub
		FileTableTypeRepository.save(entity);

	}

	@Override
	public void saveOrUpdate(FileTableType entity) {
		// TODO Auto-generated method stub
		FileTableTypeRepository.save(entity);

	}

	@Override
	public void update(FileTableType entity) {
		// TODO Auto-generated method stub
		FileTableTypeRepository.save(entity);
		
	}

	@Override
	public void remove(FileTableType entity) {
		// TODO Auto-generated method stub
		FileTableTypeRepository.delete(entity);
		
	}

	@Override
	public FileTableType getById(String id) {
		// TODO Auto-generated method stub
		return FileTableTypeRepository.findById(Integer.parseInt(id)).get();
	}

	}
