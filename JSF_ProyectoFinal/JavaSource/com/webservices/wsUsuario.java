package com.webservices;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import com.entidades.Usuario;
import com.servicios.UsuarioEJBBean;
import javax.ws.rs.core.MediaType;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.GET;


@Path("/usuarios")
public class wsUsuario {
	
	@EJB
	private UsuarioEJBBean ejb;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Usuario> getUsuarios(){
		
		List<Usuario> lista = ejb.obtenerTodosUsuarios();		
		
		return lista;			
	}
	
	
	@GET
	@Path("/login/{ci}/{password}")
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario login(@PathParam("ci") String ci,@PathParam("password")String password){
		Usuario us = ejb.sessionWS(ci, password);
		return us;
	}
}
