package com.integration.wc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.integration.wc.models.ContentTsType;
import com.integration.wc.repository.WCContentRepository;

@Service
public class DefaultWCContentService implements WCContentService {

	@Autowired
	 WCContentRepository contentRepository;
	
	
	@Override
	public ContentTsType find(Integer key) {
		// TODO Auto-generated method stub
		return contentRepository.findById(key).get();
	}

	@Override
	public List<ContentTsType> getAll() {
		// TODO Auto-generated method stub
		return toList(contentRepository.findAll()) ;
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
	public ContentTsType getById(Integer id) {
		// TODO Auto-generated method stub
		return contentRepository.findById(id).get();
		
	}

	@Override
	public ContentTsType get(Integer id) {
		// TODO Auto-generated method stub
		return contentRepository.findById(id).get();
	}

	@Override
	public void add(ContentTsType entity) {
		// TODO Auto-generated method stub
		contentRepository.save(entity);

	}

	@Override
	public void saveOrUpdate(ContentTsType entity) {
		// TODO Auto-generated method stub
		contentRepository.save(entity);

	}

	@Override
	public void update(ContentTsType entity) {
		// TODO Auto-generated method stub
		contentRepository.save(entity);
		
	}

	@Override
	public void remove(ContentTsType entity) {
		// TODO Auto-generated method stub
		contentRepository.delete(entity);
		
	}

	@Override
	public ContentTsType getById(String id) {
		// TODO Auto-generated method stub
		return contentRepository.findById(Integer.parseInt(id)).get();
	}


}
