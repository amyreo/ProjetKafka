package com.inti.controller;

import java.util.ArrayList;
import java.util.Scanner;

import org.apache.kafka.common.message.ListGroupsResponseData.ListedGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inti.model.CommandeComptoir;
import com.inti.model.CommandePizza;
import com.inti.model.Pizza;
import com.inti.repository.IPizzaRepository;


import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CommandePizzaController {

	Scanner scan = new Scanner(System.in);
	int numCommande=0;
	
	@Autowired
	IPizzaRepository ipr;
	
	@Autowired
	KafkaTemplate<Object, CommandePizza> kte;

	
	public void creationCommande() {
		
	ArrayList<Pizza> listeCommande = new ArrayList<>();
		
	double prix=0;
	
	System.out.println("Quelle pizza voulez vous ? Rentrer son identifiant.");
	int idPizza = scan.nextInt();
	do {
	Pizza pizzaClient = ipr.getReferenceById(idPizza);
	listeCommande.add(idPizza, pizzaClient);
	prix += ipr.prixPizza(idPizza);
	System.out.println("Si vous voulez une autre pizza rentrer son identifiant même si vous en voulez une du même type ou si vous avez terminé rentrer 777");
	idPizza = scan.nextInt();
	}while(idPizza != 777);
	
	CommandeComptoir commandeComptoir = new CommandeComptoir();
		System.out.println("voulez vous payer sur place ou a emporté ? P/E" );	
		String payer =scan.next();
		System.out.println("votre nom ?" );	
		String nom =scan.next();
		if (payer == "P") {
		commandeComptoir.setAPayer(false);
		}else {
		commandeComptoir.setAPayer(true);
		}
		commandeComptoir.setPrixCommande(prix);
		commandeComptoir.setIdCommandeComptoir(numCommande);
		commandeComptoir.setNomClient(nom);
	CommandePizza commandeClient = new CommandePizza(numCommande, listeCommande,prix ,commandeComptoir);
	System.out.println(commandeClient.toString());
	numCommande++;
	}
	
	@GetMapping("/sendPizza")
	public void SendPizza(CommandePizza commandePizza)
	{
		log.info("l'objet " + commandePizza + "a bien été envoyé");
	
		Message <CommandePizza> mess =MessageBuilder
		 .withPayload(commandePizza)
		 .setHeader(KafkaHeaders.TOPIC, "topicComptoir}").build();
		kte.send(mess);
	}
	

	@GetMapping("/comptoir")
	public void prixAPayer() {
		
//		System.out.println("Le montant des pizzas du client s'élève à " + prix + " €");
	}

	@GetMapping("/cuisine")
	public void pizzaAFaire() {

		// récupérer la liste de pizza à faire avec leurs quantités(for each pizza add to the list)
		// for each get quantité et faire le total de pizza à faire grâce à la variable quantité.

//		System.out.println("Les pizzas à faire sont les suivantes : "+listePizzas);
	}
}
