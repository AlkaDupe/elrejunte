package com.servicios;


import java.sql.SQLException;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import com.entidades.Alimento;

/**
 * Session Bean implementation class AlimentoBean
 */
@Stateless
@LocalBean
public class AlimentoEJBBean {	
	
    public AlimentoEJBBean() {
    	
    	super();
    }
    
    @PersistenceContext
	private EntityManager em;
	
    public Alimento agregarAlimento(String nombre, double valorPorPorcion, double calorias, double valorEnergetico, double azucar, double cloruroDeSodio, double grasasTrans, double grasasSaturadas, String idUsuario) {
    	Alimento alimento = new Alimento();
    	
    	alimento.setNombre(nombre);
    	alimento.setValorPorPorcion(valorPorPorcion);
    	alimento.setCalorias(calorias);
    	alimento.setValorEnergetico(valorEnergetico);
    	alimento.setAzucar(azucar);
    	alimento.setCloruroDeSodio(cloruroDeSodio);
    	alimento.setGrasasTrans(grasasTrans);
    	alimento.setGrasasSaturadas(grasasSaturadas);
    	alimento.setIdUsuario(idUsuario);
    	
    	try {    		
    		
    	em.persist(alimento);    	
    	em.flush();    	   	    
		
    	}catch (PersistenceException e) {
    		e.printStackTrace();
		}
    	return alimento;
    }
    
    //Metodo utilizado en e
    public Alimento agregarAlimentoWS(Alimento al) {
    	
    	try {
    		em.persist(al);
    		em.flush();
    	}catch(PersistenceException e) {
    		e.printStackTrace();
    	}
    	
    	return al;
    	
    }
    
    
    public void Actualizar(Alimento alimento) throws SQLException {
    	    	    	
		try {
			em.merge(alimento);
			em.flush();
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
	}
    
    public void borrar(Long id) throws SQLException {

		try {
			Alimento alimento = em.find(Alimento.class, id);
			em.remove(alimento);
			em.flush();
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
	}
    
    
    //Método utilizado para modificar un alimento
    public Alimento leerId(String NOMBRE) {
    	Alimento a = new Alimento();
    	TypedQuery<Alimento> query = em.createQuery("SELECT n FROM Alimento n where NOMBRE = :NOMBRE", Alimento.class);
    	
    	query.setParameter("NOMBRE", NOMBRE);
    	
    	a = query.getSingleResult();
    	
    	return a;
    	
    }
    
    //lista utilizada para mostrar todos los datos de la tabla Alimento 
    public List<Alimento> obtenerTodosAlimentos() {
		TypedQuery<Alimento> query = em.createQuery("SELECT a FROM Alimento a", Alimento.class);
		
		return query.getResultList();
	}
    
    
    public Alimento obtenerIdAlimento(Long idAlimento){
    	TypedQuery<Alimento> query = em.createQuery("SELECT al FROM Alimento al where ID_ALIMENTO = :idAlimento", Alimento.class);
    	
    	query.setParameter("idAlimento", idAlimento);    	
    	
		return query.getSingleResult();
    }
    
    public List<Alimento> obtenerPorUsuario (String id_usuario){
    	TypedQuery<Alimento> query = em.createQuery("SELECT a FROM Alimento a WHERE idUsuario =:id_usuario", Alimento.class);
    	query.setParameter("id_usuario", id_usuario);
    	return query.getResultList();
    }
    
    public List<Alimento> obtenerNombreAlimento(String N) {
		TypedQuery<Alimento> query = em.createQuery("SELECT n FROM Alimento n where NOMBRE = :N", Alimento.class);

		query.setParameter("N", N);

		return query.getResultList();
	}
    
    
    
    
        
}
