package com.beans;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


import com.entidades.Rol;
import com.servicios.RolEJBBean;

@SuppressWarnings("deprecation")
@ManagedBean(name = "rol")
@SessionScoped
@RequestScoped
public class RolBean {
	
	private Long id;
	private String nombre;
	
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
	
	
	@EJB RolEJBBean rol;
	
	public String agregarRol() {
		
		try {
			
		rol.agregarRol(nombre);	
		
		return nombre;
			
		}catch(Exception e) {
			e.printStackTrace();
			
			return null;
		}
		
	}
	
	public List<Rol> obtenerRoles() {
		
		List<Rol> obtenerRoles;
		
		obtenerRoles = rol.obtenerRoles();
		
		return obtenerRoles;
	}
	
	
	
}

