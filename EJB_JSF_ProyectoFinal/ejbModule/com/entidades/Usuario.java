package com.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;



@Entity
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property="ID_USUARIO")
@Table(name = "USUARIO")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;	

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ID_USUARIO")
	private Long id;

	@Column(name = "DOCUMENTO")
	private String ci;

	@Column(name = "NOMBRE")
	private String nombre;

	@Column(name = "APELLIDO")
	private String apellido;

	@Column(name = "GENERO")
	private String genero;

	@Column(name = "CONTRASENA")
	private String contrasena;

	@Column(name = "USUARIO")
	private String usuario;

	@Column(name = "CORREO_ELECTRONICO")
	private String email;	

	@JoinColumn(name = "ID_ROL", referencedColumnName = "ID_ROL")
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	private Rol ID_ROL;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "FECHA_NACIMIENTO")
	private Date FechaNacimiento;

	public Usuario() {
		
	}

	public Usuario(String ci, String nombre, String apellido, String genero, String contrasena, String email,
			Rol iDRol, Date FechaNacimiento, String usuario) {

		this.ci = ci;
		this.nombre = nombre;
		this.apellido = apellido;
		this.genero = genero;
		this.contrasena = contrasena;
		this.email = email;
		this.ID_ROL= iDRol;
		this.FechaNacimiento = FechaNacimiento;
		this.usuario = usuario;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCi() {
		return ci;
	}

	public void setCi(String ci) {
		this.ci = ci;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getFechaNacimiento() {
		return FechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		FechaNacimiento = fechaNacimiento;
	}

	public Rol getID_ROL() {
		return ID_ROL;
	}

	public void setID_ROL(Rol iD_ROL) {
		ID_ROL = iD_ROL;
	}
	
}
