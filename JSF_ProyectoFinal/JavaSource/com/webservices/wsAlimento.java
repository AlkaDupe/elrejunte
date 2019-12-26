package com.webservices;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.entidades.Alimento;
import com.servicios.AlimentoEJBBean;
import javax.ws.rs.core.MediaType;

import java.sql.SQLException;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
@Path("/data")
public class wsAlimento {
	
	@EJB
	private AlimentoEJBBean alimentoEJBBean;
	
	
	//Listar Alimentos
	@GET	
	@Produces(MediaType.APPLICATION_JSON)	
	public List<Alimento> getAlimentos(){
		
		return alimentoEJBBean.obtenerTodosAlimentos();		
		
	}	
	
	//Agregar Alimentos
	@POST	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)				
	public Alimento agregarAlimento(Alimento al) {
		
		try {
			
			alimentoEJBBean.agregarAlimentoWS(al);
			
		} catch(Exception e) {
		
			e.printStackTrace();
		}
		
		return al;
	}
	
	
	//Actualizar Alimentos
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)	
	public Alimento modificarAlimento(Alimento al) {
		
		try {
			
			alimentoEJBBean.Actualizar(al);
			
		} catch(Exception e) {
			
			e.printStackTrace();
			
		}
		
		return al;
	}	
	
	//para borrar le pasamos por la URL el id que queremos borrar
	@DELETE
	@Path("/borrar/{id}")
	@Produces(MediaType.APPLICATION_JSON)	
	public void borrarAlimento(@PathParam("id") Long id) throws SQLException {		
		
		try {			
			
			alimentoEJBBean.borrar(id);			
			
		}catch(Exception e) {
			
			e.printStackTrace();
			
		}		
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Alimento> obtenerPorU(@PathParam("id")String id_usuario){
	return alimentoEJBBean.obtenerPorUsuario(id_usuario);
	}
}
