package com.inti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.inti.model.CommandePizza;
import com.inti.repository.ICommandeRepository;
import com.inti.repository.IPizzaRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KafkaProducerPizza {

	@Autowired
	IPizzaRepository ipr;

	@Autowired
	KafkaTemplate<String, CommandePizza> kte;

	@Autowired
	ICommandeRepository icr;

	public void sendPizza() {

		List<CommandePizza> listeCommandes = icr.findAll();
		System.out.println(listeCommandes.getClass() + " " +  listeCommandes.size());
		System.out.println(listeCommandes.toString());
//		kte.send("topicComptoir", listeCommandes.get(0));
//		log.info("La liste des commandes a été envoyée.");
	}

}
