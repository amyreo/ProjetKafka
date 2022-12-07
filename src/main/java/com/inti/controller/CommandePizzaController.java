package com.inti.controller;

import java.util.ArrayList;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inti.model.CommandePizza;
import com.inti.model.Pizza;
import com.inti.repository.IPizzaRepository;

@RestController
public class CommandePizzaController {

	Scanner scan = new Scanner (System.in);
	
//	@Autowired
//	IPizzaRepository ipr;
//	ArrayList<Pizza> listePizzas = new ArrayList<>();
	@PutMapping("/commande")
	public void creationCommande() {
		CommandePizza commandeClient = new CommandePizza(0, null, 0);
		
	}
	
	
	@GetMapping("/comptoir")
	public void prixAPayer() {
		double somme = 0;
		
		// for each get prix*qtité et faire le total à payer grâce à la variable somme.
		
		System.out.println("Le montant de vos délicieuses pizza s'élève à "+somme+" €");
	}
	
	@GetMapping("/cuisine")
	public void pizzaAFaire() {
		int quantite = 0;
		
		//récupérer la liste de pizza à faire avec leurs quantités(for each pizza add to the list)
		//for each get quantité et faire le total de pizza à faire grâce à la variable quantité.
		
//		System.out.println("La quantité de pizza à faire est de "+quantite+".\n Les pizzas à faire sont les suivantes : "+listePizzas);
	}
}
