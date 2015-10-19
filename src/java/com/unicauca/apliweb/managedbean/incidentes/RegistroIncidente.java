/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.apliweb.managedbean.incidentes;

import com.unicauca.apliweb.beans.CategoriaFacade;
import com.unicauca.apliweb.beans.IncidenteFacade;
import com.unicauca.apliweb.beans.PersonaFacade;
import com.unicauca.apliweb.beans.RespondeFacade;
import com.unicauca.apliweb.entities.Categoria;
import com.unicauca.apliweb.entities.Incidente;
import com.unicauca.apliweb.entities.Preguntas;
import java.io.Serializable;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author miguel
 */
@ManagedBean
@ViewScoped
public class RegistroIncidente implements Serializable{
    
    @EJB
    private PersonaFacade personaEJB;
    @EJB
    private CategoriaFacade categoriaEJB;
    @EJB
    private IncidenteFacade incidenteEJB;
    private Incidente incidente;
    private List<Categoria> categoriasDisponibles;    
    private int idCatSeleccionada;
    private String[] prioridades={"Alta","Media","Baja"};
    private String prioridadSeleccionada;
    
    //Para las respuestas    
    @EJB
    private RespondeFacade respondeEJB;
    private List<Preguntas> preguntas;
    private Preguntas preguntaActual;
    private int iterador;
    private String respuesta;    

    
    public void actionRegistrar()
    {
        this.incidente.setIncnivel(convertirPrioridad(prioridadSeleccionada));
        Principal user=FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
        this.incidente.setPersona(personaEJB.obtnPersonaPrincipal(user.getName()));
        this.incidente.setCategoria(categoriaEJB.obtnCategoria(idCatSeleccionada));
        this.incidente.setIncsolucionado(false);
        this.incidente.setIncfecharegistro(new Date());
        RequestContext req=RequestContext.getCurrentInstance();
        try
        {
            incidenteEJB.registrar(this.incidente);             
        }
        catch(Exception ex)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Error en el registro",ex.getMessage()));                
            // se interrumpe la ejecucion de la funcion
            return;
        }
        
        preguntas=incidente.getCategoria().getPreguntasList();
        if(preguntas.size()>0)
        {
            preguntaActual=preguntas.get(iterador);
            iterador++;
            req.execute("PF('emergCuestionario').show()");            
        }
        
    }
    
    public void actionGuardarRespuesta()
    {                
        try
        {
            respondeEJB.guardarRespuesta(this.incidente.getIncid(),this.incidente.getPersona().getPerid(),this.preguntaActual.getPreid(),respuesta);
        }
        catch(Exception ex)
        {                     
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Respuesta ya existe", "Usted ya respondio esta pregunta. Se omitira la respuesta que usted acabo de ingresar"));                        
        }                
        
        RequestContext req=RequestContext.getCurrentInstance();
        if(iterador<preguntas.size())
        {
            preguntaActual=preguntas.get(iterador);
            iterador++;
            
            req.update("frmCuestionario");
            req.execute("PF('emergCuestionario').show()");            
        }
        else
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Registro Exitoso","El incidente se ha registrado correctamente"));                
            incidente=new Incidente();
            idCatSeleccionada=0;
            prioridadSeleccionada="";                
            iterador=0;
            req.update("frmRegIncidente");
            req.execute("PF('emergCuestionario').hide()");            
        }
    }       
    
    @PostConstruct
    public void init()
    {
        categoriasDisponibles=categoriaEJB.obtnCategorias();
        incidente=new Incidente();       
        iterador=0;
    } 

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }        
    
    public int convertirPrioridad(String prioridad)
    {
        switch(prioridad)
        {
            case "Alta":
                return 3;
            case "Media":
                return 2;
            case "Baja":
                return 1;
            default:
                return 0;                                                    
        }
    }    

    public Preguntas getPreguntaActual() {
        return preguntaActual;
    }

    public void setPreguntaActual(Preguntas preguntaActual) {
        this.preguntaActual = preguntaActual;
    }   
    
    public int getIdCatSeleccionada() {
        return idCatSeleccionada;
    }

    public void setIdCatSeleccionada(int idCatSeleccionada) {
        this.idCatSeleccionada = idCatSeleccionada;
    }

    
    public String getPrioridadSeleccionada() {
        return prioridadSeleccionada;
    }

    public void setPrioridadSeleccionada(String prioridadSeleccionada) {
        this.prioridadSeleccionada = prioridadSeleccionada;
    }
    
    
    
    public String[] getPrioridades() {
        return prioridades;
    }

    public void setPrioridades(String[] prioridades) {
        this.prioridades = prioridades;
    }            
    
    public Incidente getIncidente() {
        return incidente;
    }

    public void setIncidente(Incidente incidente) {
        this.incidente = incidente;
    }

    public List<Categoria> getCategoriasDisponibles() {
        return categoriasDisponibles;
    }

    public void setCategoriasDisponibles(List<Categoria> categoriasDisponibles) {
        this.categoriasDisponibles = categoriasDisponibles;
    }
                        
    public RegistroIncidente() {                
    }
                       
}
