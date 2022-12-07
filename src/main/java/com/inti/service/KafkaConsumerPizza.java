package com.inti.service;

import java.util.List;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.inti.model.CommandeComptoir;
import com.inti.model.CommandePizza;
import com.inti.model.Pizza;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KafkaConsumerPizza {

	@KafkaListener(topics = "topicComptoir", groupId = "myGroup")
	public void consumerComptoir(CommandePizza commandePizza) {
		log.info("La liste des pizzas commandées est la suivante : ");
		CommandeComptoir cc = commandePizza.getCommandeComptoir();
		List<Pizza> listePizza = commandePizza.getListePizza();
		int qtePizza = listePizza.size();
		log.info("Le prix de la commande est de : " + cc.getPrixCommande() + " pour " + qtePizza + " pizzas au total.");
		for (Pizza pizza : listePizza) {
			log.info("Pizza : " + pizza.getNom());
		}
	}

	@KafkaListener(topics = "topicCuisine", groupId = "myGroup")
	public void consumerCuisine(CommandePizza commandePizza) {
		List<Pizza> listePizza = commandePizza.getListePizza();
		log.info("La liste des pizzas à cuisiner est la suivante : ");
		for (Pizza pizza : listePizza) {
			log.info("Pizza : " + pizza.getNom() + pizza.getIngredients());
		}
	}

}
