package com.prueba.calculator;

import java.util.List;

import org.springframework.data.repository.Repository;

public interface CalculadoraRepositorio extends Repository<Calculadora,Integer>{
	
	Calculadora save(Calculadora cal);
		
	List<Calculadora> findByIdtecnico(String id);
	
}
