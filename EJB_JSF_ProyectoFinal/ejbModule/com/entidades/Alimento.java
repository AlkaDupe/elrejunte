package com.entidades;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Alimento
 *
 */
@Entity
@Table(name = "ALIMENTO")
public class Alimento implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)		
	@Column(name = "ID_ALIMENTO")	
	private Long id;
	
	@Column(length = 200, name = "NOMBRE")
	private String nombre;
	
	@Column(length = 4, name = "AZUCAR")	
	private double azucar;
	
	@Column(length = 4, name = "CLORURO_DE_SODIO")
	private double cloruroDeSodio;
	
	@Column(length = 10, name = "VALOR_POR_PORCION")
	private double valorPorPorcion;
	
	@Column(length = 4, name = "GRASAS_TRANS")
	private double grasasTrans;
	
	@Column(length = 10, name = "GRASAS_SATURADAS")
	private double grasasSaturadas;
	
	@Column(length = 10, name = "VALOR_ENERGETICO")
	private double valorEnergetico;
	
	@Column(length = 10, name = "CALORIAS")
	private double calorias;
	
	@Column
	private String idUsuario;
	
	
	

	private static final long serialVersionUID = 1L;

	public Alimento() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getAzucar() {
		return azucar;
	}

	public void setAzucar(double azucar) {
		this.azucar = azucar;
	}

	public double getCloruroDeSodio() {
		return cloruroDeSodio;
	}

	public void setCloruroDeSodio(double cloruroDeSodio) {
		this.cloruroDeSodio = cloruroDeSodio;
	}

	public double getValorPorPorcion() {
		return valorPorPorcion;
	}

	public void setValorPorPorcion(double valorPorPorcion) {
		this.valorPorPorcion = valorPorPorcion;
	}

	public double getGrasasTrans() {
		return grasasTrans;
	}

	public void setGrasasTrans(double grasasTrans) {
		this.grasasTrans = grasasTrans;
	}

	public double getGrasasSaturadas() {
		return grasasSaturadas;
	}

	public void setGrasasSaturadas(double grasasSaturadas) {
		this.grasasSaturadas = grasasSaturadas;
	}

	public double getValorEnergetico() {
		return valorEnergetico;
	}

	public void setValorEnergetico(double valorEnergetico) {
		this.valorEnergetico = valorEnergetico;
	}

	public double getCalorias() {
		return calorias;
	}

	public void setCalorias(double calorias) {
		this.calorias = calorias;
	}      
	

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	
}
