
package com.unicauca.apliweb.managedbean.personas;

import com.unicauca.apliweb.beans.PersonaFacade;
import com.unicauca.apliweb.beans.PersonagrupoFacade;
import com.unicauca.apliweb.entities.Persona;
import com.unicauca.apliweb.entities.Personagrupo;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import org.primefaces.context.RequestContext;

/**
 *
 * @author geovanny
 */
@ManagedBean
@ViewScoped
public class MostrarUsuariosPendientes implements Serializable 
{
    @EJB
    private PersonaFacade personaEJB;
    @EJB
    private PersonagrupoFacade personagrupoEJB;
    private List<Persona> listaPendientes;   
    private String idGrupoSeleccionado;
    private Persona persona;
               
    @PostConstruct
    private void init()
    {
        listaPendientes=personaEJB.buscarPendientes();
        this.idGrupoSeleccionado="";
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
    

    public String getIdGrupoSeleccionado() {
        return idGrupoSeleccionado;
    }

    public void setIdGrupoSeleccionado(String idGrupoSeleccionado) {
        this.idGrupoSeleccionado = idGrupoSeleccionado;
    }
    
    

    public List<Persona> getListaPendientes() {
        return listaPendientes;
    }

    public void setListaPendientes(List<Persona> listaPendientes) {
        this.listaPendientes = listaPendientes;
    }
    
    public void mostrarEmergente(Persona persona)
    {
        this.persona=persona;
        RequestContext requestContext = RequestContext.getCurrentInstance();       
        requestContext.update("frmHabilitar");
        requestContext.execute("PF('habilitarUsuario').show()");        
    }
    
    public void habilitarUsuario()
    {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("PF('habilitarUsuario').hide()");
        try 
        {
            personagrupoEJB.registrar(new Personagrupo(idGrupoSeleccionado, persona.getPerid(),persona.getPeruser()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Registro Exitoso","Registro Exitoso"));
            listaPendientes.remove(persona);            
            requestContext.update("tablasUsuariosPendientes");

        }
        catch(Exception ex)
        {
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, ex.getMessage(), ex.getMessage()));
        }                        
        
    }   
    
}
