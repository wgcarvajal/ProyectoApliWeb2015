/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.apliweb.managedbean.personas;


import com.unicauca.apliweb.beans.GrupoFacade;
import com.unicauca.apliweb.beans.PersonaFacade;
import com.unicauca.apliweb.beans.PersonagrupoFacade;
import com.unicauca.apliweb.entities.Grupo;
import com.unicauca.apliweb.entities.Persona;
import com.unicauca.apliweb.entities.Personagrupo;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;



/**
 *
 * @author Miguel
 */
@ManagedBean
@RequestScoped
public class RegistroController{

    @EJB
    private PersonaFacade personaEJB;
    private Persona persona;
    private List<Grupo> gruposDisponibles;
    private String idGrupoSeleccionado;
    @EJB
    private GrupoFacade grupoEJB;

    @EJB
    private PersonagrupoFacade personagrupoEJB;
    
    public String actionRegistrar(){
        FacesContext ctx=FacesContext.getCurrentInstance();
        if(personaEJB.existsPerson(persona.getPeruser()))
            ctx.addMessage("regPersona:user", new FacesMessage("Usuario ya existe"));
        else
        {
            try
            {   
                personaEJB.registrar(persona);
                personagrupoEJB.registrar(new Personagrupo(idGrupoSeleccionado, persona.getPerid(),persona.getPeruser()));                            
                ctx.addMessage(null, new FacesMessage("Registro Exitoso"));            
            }
            catch(Exception ex)
            {            
                ctx.addMessage(null, new FacesMessage(ex.getMessage()));                            
            }  
        }        
        return null;
    }
                
    public RegistroController() 
    {
        persona= new Persona();                
    }
    
    public List<Grupo> obtnGrupos()
    {
        gruposDisponibles=grupoEJB.obtnGrupos();
        return gruposDisponibles;
    }
    
    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }        

    public List<Grupo> getGruposDisponibles() {
        return gruposDisponibles;
    }

    public void setGruposDisponibles(List<Grupo> gruposDisponibles) {
        this.gruposDisponibles = gruposDisponibles;
    }

    public String getIdGrupoSeleccionado() {
        return idGrupoSeleccionado;
    }

    public void setIdGrupoSeleccionado(String idGrupoSeleccionado) {
        this.idGrupoSeleccionado = idGrupoSeleccionado;
    }     
}