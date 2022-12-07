package com.inti.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
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
	
	
	@ManyToOne
	@JoinTable(name="Pizza_CommandePizza",
			joinColumns = @JoinColumn (name ="idCommandePizza"),
				inverseJoinColumns = @JoinColumn(name="idPizza"))
	private CommandePizza commandePiza;

}
