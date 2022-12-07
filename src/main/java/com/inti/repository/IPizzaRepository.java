package com.inti.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.inti.model.Pizza;

@Repository
public interface IPizzaRepository extends JpaRepository<Pizza, Integer> {
	
	@Query (value = "select id, ingredients, nom, prix from pizza", nativeQuery = true)
	List<Pizza> cartePizza ();

	@Query (value = "select prix from Pizza where id =:idPizza", nativeQuery = true)
	double prixPizza (@Param("idPizza")int idPizza);
}
