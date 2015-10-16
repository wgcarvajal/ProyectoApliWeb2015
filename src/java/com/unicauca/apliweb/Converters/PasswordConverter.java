/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.apliweb.Converters;


import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Miguel
 */
@FacesConverter("passwordConverter")
public class PasswordConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {                
        if(value.length()>6)
        {
            HashGenerator hash=new HashGenerator();
            return hash.generateSHA256(value);
        }
        else
        {   
            /* Si es el componente de repetir contraseña no borrar lo que estaba pa que
                no aparezca el mensaje de error de CONFIRME LA CONTRASEÑA */
            if(!component.getClientId().contains("REP"))          
                context.addMessage(component.getClientId(), new FacesMessage("La longitud minima es de 7"));                        
            return value;                                 
        }
        
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return "";
    }

    
}
