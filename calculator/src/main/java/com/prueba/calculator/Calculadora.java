package com.prueba.calculator;

import javax.persistence.*;

@Entity
@Table(name="reportservice")
public class Calculadora {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private String idtecnico;
	@Column
	private String idservicio;
	@Column
	private String finicio;
	@Column
	private String ffin;
	
	/*private String semana;
	public String getSemana() {
		return semana;
	}
	public void setSemana(String semana) {
		this.semana = semana;
	}*/
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIdtecnico() {
		return idtecnico;
	}
	public void setIdtecnico(String idtecnico) {
		this.idtecnico = idtecnico;
	}
	public String getIdservicio() {
		return idservicio;
	}
	public void setIdservicio(String idservicio) {
		this.idservicio = idservicio;
	}
	public String getFinicio() {
		return finicio;
	}
	public void setFinicio(String finicio) {
		this.finicio = finicio;
	}
	public String getFfin() {
		return ffin;
	}
	public void setFfin(String ffin) {
		this.ffin = ffin;
	}
	
}
