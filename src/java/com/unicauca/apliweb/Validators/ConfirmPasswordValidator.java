/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.apliweb.Validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Miguel
 */
@FacesValidator("confirmPasswordValidator")
public class ConfirmPasswordValidator implements Validator{

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        
        try
        {
            UIInput InputPassword=(UIInput) context.getViewRoot().findComponent("regPersona:password");
            if(!InputPassword.getValue().equals(value.toString()))
            {
                throw new ValidatorException(new FacesMessage("Las contraseñas NO coinciden"));
            }
        }
        catch(Exception ex)
        {
            throw new ValidatorException(new FacesMessage(ex.getMessage()));
        }
    }
    
}
