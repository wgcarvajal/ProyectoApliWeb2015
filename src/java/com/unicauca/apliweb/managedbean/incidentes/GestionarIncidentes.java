
package com.unicauca.apliweb.managedbean.incidentes;

import com.unicauca.apliweb.beans.IncidenteFacade;
import com.unicauca.apliweb.beans.PersonaFacade;
import com.unicauca.apliweb.entities.Incidente;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author geovanny
 */
@ManagedBean
@ViewScoped
public class GestionarIncidentes implements Serializable
{
    private List<String> estadosIncidentes;
    private boolean visibilidadTabla;
    private List<Incidente> listaIncidentes;        
    
    @EJB
    private IncidenteFacade incidenteEJB;
    
    @EJB
    private PersonaFacade personaEJB;

    @PostConstruct
    private void init()
    {        
        this.InicializarValores();
    }
    
    
    public void eventoCambioEstado(ValueChangeEvent event)
    {           
        String estado=(String) event.getNewValue();
        
        if(this.visibilidadTabla=(estado.equals("")==false))
        {
            try
            {
                this.listaIncidentes=incidenteEJB.buscarIncidentes(estado);                    
            }
            catch(Exception ex)
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Hubo un error al cargar los incidentes", ex.getMessage()));
            }
        }
        else        
            System.out.println("Mi msg: EFECTIVAMENTE!!!");        
        
        // la actualizacion ya la hace el componente <p:selectOneMenu  id="estadoIncidente"
        // con <p:ajax update=":frmTablaIncidentes"/>
    }
    
    
    
    public GestionarIncidentes() 
    {        
    }

    public List<String> getEstadosIncidentes() {
        return estadosIncidentes;
    }

    public void setEstadosIncidentes(List<String> estadosIncidentes) {
        this.estadosIncidentes = estadosIncidentes;
    }

    
    public boolean isVisibilidadTabla() {
        return visibilidadTabla;
    }

    public void setVisibilidadTabla(boolean visibilidadTabla) {
        this.visibilidadTabla = visibilidadTabla;
    }
                   
    public List<Incidente> getListaIncidentes()
    {
        return listaIncidentes;
    }

    public void setListaIncidentes(List<Incidente> listaIncidentes) 
    {
        this.listaIncidentes = listaIncidentes;
    }

    
    private void InicializarValores() 
    {
       this.estadosIncidentes= new ArrayList();
       this.estadosIncidentes.add("Pendiente");
       this.estadosIncidentes.add("Solucionado");
       this.estadosIncidentes.add("Todos");
       
       
       this.visibilidadTabla=false;
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
