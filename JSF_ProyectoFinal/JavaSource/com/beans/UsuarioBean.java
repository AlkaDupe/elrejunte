package com.beans;

import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import com.emailservice.MailService;
import com.entidades.Rol;
import com.entidades.Usuario;
import com.servicios.UsuarioEJBBean;

@SuppressWarnings("deprecation")
@ManagedBean(name = "usuarioBean")
@SessionScoped
public class UsuarioBean {

	private Long id;
	private String ci;
	private String nombre;
	private String apellido;
	private String genero;
	private String contrasena;
	private String email;
	private String rolNombre;
	private Rol rol;
	private Date FechaNacimiento;
	private String usuario;
	private Usuario user;

	/*
	 * Getters y setters
	 */

	
	
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public Date getFechaNacimiento() {
		return FechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		FechaNacimiento = fechaNacimiento;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getRolNombre() {
		return rolNombre;
	}

	public void setRolNombre(String rolNombre) {
		this.rolNombre = rolNombre;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	// ---------------------------------------------

	@EJB
	private UsuarioEJBBean UsuarioEJBBean;

	public String crearUsuario() {

		// Creo nueva instancia de Rol
		Rol r = new Rol();

		try {

			// Dependiendo de qué rol sea asigno el id para cada rol
			if (getRolNombre().equals("ADMINISTRADOR")) {
				r.setId((long) 1);
			} else if (getRolNombre().equals("USUARIO")) {
				r.setId((long) 2);
			} else {
				r.setId((long) 3);
			}

			/*
			 * El nombre de usuario se crea con :nombre + apellido, vamos a crear una
			 * concatenación de los 2 para asignar el nombre de usuario nuevo
			 */
			// quito espacios y transormo el texto a minuscula
			String getName = getNombre().trim().toLowerCase();

			String getApellido = getApellido().trim().toLowerCase();

			// concateno los 2 valores
			String concat = getName.concat("." + getApellido);

			// y lo igualo
			usuario = concat;

			// Método para controlar CI duplicadas en base de datos
			List<Usuario> lista = UsuarioEJBBean.obtenerCIUsuario(ci);
			
			List <Usuario> lista2 = UsuarioEJBBean.obtenerUserUsuario(usuario);

			// Si las listas están vacias quiere decir que el CI O el Usuario ingresado no está registrado en
			// base de datos aún
			
			
			
			if (lista.isEmpty() && lista2.isEmpty()) {

				UsuarioEJBBean.agregarUsuario(ci, nombre, apellido, genero, contrasena, usuario, email, r,
						FechaNacimiento);
				
				//limpio el formulario luego de ingresar registros
				limpiar();

			} else {

				// retorno página de error cuando el registro ya se encuentra en base de datos
				return "errorDuplicadoUsuario";
			}

			return "usuarioAgregado";

		} catch (Exception e) {

			return e.getMessage();
		}
	}
	
	public String crearUsuarioPersonal() {

		// Creo nueva instancia de Rol
		Rol r = new Rol();

		try {

			// Dependiendo de qué rol sea asigno el id para cada rol
			if (getRolNombre().equals("ADMINISTRADOR")) {
				r.setId((long) 1);
			} else if (getRolNombre().equals("USUARIO")) {
				r.setId((long) 2);
			} else {
				r.setId((long) 3);
			}

			/*
			 * El nombre de usuario se crea con :nombre + apellido, vamos a crear una
			 * concatenación de los 2 para asignar el nombre de usuario nuevo
			 */
			// quito espacios y transormo el texto a minuscula
			String getName = getNombre().trim().toLowerCase();

			String getApellido = getApellido().trim().toLowerCase();

			// concateno los 2 valores
			String concat = getName.concat("." + getApellido);

			// y lo igualo
			usuario = concat;

			// Método para controlar CI duplicadas en base de datos
			List<Usuario> lista = UsuarioEJBBean.obtenerCIUsuario(ci);
			
			List <Usuario> lista2 = UsuarioEJBBean.obtenerUserUsuario(usuario);

			// Si la lista está vacia quiere decir que el CI ingresado no está registrado en
			// base de datos aún
			if (lista.isEmpty() && lista2.isEmpty()) {

				UsuarioEJBBean.agregarUsuario(ci, nombre, apellido, genero, contrasena, usuario, email, r,
						FechaNacimiento);
				
				//limpio el formulario luego de insertar registros
				limpiar();

			} else {

				// retorno página de error cuando el registro ya se encuentra en base de datos
				return "errorDuplicadoUsuarioPersonal";
			}

			return "usuarioAgregadoPersonal";

		} catch (Exception e) {

			return e.getMessage();
		}

	}

	public void modificarUsuario() throws Exception{		

		
		//Instancia nueva de Rol
		Rol r = new Rol();
		
		try {
			
			/*
			 * Dependiendo de qué rol sea, asigno el id correspondiente
			 * 
			 * 
			 * 
			 * */
		
			if(user.getID_ROL().getNombre().equals("ADMINISTRADOR")) {
				
				r.setId((long) 1);				
				
				
			} else if(user.getID_ROL().getNombre().equals("USUARIO")) {
				
				r.setId((long) 2);
				
			} else {
				
				r.setId((long) 3);
				
			}
			
			//seteo el rol a la variable
			user.setID_ROL(r);
			
			
			//obtengo nombre
			String getName = user.getNombre().trim().toLowerCase();
			
			//obtengo apellido
			String getApellido = user.getApellido().trim().toLowerCase();
			
			//concateno las 2 variables para formar el nombre de usuario
			String concat = getName.concat("." + getApellido);
						
			//seteo la variable
			user.setUsuario(concat);
			

			//actualizo
			
			UsuarioEJBBean.Actualizar(user);		
				
				RequestContext req = RequestContext.getCurrentInstance();
				req.execute("PF('wdgDatos').hide();");				
				
				
			//
			
			//y refresco la lista de usuarios
			this.listaUsuarios();
			
		} catch (Exception e) {

				throw e;
				
		}
		
		//llamamos al dialogo de actualización
		mostrarDialogoA();
		
	}

	public void borrarUsuario(Long id) throws Exception{

		try {			

			UsuarioEJBBean.borrar(id);
			
			this.listaUsuarios();
			
		} catch (Exception e) {			
			
			throw e;
		}
	}	
	

	public void buscarPorCI(Usuario u) {
		
		try {
			
			Usuario temporal;
			temporal = UsuarioEJBBean.leerid(u.getCi());
			
			// si la variable temporal es distinto de nulo igualamos el usuario al contenido
			// de la misma
			if(temporal != null) {
				this.user = temporal;
			}
			
		}catch(Exception e) {
			
			e.getMessage();		
		}		
	}

	public List<Usuario> listaUsuarios() {

		List<Usuario> lista = null;

		lista = UsuarioEJBBean.obtenerTodosUsuarios();

		return lista;
	}	
	
	//Dialogo que muestra el usuario actualizado
	public void mostrarDialogoA(){
		RequestContext req = RequestContext.getCurrentInstance();
		req.execute("PF('wuActualizado').show();");
	}	
	
	//Metodo usado para limpiar el formulario
	public void limpiar() {
		
		setCi(null);
		setNombre(null);
		setApellido(null);
		setEmail(null);
		setFechaNacimiento(null);	
		setGenero("MASCULINO");
		setRolNombre("ADMINISTRADOR");
	}        
	
	public void enviarEmail() throws Exception{		
	
		
		Usuario s = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");		
		
			
			String host = "smtp.gmail.com";
	        String port = "587";
	        String mailFrom = "desarrollo@gsoftit.com.uy";
	        String password = "e6c1bd145f";	 
	        
	        String mailTo = s.getEmail().trim();
	        String subject = "Hola! "+s.getUsuario();
	        
	        // message contains HTML markups
	        String message = "<i>Coso pum</i><br>";
	        message += "<b>Correo de prueba para IMC (Beta) </b><br>";
	        message += "<font color=red>Gabrielaso</font>";
	        message += "<b>Tu correo electronico es: "+s.getEmail()+"</b>";
	 
	        MailService mail = new MailService();
	 
	        try {
	            mail.sendHtmlEmail(host, port, mailFrom, password, mailTo,
	                    subject, message);
	            System.out.println("E-Mail enviado.");
	            FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Mail enviado a la casilla de correo "+s.getEmail()));           
	            
	            
	        } catch (Exception ex) {
	        	FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "El Email no ha sido enviado"));
	        }       
	}
}
