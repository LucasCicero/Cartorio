package com.cartorioapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.cartorioapp.models.Cartorio;

public interface CartorioRepository extends CrudRepository<Cartorio, String>{
	
	Cartorio findByCodigo(long codigo);
	
	

}
