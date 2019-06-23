package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.model.Profile;
import com.example.demo.model.Profile2;

@Service
public interface ProfileService {
	Profile findbyid(Integer user_account);

	Profile SendToOtp(int user_account, String email_id);
	
	 Profile saveDetails(Profile black);
	
	

	void SendToOtp1(Profile2 profile);

	String getMd5(String input);
}
