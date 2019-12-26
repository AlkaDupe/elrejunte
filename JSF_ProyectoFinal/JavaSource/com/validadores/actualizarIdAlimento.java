package com.validadores;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


@FacesValidator("vIdAlimentoActulizar")
public class actualizarIdAlimento implements Validator {

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2) throws ValidatorException {
		
String idAlimento = arg2.toString().trim();
		
		if(idAlimento.isEmpty()) {
			throw new ValidatorException(new FacesMessage("Alimento no existe en base de datos"));
		}
		
	}

	
	
	
}
