package com.inti.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pizza {
	
	@Id
	private int id;
	private String nom;
	private String taille;
	private double prix;
	private String ingredients;
	private String typePate;
	private boolean livraison;
	private String heureLivraison;
	
}
