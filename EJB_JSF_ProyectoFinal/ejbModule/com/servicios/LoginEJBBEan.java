package com.servicios;

import java.io.Serializable;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Session Bean implementation class LoginEJBBEan
 */
@SuppressWarnings("deprecation")
@ManagedBean
@SessionScoped
public class LoginEJBBEan implements Serializable{

    
	private static final long serialVersionUID = 1L;
	
	
	private String usuario;
	private String contrasena;
	private String mensaje;
	

	public LoginEJBBEan() {
	       
    }


    public String getContrasena() {
		return contrasena;
	}



	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}



	public String getMensaje() {
		return mensaje;
	}



	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}



	public String getUsuario() {
		return usuario;
	}



	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}



	

}
