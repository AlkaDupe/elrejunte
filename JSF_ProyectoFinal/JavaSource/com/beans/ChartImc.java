package com.beans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import com.entidades.Imc;
import com.entidades.Usuario;
import com.servicios.ImcEJB;

/**
 * Session Bean implementation class ChartImc
 */
@SuppressWarnings("deprecation")
@ManagedBean(name = "chart")
@ViewScoped
public class ChartImc implements Serializable {
	
	String pattern = "dd-MM-yyyy";
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

	private static final long serialVersionUID = 1L;
	

	@EJB
	ImcEJB ejbIMC;

	private LineChartModel linemodel;
	
	LineChartModel model = new LineChartModel();
	LineChartSeries series = new LineChartSeries();
	List<Imc> listado;
	
	public LineChartModel getLinemodel() {
		return linemodel;
	}
	
	@PostConstruct
	public void init(){
		crearModelo();
	}	
	
	private LineChartModel iniciarModeloLineal() {
		
		series.setLabel("IMC");
		Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
		listado =ejbIMC.historicoAscendente(us);
		for(Imc i:listado){
			series.set(i.getFecha().toGMTString(), i.getCalcImc());
			model.addSeries(series);
		}
		
		return model;
	}
	public void crearModelo() {
		linemodel = iniciarModeloLineal();		
		Axis yAxis = linemodel.getAxis(AxisType.Y);
		yAxis.setLabel("IMC");	
		linemodel.setSeriesColors("129D19");			
		DateAxis xAxis = new DateAxis();	
		xAxis.setLabel("Fecha");    
		xAxis.setTickAngle(-50);				
        xAxis.setTickFormat("%b %#d, %y");			
		linemodel.getAxes().put(AxisType.X, xAxis);		
 
		
	}
	
	
	

	

}
