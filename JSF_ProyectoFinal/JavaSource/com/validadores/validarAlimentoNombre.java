package com.validadores;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("vnombre")
public class validarAlimentoNombre implements Validator{

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object o) throws ValidatorException {
		
		
		String vnombre = o.toString().trim();
		
		if(vnombre.length() == 0) {
			throw new ValidatorException(new FacesMessage("No puede quedar vacío"));
		}
		
	}

	
	
}
