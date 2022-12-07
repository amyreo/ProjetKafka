package com.inti.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (name = "commande_pizza")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommandePizza {
	
	@Id
	public int idCommandePizza;
	
	public double prixCommande ;
	

	@OneToMany
	@JoinTable(name="Pizza_CommandePizza",
				joinColumns = @JoinColumn (name ="idPizza"),
					inverseJoinColumns = @JoinColumn(name="idCommandePizza"))
	public List<Pizza> listePizza;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idCommandeComptoir")
	private CommandeComptoir commandeComptoir ;


	
}
