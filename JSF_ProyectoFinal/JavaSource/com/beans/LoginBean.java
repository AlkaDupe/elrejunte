package com.beans;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.entidades.Usuario;
import com.servicios.UsuarioEJBBean;

@ManagedBean(name = "login")
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;

	public boolean isLogged = false;
	private Usuario usuario;

	@EJB
	private UsuarioEJBBean ejb;

	@PostConstruct
	public void init() {

		usuario = new Usuario();

	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/*
	 * public String logout() {
	 * FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	 * return "/login/login.xhtml?faces-redirect=true"; }
	 */

	public String iniciarSesion() {

		// String para utilizar la pagina a la que voy a redirigir el login
		String redireccion = null;
		Usuario us = null;

		try {

			us = ejb.iniciarSesion(usuario);

			// Si el usuario no está vacío, retorno la pagina princpal
			if (us != null) {

				if (us.getID_ROL().getNombre().equals("ADMINISTRADOR")) {
					// Almaceno la variable usuario, para controlar ver pagias de la aplicacion sin
					// antes haber iniciado sesion

					isLogged = true;
					redireccion = "/private/inicioAdministrador.xhtml?faces-redirect=true";
				} else if (us.getID_ROL().getNombre().equals("USUARIO")) {
					isLogged = true;
					redireccion = "/private/inicioUsuario.xhtml?faces-redirect=true";
				} else if ((us.getID_ROL().getNombre().equals("PERSONAL_INSTITUCION"))) {
					isLogged = true;
					redireccion = "/private/inicioPersonal.xhtml?faces-redirect=true";
				}

			} else {

				// Sino muestro mensaje de error de usuario y contraseña
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
						"Credenciales Incorrectas", "Credenciales Incorrectas"));

			}

		} catch (Exception e) {

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Error desconocido"));

		}

		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", us);
		return redireccion;

	}

public String seguridadPaginas() {
	
	String redireccion = null;
	Usuario us = null;
			
			try {
				
				us = ejb.iniciarSesion(usuario);

				HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
				String direccion = request.getContextPath() + "/private/ingresoAlimento.xhtml";
				
				boolean url = request.getRequestURL().toString().equals(direccion);
				
				if(us.getID_ROL().getNombre().equals("ADMINISTRADOR") || url );			
				
				redireccion = "/private/login/login.xhtml?faces-redirect=true";
				
			} catch(Exception e) {
				
				e.getMessage();
				
			}		
			
			return redireccion;
	}

	public String cerrarSesion() {

		String redireccion = null;

		isLogged = false;
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("usuario");

		redireccion = "/private/login/login.xhtml?faces-redirect=true";

		return redireccion;

	}

}
