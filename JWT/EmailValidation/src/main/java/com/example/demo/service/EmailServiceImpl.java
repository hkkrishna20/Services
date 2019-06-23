package com.example.demo.service;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.demo.kafka.producer.Sender;
import com.example.demo.model.Profile2;
import com.example.demo.repository.EmailRepository;

@Service
@Component
public class EmailServiceImpl implements EmailService {

	@Value("${spring.kafka.topic.userRegistered1}")
    private String OtpGenerate;
	
	@Autowired
	private Sender sender;
	
	@Autowired
	private EmailRepository emailRepository;
	
	@Override
	public void isValid(Profile2 temp) {
		String email = temp.getEmail_id();
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$"; 
		Pattern pat = Pattern.compile(emailRegex);
		String testEmail =emailRepository.findByBemailId(email); 
		System.out.println(""+testEmail);
		if(pat.matcher(email).matches() == false) {
//			sender.send(OtpGenerate, temp);
			System.out.println("entered email id is invalid");			
		}
		//else if(emailRepository.equals(email) == true) {

		else if(null !=testEmail)
		{
			System.out.println("Entered email Id is Blacklisted");
		}
		else {
			sender.send(OtpGenerate, temp);
		}
	}

}
