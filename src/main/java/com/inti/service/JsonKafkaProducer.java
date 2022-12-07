package com.inti.service;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.inti.model.Pizza;


import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JsonKafkaProducer {
	
	
	@Autowired
	KafkaTemplate<String, Pizza> kte;
	
	public void selectionPizza(Pizza pizza)
	{
		log.info("l'objet " + pizza + "a bien été envoyé");
		
		Message <Pizza> mess =MessageBuilder
		 .withPayload(pizza)
		 .setHeader(KafkaHeaders.TOPIC, "${topic_name}").build();
		kte.send(mess);

	}

}
