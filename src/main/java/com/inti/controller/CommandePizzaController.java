package com.inti.controller;

import java.util.ArrayList;
import java.util.Scanner;

import org.apache.kafka.common.message.ListGroupsResponseData.ListedGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inti.model.CommandeComptoir;
import com.inti.model.CommandePizza;
import com.inti.model.Pizza;
import com.inti.repository.IPizzaRepository;

@RestController
public class CommandePizzaController {

	Scanner scan = new Scanner(System.in);

	@Autowired
	IPizzaRepository ipr;
//	ArrayList<Pizza> listePizzas = new ArrayList<>();

	public void creationCommande() {
		
	ArrayList<Pizza> listePizza =  (ArrayList<Pizza>) ipr.cartePizza();
	int count =0;
	double prix=0;
	CommandeComptoir commandeComptoir = new CommandeComptoir();
		for (int i = 0; i<listePizza.size(); i++) {
			System.out.println("combien voulez vous de " + listePizza.get(i) + " ?" );
			int nbrPizza =scan.nextInt();
		count++;	
		prix += nbrPizza * ipr.prixPizza();
		}
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
		commandeComptoir.setIdCommandeComptoir(count);
		commandeComptoir.setNomClient(nom);
	CommandePizza commandeClient = new CommandePizza(count, listePizza,prix ,commandeComptoir);
	System.out.println(commandeClient.toString());
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
