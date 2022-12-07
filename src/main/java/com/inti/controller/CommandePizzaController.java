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
		double somme = 0;

		// for each get prix*qtité et faire le total à payer grâce à la variable somme.

		System.out.println("Le montant de vos délicieuses pizza s'élève à " + somme + " €");
	}

	@GetMapping("/cuisine")
	public void pizzaAFaire() {
		int quantite = 0;

		// récupérer la liste de pizza à faire avec leurs quantités(for each pizza add
		// to the list)
		// for each get quantité et faire le total de pizza à faire grâce à la variable
		// quantité.

//		System.out.println("La quantité de pizza à faire est de "+quantite+".\n Les pizzas à faire sont les suivantes : "+listePizzas);
	}
}
