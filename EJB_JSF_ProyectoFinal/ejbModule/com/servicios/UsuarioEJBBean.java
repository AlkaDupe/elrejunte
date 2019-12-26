package com.servicios;

import java.util.Date;
import java.sql.SQLException;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import com.entidades.Rol;
import com.entidades.Usuario;

@Stateless
@LocalBean
public class UsuarioEJBBean {

	public UsuarioEJBBean() {

		super();
	}

	@PersistenceContext
	private EntityManager em;

	public Usuario agregarUsuario(String ci, String nombre, String apellido, String genero, String contrasena,
			String usuario, String email, Rol rol, Date FechaNacimiento) {
		Usuario usuario1 = new Usuario();

		usuario1.setCi(ci);
		usuario1.setNombre(nombre);
		usuario1.setApellido(apellido);
		usuario1.setGenero(genero);
		usuario1.setContrasena(contrasena);
		usuario1.setUsuario(usuario);
		usuario1.setEmail(email);
		usuario1.setID_ROL(rol);
		usuario1.setFechaNacimiento(FechaNacimiento);

		try {

			em.persist(usuario1);
			em.flush();

		} catch (PersistenceException e) {
			e.printStackTrace();
		}
		return usuario1;
	}
	
	
	//Metodo para actualizar usuarios
	public void Actualizar(Usuario usuario) throws SQLException {

		try {
			em.merge(usuario);
			em.flush();
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
	}

	public void borrar(Long id) throws SQLException {

		try {
			Usuario usuario = em.find(Usuario.class, id);
			em.remove(usuario);
			em.flush();
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
	}		
	

	// método para iniciar sesion
	public Usuario iniciarSesion(Usuario us) {
		Usuario usuario = null;

		try {

			TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.usuario = ?1 and u.contrasena = ?2", Usuario.class);

			/*
			 * Seteo los parametros para que el 1 sea igual al usuario y 2 sea igual a al
			 * contraseña en la consulta
			 * 
			 */
			query.setParameter(1, us.getUsuario().trim().toLowerCase());
			query.setParameter(2, us.getContrasena());

			List<Usuario> lista = query.getResultList();

			// Si la lista tiene datos obtengo el primero índice de la lista(el nombre de
			// usuario)
			if (!lista.isEmpty()) {

				usuario = lista.get(0);

			}
		} catch (Exception e) {

			throw e;

		}

		return usuario;

	}
	
	public Usuario iniciarSesionAndroid(Usuario us) {
		Usuario usuario = null;

		try {

			TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.documento = ?1 and u.contrasena = ?2", Usuario.class);

			/*
			 * Seteo los parametros para que el 1 sea igual al usuario y 2 sea igual a al
			 * contraseña en la consulta
			 * 
			 */
			query.setParameter(1, us.getCi());
			query.setParameter(2, us.getContrasena());

			List<Usuario> lista = query.getResultList();

			// Si la lista tiene datos obtengo el primero índice de la lista(el nombre de
			// usuario)
			if (!lista.isEmpty()) {

				usuario = lista.get(0);

			}
		} catch (Exception e) {

			throw e;
		}

		return usuario;

	}		

	public Usuario leerid(String CI) {
		Usuario u = new Usuario();
		TypedQuery<Usuario> query = em.createQuery("SELECT ci FROM Usuario ci where DOCUMENTO = :CI", Usuario.class);
		query.setParameter("CI", CI);
		u = query.getSingleResult();
		return u;
	}

	public List<Usuario> obtenerTodosUsuarios() {
		TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u", Usuario.class);

		return query.getResultList();
	}

	public Usuario obtenerIdUsuario(Long idUsuario) {
		TypedQuery<Usuario> query = em.createQuery("SELECT us FROM Usuario us where ID_USUARIO = :idUsuario",
				Usuario.class);

		query.setParameter("idUsuario", idUsuario);

		return query.getSingleResult();

	}

	/*
	 * Método para obtener algún registro que tenga un CI pasado por parametro,
	 * utilizado para evitar datos duplicados en base de datos, especialmente en el
	 * registro y modificación de usuario
	 */
	public List<Usuario> obtenerCIUsuario(String CI) {
		TypedQuery<Usuario> query = em.createQuery("SELECT ci FROM Usuario ci where DOCUMENTO = :CI", Usuario.class);

		query.setParameter("CI", CI);

		return query.getResultList();
	}
	
	public List<Usuario> obtenerUserUsuario(String USER) {
		TypedQuery<Usuario> query = em.createQuery("SELECT c FROM Usuario c where USUARIO = :USER", Usuario.class);

		query.setParameter("USER", USER);

		return query.getResultList();
	}

	public Usuario sessionWS (String ci, String password) {
		Usuario usuario = null;

		try {

			TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.ci = :ci and u.contrasena = :password", Usuario.class);

			/*
			 * Seteo los parametros para que el 1 sea igual al usuario y 2 sea igual a al
			 * contraseña en la consulta
			 * 
			 */
			query.setParameter("ci", ci);
			query.setParameter("password", password);

			List<Usuario> lista = query.getResultList();

			// Si la lista tiene datos obtengo el primero índice de la lista(el nombre de
			// usuario)
			if (!lista.isEmpty()) {

				usuario = lista.get(0);

			}
		} catch (Exception e) {

			throw e;

		}

		return usuario;

	}
	
}
