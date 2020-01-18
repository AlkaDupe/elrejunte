package com.beans;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.primefaces.context.RequestContext;

import com.entidades.Alimento;
import com.entidades.Imc;
import com.entidades.Usuario;
import com.servicios.AlimentoEJBBean;
import com.servicios.ImcEJB;
import com.servicios.UsuarioEJBBean;

@SuppressWarnings("deprecation")
@ManagedBean(name = "imcBean")
@SessionScoped
public class IMCBean {

	private long id;

	private float altura;

	private float peso;

	private Date fecha;

	private float calcImc;
	
	private List<Imc> listaPromedioIMC;
	


	@EJB
	ImcEJB ejbIMC;
	UsuarioEJBBean ejbUser;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public float getAltura() {
		return altura;
	}

	public void setAltura(float altura) {
		this.altura = altura;
	}

	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public float getCalcImc() {
		return calcImc;
	}

	public void setCalcImc(float calcImc) {
		this.calcImc = calcImc;
	}

	public List<Imc> getListaPromedioIMC() {
		return listaPromedioIMC;
	}

	public void setListaPromedioIMC(List<Imc> listaPromedioIMC) {
		this.listaPromedioIMC = listaPromedioIMC;
	}

	public String IngresoIMC() {

		String retorno = "";

		Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");

		Imc Imc = new Imc();

		Imc.setAltura(altura);

		Imc.setPeso(peso);

		fecha = new Date();

		Imc.setFecha(fecha);

		Imc.setCalcImc(peso / (altura * altura));

		Imc.setUsuario(us);

		try {

			if (peso <= 10.00) {

				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error Datos", "El peso mínimo es de 10 kilos"));

			} else if (altura <= 0.60) {

				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,
						"Error Datos", "La altura mínima es de 0.60 metros"));

			} else if (peso >= 300.00){
				
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,
						"Error Datos", "El peso no puede superar los 300 kilos"));
				
			}else if(altura >= 3.00) {
				
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,
						"Error Datos", "La aultura no puede supear los 3 metros"));			
				
			}else {					

				ejbIMC.altaIMC(Imc);

				retorno = "mostrarIMC";

				clean();

			}

		} catch (Exception e) {

			e.getMessage();

			retorno = "errorIngresoIMC";

			clean();

		}
		return retorno;
	}
	
	
	public List<Imc> listarIMC() {
		
		Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
		
		List<Imc> lista = ejbIMC.historico(us);	
		
		return lista;
		
	}
	
	
	
	public String estadoSalud (){
        float label = 0;
        
        List<Imc> list = listarIMC();
        
        String ret = "";
        
        if(list == null) {
        	
        	ret = "Ups! no hay registros por aquí. :(";
        }        

        if(list.size()>0){
            label = list.get(0).getCalcImc();

            if(label < 18.444){
            	ret = "Estado: Peso bajo, ponderal";
            }
            if(label >=18.555 && label <=24.999){
            	ret= "Estado: Peso Normal";
            }
            if(label >=25 && label <=29.999){
            	ret= "Estado: Sobrepeso";
            }
            if(label >=30 && label <=34.999){
            	ret= "Estado: Obesidad I";
            }
            if(label >=35 && label <=39.999){
            	ret= "Estado: Obesidad II";
            }
            if(label>=40){
            	ret = "Estado: Obesidad III";
            }           
           
        }
        else{
        	
        	ret = "Ups! no hay registros por aquí. :(";
        }        
        
        return ret;
    }
	

	public void clean() {

		setAltura(0);
		setPeso(0);
	}

}
