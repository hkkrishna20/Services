package com.example.demo.kafka.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import com.example.demo.model.Profile2;
import com.example.demo.service.EmailService;



public class Receiver {
	private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);


    @Autowired
    private EmailService emailService;
    

    @KafkaListener(topics = "${spring.kafka.topic.userRegistered}")
    public void receive(Profile2 temp) {
    	Profile2 email = new Profile2();
    	email.setUser_account(temp.getUser_account());
    	email.setEmail_id(temp.getEmail_id());
    	System.out.printf("hey its me palash" , temp);
        LOGGER.info("received payload='{}'", temp);
          emailService.isValid(email);
    }
}
