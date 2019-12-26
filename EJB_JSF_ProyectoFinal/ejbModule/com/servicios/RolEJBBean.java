package com.servicios;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import com.entidades.Rol;

/**
 * Session Bean implementation class RolEJBBean
 */
@Stateless
@LocalBean
public class RolEJBBean {

   
    public RolEJBBean() {
    
    }
    
    @PersistenceContext 
    private EntityManager em;
    
    public Rol agregarRol(String nombre) throws SQLException {
    	
    	Rol rol = new Rol();
    	rol.setNombre(nombre);
    	
    	try {
    		em.persist(rol);
    		em.flush();
    		
    	}catch(PersistenceException e) {
    		e.printStackTrace();
    	}
    	
		return rol;    	
    }
    
    public void Actualizar (Rol rol) throws SQLException{
    	
    	try {
    		em.merge(rol);
    		em.flush();
    	}catch(PersistenceException e) {
    		e.printStackTrace();
    	}
    	
    }
    
    public void borrar(Long id) throws SQLException {

		try {
			Rol rol = em.find(Rol.class, id);
			em.remove(rol);
			em.flush();
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
	}
    
    public List<Rol> obtenerRoles() {
		TypedQuery<Rol> query = em.createQuery("SELECT c FROM Rol c", Rol.class);
		
		return query.getResultList();
	}

}
