package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Profile;

public interface ProfileRepository extends CrudRepository<Profile, Integer>{
	
	Profile findByUsername(String username);
}
