package com.beans;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.context.RequestContext;

import com.entidades.Alimento;
import com.servicios.AlimentoEJBBean;

@SuppressWarnings("deprecation")
@ManagedBean(name = "alimentoBean")
@SessionScoped
public class AlimentoBean {

	private Long id;
	private String nombre;
	private double valorPorPorcion;
	private double calorias;
	private double valorEnergetico;
	private double azucar;
	private double cloruroDeSodio;
	private double grasasTrans;
	private double grasasSaturadas;
	private Alimento al;	
	private String idUsuario = "0";
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getValorPorPorcion() {
		return valorPorPorcion;
	}

	public void setValorPorPorcion(double valorPorPorcion) {
		this.valorPorPorcion = valorPorPorcion;
	}

	public double getCalorias() {
		return calorias;
	}

	public void setCalorias(double calorias) {
		this.calorias = calorias;
	}

	public double getValorEnergetico() {
		return valorEnergetico;
	}

	public void setValorEnergetico(double valorEnergetico) {
		this.valorEnergetico = valorEnergetico;
	}

	public double getAzucar() {
		return azucar;
	}

	public void setAzucar(double azucar) {
		this.azucar = azucar;
	}

	public double getCloruroDeSodio() {
		return cloruroDeSodio;
	}

	public void setCloruroDeSodio(double cloruroDeSodio) {
		this.cloruroDeSodio = cloruroDeSodio;
	}

	public double getGrasasTrans() {
		return grasasTrans;
	}

	public void setGrasasTrans(double grasasTrans) {
		this.grasasTrans = grasasTrans;
	}

	public double getGrasasSaturadas() {
		return grasasSaturadas;
	}

	public void setGrasasSaturadas(double grasasSaturadas) {
		this.grasasSaturadas = grasasSaturadas;
	}
	
	public Alimento getAl() {
		return al;
	}

	public void setAl(Alimento al) {
		this.al = al;
	}

	@EJB
	private AlimentoEJBBean alimentoEJBBean;

	public String crearAlimento() {

		try {			
			
			
			//metodo para evitar registro con nombres duplicados en base de datos
			List<Alimento> lista = alimentoEJBBean.obtenerNombreAlimento(nombre.toUpperCase().trim());
			
						
			
			if(lista.isEmpty()) {
				
				alimentoEJBBean.agregarAlimento(nombre.toUpperCase().trim(), valorPorPorcion, calorias, valorEnergetico, azucar, cloruroDeSodio,
						grasasTrans, grasasSaturadas, idUsuario);
				
				limpiar();
				
			}else {
				
				return "errorDuplicadoAlimento";
			}			

			return "mostrar";

		} catch (Exception e) {

			return null;
		}

	}
	
	public String crearAlimentoPersonal() {		
		
		try {
			
			//metodo para evitar registro con nombres duplicados en base de datos
			List<Alimento> lista = alimentoEJBBean.obtenerNombreAlimento(nombre.toUpperCase().trim());
			
			
			if(lista.isEmpty()) {
				
				alimentoEJBBean.agregarAlimento(nombre.toUpperCase().trim(), valorPorPorcion, calorias, valorEnergetico, azucar, cloruroDeSodio,
						grasasTrans, grasasSaturadas, idUsuario);
				
				//Método para limpiar el formulario
				limpiar();
				
			} else {
				
				return "errorDuplicadoAlimentoPersonal";
			}
			

			return "mostrarPersonal";

		} catch (Exception e) {

			return null;
		}

	}
	
public void buscarPorCI(Alimento a) {
		
		try {
			
			Alimento temporal;
			temporal = alimentoEJBBean.leerId(a.getNombre());
			
			// si la variable temporal es distinto de nulo igualamos el usuario al contenido
			// de la misma
			if(temporal != null) {
				this.al = temporal;
			}
			
		}catch(Exception e) {
			
			e.getMessage();		
		}		
	}
	

	//metodo utilizado para modificar un alimento
	public void modificarAlimento() {

		try {
			
			
			//List<Alimento> lista = alimentoEJBBean.obtenerNombreAlimento(al.getNombre().toString());
			
		//	if(lista.get(0).equals(null)) {			
			
				al.setNombre(al.getNombre().toUpperCase());
				
				alimentoEJBBean.Actualizar(al);			
				
				
				//Cierro el cuadro de dialogo luego de actualizar correctamente el alimento
				RequestContext req = RequestContext.getCurrentInstance();
				req.execute("PF('wdgDatosAlimento').hide();");
				
				//Actualizo la lista de alimentos
				this.listaAlimentos();
				
				//Luego muestro el diálogo de actualización
				mostrarDialogoA();
				
			//} else {
				//Dialogo de dato duplicado
			//	mostrarDialogoB();
			
				return;
		//	}		
			
		} catch (Exception e) {

			e.getMessage();
		}		
		
	}
	
	
	public void borrarAlimento(Long id) {

		try {
			
			alimentoEJBBean.borrar(id);				
			
			this.listaAlimentos();
			
		} catch (Exception e) {
			
			e.getMessage();
		}		
	}

	public List<Alimento> listaAlimentos() {

		List<Alimento> lista = alimentoEJBBean.obtenerTodosAlimentos();			
		
		if (lista == null) {
			lista = alimentoEJBBean.obtenerTodosAlimentos();
		}		
		
		return lista;
		
	}
	//Dialogo para mostrar confirmación en la actualización
	public void mostrarDialogoA(){
		RequestContext req = RequestContext.getCurrentInstance();
		req.execute("PF('waActualizado').show();");
	}

	//Dialogo para mostrar dato duplicado 
	public void mostrarDialogoB() {
		RequestContext req = RequestContext.getCurrentInstance();
		req.execute("PF('waDuplicado').show();");
	}
	
	
	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	//Método utilizado para limpiar el formulario y volverlo a los valores por defecto
	public void limpiar() {
		
		setNombre(null);
		setValorPorPorcion(0);
		setCalorias(0);
		setValorEnergetico(0);
		setAzucar(0);
		setCloruroDeSodio(0);
		setGrasasTrans(0);
		setGrasasSaturadas(0);		
		
	}

}
