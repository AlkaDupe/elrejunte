package com.webservices;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.entidades.Imc;
import com.entidades.Usuario;
import com.servicios.ImcEJB;
import com.servicios.UsuarioEJBBean;
import javax.ws.rs.core.MediaType;


import java.util.List;
import java.util.Date;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.POST;


@Path("/usuarios")
public class wsUsuario {
	@EJB
	private UsuarioEJBBean ejb;
	@EJB
	private ImcEJB imc;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Usuario> getUsuarios(){
		
		List<Usuario> lista = null;
		
		lista = ejb.obtenerTodosUsuarios();		
		lista.size();
		return lista;			
	}
	
	
	@GET
	@Path("/login/{ci}/{password}")
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario login(@PathParam("ci") String ci,@PathParam("password")String password){
		Usuario us = ejb.sessionWS(ci, password);
		return us;
	}
	@GET
	@Path("/imc/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Imc> buscar(@PathParam("id")Long id){
		Usuario u = ejb.obtenerIdUsuario(id);
		List<Imc> lista = null;
		lista = imc.historico(u);
		return lista;
		
	}
	@POST
	@Path("/imc/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Imc registrar(@PathParam("id")Long id, Imc imc2){
		Usuario u = ejb.obtenerIdUsuario(id);
		Date hoy = new Date();
		imc2.setFecha(hoy);
		imc2.setUsuario(u);
		imc.altaIMC(imc2);
		return imc2;
		
		
	}
	
}
