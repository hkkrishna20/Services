package com.example.demo.service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
	public Profile saveDetails(Profile black) {

		String hashVal = getMd5(black.getUser_password());
		black.setUser_password(hashVal);
		System.out.println(hashVal);
		return profileRepository.save(black);

	}

	@Override
	public String getMd5(String input) {


		try { 


			MessageDigest md = MessageDigest.getInstance("MD5"); 


			byte[] messageDigest = md.digest(input.getBytes()); 

			// Convert byte array into signum representation 
			BigInteger no = new BigInteger(1, messageDigest); 

			// Convert message digest into hex value 
			String hashtext = no.toString(16); 
			while (hashtext.length() < 32) { 
				hashtext = "0" + hashtext; 
			} 
			return hashtext; 
		}  

		// For specifying wrong message digest algorithms 
		catch (NoSuchAlgorithmException e) { 
			throw new RuntimeException(e); 
		} 

	}






}
