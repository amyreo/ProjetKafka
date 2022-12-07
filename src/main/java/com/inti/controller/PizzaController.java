package com.inti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inti.model.Pizza;
import com.inti.repository.IPizzaRepository;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/Pizza")
@Slf4j
public class PizzaController {

	@Autowired
	IPizzaRepository ipr;

	@PostMapping("/savePizza")


	
	public boolean savePizza(@RequestBody Pizza p) {
		if (p.getId() > 0) {
			log.info("La pizza a bien été créé");
			ipr.save(p);
			return true;
		}

		log.error("Veuillez saisir un identifiant valide");
		return false;
	}

	@GetMapping("/listePizzas")

	public List<Pizza> cartePizza() {
		log.info("Liste de toutes les pizzas");
		return ipr.cartePizza();
	}

	@GetMapping("/getPizza/{id}")
	public Pizza getPizza(@PathVariable int id) {
		try {
			log.info("La pizza a bien été affichée");
			return ipr.findById(id).get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.error("Veuillez saisir un id de pizza valide");
		return null;
	}

	@DeleteMapping("/deletePizza/{id}")
	public boolean deletePizza(@PathVariable int id) {
		if (id != 0) {
			log.info("La pizza a bien été supprimée");
			ipr.deleteById(id);
			return true;
		}
		log.error("Veuillez saisir un id de pizza valide");
		return false;
	}

	@PutMapping("/updatePizza/{id}")
	public Pizza updatePizza(@RequestBody Pizza nouvelPizza, @PathVariable int id) {
		return ipr.findById(id).map(Pizza -> {
			Pizza.setId(nouvelPizza.getId());
			Pizza.setNom(nouvelPizza.getNom());
			Pizza.setTaille(nouvelPizza.getTaille());
			Pizza.setPrix(nouvelPizza.getPrix());
			Pizza.setIngredients(nouvelPizza.getIngredients());
			return ipr.save(Pizza);
		}).orElseGet(() -> {
			return ipr.save(nouvelPizza);
		});
	}

	public double prixPizza() {
			return ipr.prixPizza(0);
	}
}
