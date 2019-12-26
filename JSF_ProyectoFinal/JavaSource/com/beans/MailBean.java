package com.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.Properties;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import com.emailservice.MailService;
import com.beans.UsuarioBean;

@ManagedBean(name = "mailBean")
@SessionScoped
public class MailBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	
	
	
}
