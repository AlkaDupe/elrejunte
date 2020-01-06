package com.beans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import com.emailservice.MailService;


@SuppressWarnings("deprecation")
@ManagedBean(name = "mailBean")
@SessionScoped
public class MailBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
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

	public void enviarEmail() throws Exception {

		//Usuario s = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");

		// Configuración del servidor de correo
		String host = "smtp.gmail.com";
		String port = "587";
		String mailFrom = "helpdesk.elrejunte@gmail.com";
		String password = "elrejunte2020";

		String mailTo = "helpdesk.elrejunte@gmail.com";
		String subject = "Solicitud de reinicio de contraseña";

		// message contains HTML markups
		String message = "<i>El usuario "+getNombreUsuario().trim()+" solicitó una nueva contraseña</i><br>";		

		MailService mail = new MailService();

		try {
			mail.sendHtmlEmail(host, port, mailFrom, password, mailTo, subject, message);
			System.out.println("E-Mail enviado.");
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Email enviado"));

		} catch (Exception ex) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "El Email no ha sido enviado, compruebe el correo elecrtónico"));
		}
	}
	
}
