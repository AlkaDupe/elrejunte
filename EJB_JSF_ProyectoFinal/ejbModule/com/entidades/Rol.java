package com.entidades;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;


@Entity
@Table(name = "ROL" )
public class Rol implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ID_ROL")
	private Long id;
	
	@Column(name="NOMBRE")
	private String nombre;


	@OneToMany(mappedBy="ID_ROL", cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Usuario> usuario = new HashSet<>();
	
	 public Rol() {		
		
	}   
	
	public Rol(Long id, String nombre) {
		
		this.id = id;
		this.nombre = nombre;
		
	}		 

	public Long getId() {
		return id;
	}

	public void Long(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<Usuario> getUsuario() {
		return usuario;
	}

	public void setUsuario(Set<Usuario> usuario) {
		this.usuario = usuario;
		
		
	}
	public void setId(Long id) {
		this.id = id;
	}


   
	
	
}
