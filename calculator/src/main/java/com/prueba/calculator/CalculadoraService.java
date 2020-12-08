package com.prueba.calculator;

import java.util.List;

import org.springframework.stereotype.Service;


public interface CalculadoraService {

	Calculadora add(Calculadora c);
	List<Calculadora> calcular(String id);
}
