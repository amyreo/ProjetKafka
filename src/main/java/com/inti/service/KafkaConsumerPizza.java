package com.inti.service;

import java.util.List;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.inti.model.Pizza;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KafkaConsumerPizza {
	
	@KafkaListener(topics = "topicComptoir", groupId = "myGroup")
	public void consumerComptoir(List<Pizza> listePizzas) {
		log.info("La liste des pizzas commandées est la suivante : ");
		for (Pizza pizza : listePizzas) {
			log.info(pizza.getNom());
		}
	}
	
	@KafkaListener(topics = "topicCuisine", groupId = "myGroup")
	public void consumerCuisine(List<Pizza> listePizzas) {
		log.info("La liste des pizzas à cuisiner est la suivante : ");
		for (Pizza pizza : listePizzas) {
			log.info(pizza.getNom() + pizza.getIngredients());
		}
	}

}
