
package com.unicauca.apliweb.managedbean.incidentes;

import com.unicauca.apliweb.beans.IncidenteFacade;
import com.unicauca.apliweb.beans.PersonaFacade;
import com.unicauca.apliweb.entities.Incidente;
import com.unicauca.apliweb.entities.Persona;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.context.RequestContext;

/**
 *
 * @author geovanny
 */
@ManagedBean
@ViewScoped
public class MostrarIncidentes implements Serializable
{
    private List<String> listaTiposdeIncidentes;
    private boolean habilitarTablaIncidentes;
    private boolean habilitarIncidentesTodos;
    private boolean habilitarIncidentesSolucionados;
    private boolean habilitarIncidentesPendientes;
    private List<Incidente> listaIncidentes;
    
    @EJB
    private IncidenteFacade incidenteEJB;
    
    @EJB
    private PersonaFacade personaEJB;
   
    public MostrarIncidentes() 
    {
    }
    
    @PostConstruct
    private void init()
    {
        this.cargarListaTiposIncidentes();
        this.InicializarValores();
    }

    public List<String> getListaTiposdeIncidentes()
    {
        return listaTiposdeIncidentes;
    }

    public void setListaTiposdeIncidentes(List<String> listaTiposdeIncidentes)
    {
        this.listaTiposdeIncidentes = listaTiposdeIncidentes;
    }

    public boolean isHabilitarTablaIncidentes()
    {
        return habilitarTablaIncidentes;
    }

    public void setHabilitarTablaIncidentes(boolean habilitarTablaIncidentes)
    {
        this.habilitarTablaIncidentes = habilitarTablaIncidentes;
    }

    public List<Incidente> getListaIncidentes()
    {
        return listaIncidentes;
    }

    public void setListaIncidentes(List<Incidente> listaIncidentes) 
    {
        this.listaIncidentes = listaIncidentes;
    }

    public boolean isHabilitarIncidentesTodos()
    {
        return habilitarIncidentesTodos;
    }

    public void setHabilitarIncidentesTodos(boolean habilitarIncidentesTodos) 
    {
        this.habilitarIncidentesTodos = habilitarIncidentesTodos;
    }

    public boolean isHabilitarIncidentesSolucionados() 
    {
        return habilitarIncidentesSolucionados;
    }

    public void setHabilitarIncidentesSolucionados(boolean habilitarIncidentesSolucionados) 
    {
        this.habilitarIncidentesSolucionados = habilitarIncidentesSolucionados;
    }

    public boolean isHabilitarIncidentesPendientes() 
    {
        return habilitarIncidentesPendientes;
    }

    public void setHabilitarIncidentesPendientes(boolean habilitarIncidentesPendientes) 
    {
        this.habilitarIncidentesPendientes = habilitarIncidentesPendientes;
    }
    
    
    
    
    

    private void cargarListaTiposIncidentes() 
    {
       this.listaTiposdeIncidentes= new ArrayList();
       this.listaTiposdeIncidentes.add("Todos");
       this.listaTiposdeIncidentes.add("Pendientes");
       this.listaTiposdeIncidentes.add("Solucionados");
    }
    
    public void cambiarTipoIncidente(ValueChangeEvent e)
    {
        String tipo=e.getNewValue().toString();
        this.habilitarTablaIncidentes=false;
        this.habilitarIncidentesPendientes=false;
        this.habilitarIncidentesTodos=false;
        this.habilitarIncidentesSolucionados=false;
        RequestContext requestContext = RequestContext.getCurrentInstance();
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest req = (HttpServletRequest) fc.getExternalContext().getRequest();
        
        Persona persona=personaEJB.buscarNombreUsuario(req.getUserPrincipal().getName());
        System.out.println("id:"+persona.getPerid());
        if(tipo.equals("Todos"))
        {
            this.habilitarIncidentesTodos=true;
            this.habilitarTablaIncidentes=true;
            this.listaIncidentes=this.incidenteEJB.buscarTodos(persona.getPerid());
        }
        if(tipo.equals("Pendientes"))
        {
            this.habilitarIncidentesPendientes=true;
            this.habilitarTablaIncidentes=true;
            this.listaIncidentes=this.incidenteEJB.buscarPorPendientes(persona.getPerid());
        }
        if(tipo.equals("Solucionados"))
        {
            this.habilitarIncidentesSolucionados=true;
            this.habilitarTablaIncidentes=true;
            this.listaIncidentes=this.incidenteEJB.buscarPorSolucionados(persona.getPerid());
        }
        
    } 

    private void InicializarValores() 
    {
      this.habilitarTablaIncidentes=false;
      this.habilitarIncidentesPendientes=false;
      this.habilitarIncidentesTodos=false;
      this.habilitarIncidentesSolucionados=false;
    }
    
    public String convertirPrioridad(int p)
    {
        switch(p)
        {
            case 1:
                return "Baja";
            
            case  2:
                return "Media";
            
            case 3:
                return "Alta";
            default:
                return "";
        }
        
    }
    
    
    
}
