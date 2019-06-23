package com.example.demo.kafka.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

import com.example.demo.model.Profile2;

public class Sender {
private static final Logger LOGGER = LoggerFactory.getLogger(Sender.class);

	
    @Autowired
    private KafkaTemplate<String, Profile2> simpleKafkaTemplate;
    
    
    
    public void send(String topic, Profile2 temp) {
    	System.out.println(" sjdksd "+temp);
    	System.out.println("topic" + topic);
        LOGGER.info("sending payload='{}' to topic='{}'", temp, topic);
        simpleKafkaTemplate.send(topic, temp);
}
}
