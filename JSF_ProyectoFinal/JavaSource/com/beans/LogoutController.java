package com.beans;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@SessionScoped
@ManagedBean
public class LogoutController implements Serializable{

	
	private static final long serialVersionUID = 1L;

	
	public void cerrarSesion() {
		
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();	
	}
	
}
