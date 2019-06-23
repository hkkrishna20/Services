package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.demo.kaka.producer.Sender;
import com.example.demo.model.Profile;
import com.example.demo.model.Profile2;
import com.example.demo.repository.ProfileRepository;

@Service
@Component
public class ProfileServiceImpl implements ProfileService {
	
	@Value("${spring.kafka.topic.userRegistered}")
    private String eValid;
	
	@Autowired
	private ProfileRepository profileRepository;
	
	@Autowired
	private Sender sender;

	@Override
	public Profile findbyid(Integer user_account) {
		Profile result1 = profileRepository.findById(user_account).get();
		
//		Profile1 temp = new Profile1();
//		temp.setUser_account(result1.getUser_account());
//		temp.setEmail_id(result1.getEmail_id());
//		sender.send(eValid, temp);
		return result1;
	}

	@Override
	public Profile SendToOtp(int user_account, String email_id) {
		Profile2 temp = new Profile2();
		temp.setUser_account(user_account);
		temp.setEmail_id(email_id);
		System.out.println("hey man"+temp);
		
		sender.send(eValid, temp);
		return null;
	}

	@Override
	public void SendToOtp1(Profile2 profile) {
		System.out.println("chal ja bhai" + profile);
		sender.send(eValid, profile);
	}

	@Override
	public Profile findByUsername(String username) {
		Profile result = profileRepository.findByUsername(username);
        return result;
	}

	
}
