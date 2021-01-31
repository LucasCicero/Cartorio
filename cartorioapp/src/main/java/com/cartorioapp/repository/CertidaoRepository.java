package com.cartorioapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.cartorioapp.models.Cartorio;
import com.cartorioapp.models.Certidao;

public interface CertidaoRepository extends CrudRepository<Certidao, String >{

	Iterable<Certidao> findByCartorio(Cartorio cartorio);
	
	Certidao findByNomeCertidao(String nomeCertidao);
}
