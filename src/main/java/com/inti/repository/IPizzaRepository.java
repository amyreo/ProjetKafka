package com.inti.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.inti.model.Pizza;

@Repository
public interface IPizzaRepository extends JpaRepository<Pizza, Integer> {
	
	@Query (value = "select id,nom,prix, ingredients from Pizza", nativeQuery = true)
	List<Pizza> cartePizza ();

	@Query (value = "select prix from Pizza", nativeQuery = true)
	double prixPizza ();
}
