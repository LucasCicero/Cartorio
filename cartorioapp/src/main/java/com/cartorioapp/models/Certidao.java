package com.cartorioapp.models;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import javax.validation.constraints.NotEmpty;


@Entity
public class Certidao {

	@Id 
	private String nomeCertidao;
	
	@ManyToOne
	private Cartorio cartorio;

	public String getNomeCertidao() {
		return nomeCertidao;
	}

	public void setNomeCertidao(String nomeCertidao) {
		this.nomeCertidao = nomeCertidao;
	}

	public Cartorio getCartorio() {
		return cartorio;
	}

	public void setCartorio(Cartorio cartorio) {
		this.cartorio = cartorio;
	}
	
	
	
	
}
	
	

	
	
	
	
	
	
	

	