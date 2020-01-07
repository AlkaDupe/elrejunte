package com.beans;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import com.emailservice.MailService;
import com.entidades.Usuario;
import com.servicios.UsuarioEJBBean;


@SuppressWarnings("deprecation")
@ManagedBean(name = "mailBean")
@SessionScoped
public class MailBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@EJB
	private UsuarioEJBBean ejb;
	
	private String correo;
	private String nombreUsuario;	
	
	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	
	public String ret() {
		
		return "/login/resetPasswordForm.xhtml";
	}

	public void enviarEmail() {

		//Usuario s = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");

		// Configuración del servidor de correo
		String host = "smtp.gmail.com";
		String port = "587";
		String mailFrom = "helpdesk.elrejunte@gmail.com";
		String password = "elrejunte2020";

		String mailTo = "helpdesk.elrejunte@gmail.com";
		String subject = "Solicitud de reinicio de contraseña";

		//  HTML
		String message = "<i>El usuario "+nombreUsuario+" solicitó una nueva contraseña</i><br>";	
		message += "<h4>Su correo electrónico de contacto es: "+correo+"<h4>";
		message += "<img src=\"C:Users/Gabriel/GitElRejunte/JSF_ProyectoFinal/WebContent/resources/imagenes/ItSupport.jpg\">";		
	

		MailService mail = new MailService();
		
		List<Usuario> us = ejb.obtenerUserUsuario(nombreUsuario);
		
		if(!us.isEmpty()) {			
	

		try {
			mail.sendHtmlEmail(host, port, mailFrom, password, mailTo, subject, message);
			System.out.println("E-Mail enviado.");
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Email enviado"));

		} catch (Exception ex) {
			System.out.println("Error");
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "El Email no ha sido enviado, compruebe el correo elecrtónico"));
		}
		
	}else {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "El usuario no existe en el sistema, compruebe su nombre de usuario"));
		return;
	}
		
	}
	
	
	
}
