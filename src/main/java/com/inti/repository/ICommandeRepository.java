package com.inti.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.inti.model.CommandePizza;
import com.inti.model.Pizza;

public interface ICommandeRepository extends JpaRepository<CommandePizza, Integer>{

	@Query (value = "select id_commande_pizza from commande_pizza", nativeQuery = true)
	List<Integer> listeIdCommandes();
	
}
