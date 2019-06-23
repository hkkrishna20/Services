package com.example.demo.service;

import com.example.demo.model.Profile;
import com.example.demo.model.Profile2;

public interface ProfileService {
	Profile findbyid(Integer user_account);
	Profile SendToOtp(int user_account, String email_id);
	void SendToOtp1(Profile2 profile);
	Profile findByUsername(String username);
}
