package com.servicios;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.entidades.Imc;
import com.entidades.Usuario;

/**
 * Session Bean implementation class ImcEJB
 */
@Stateless
@LocalBean
public class ImcEJB {

    /**
     * Default constructor. 
     */
    public ImcEJB() {
        // TODO Auto-generated constructor stub
    }
    @PersistenceContext
	private EntityManager em;
    
    public Imc altaIMC (Imc IMC){
    	em.persist(IMC);
    	em.flush();
    	return IMC;
    }
    public List<Imc> historico (Usuario u){
    	TypedQuery<Imc> query = em.createQuery("SELECT i FROM Imc i where USUARIO_ID = :u ORDER BY fecha DESC", Imc.class);
    	
    	query.setParameter("u", u); 
    	
    	return query.getResultList();
    }
    public List<Imc> historicoId (long id){
    	TypedQuery<Imc> query = em.createQuery("SELECT i FROM Imc i where USUARIO_ID.id = :id ORDER BY fecha DESC",Imc.class);
    	query.setParameter("id", id);
    	return query.getResultList();
    }
}


