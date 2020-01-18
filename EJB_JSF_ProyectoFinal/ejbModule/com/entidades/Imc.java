package com.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * Entity implementation class for Entity: Imc
 *
 */
@Entity

public class Imc implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "SEQ_ID_IMC", sequenceName = "SEQ_ID_IMC", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "SEQ_ID_IMC")
	@Column
	private long id;
	
	private float altura;
	
	private float peso;
	
	@Temporal(TemporalType.DATE)
	private Date fecha;
	
	private float calcImc;
	
	@ManyToOne(fetch = FetchType.LAZY,cascade ={CascadeType.MERGE})
	@JsonBackReference
    @JoinColumn(name = "USUARIO_ID")
    private Usuario usuario;

	public Imc() {
		super();
	}	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public float getAltura() {
		return altura;
	}

	public void setAltura(float altura) {
		this.altura = altura;
	}

	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public float getCalcImc() {
		return calcImc;
	}

	public void setCalcImc(float calcImc) {
		this.calcImc = calcImc;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Imc(float altura, float peso, Date fecha, float calcImc, Usuario usuario) {
		super();
		this.altura = altura;
		this.peso = peso;
		this.fecha = fecha;
		this.calcImc = calcImc;
		this.usuario = usuario;
	}	
   
}