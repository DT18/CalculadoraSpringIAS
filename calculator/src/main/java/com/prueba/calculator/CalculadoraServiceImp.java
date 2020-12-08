package com.prueba.calculator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class CalculadoraServiceImp implements CalculadoraService{
	
	@Autowired
	private CalculadoraRepositorio repo;
		
	@Override
	public Calculadora add(Calculadora c) {
		// TODO Auto-generated method stub
		return repo.save(c);		
	}

	@Override
	public List<Calculadora> calcular(String id) {
		// TODO Auto-generated method stub
		return repo.findByIdtecnico(id);
	}

	

}
