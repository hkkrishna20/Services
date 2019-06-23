package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.BlackList;

public interface EmailRepository extends CrudRepository<BlackList , String>{
	
	public String findByBemailId(String email);

}
