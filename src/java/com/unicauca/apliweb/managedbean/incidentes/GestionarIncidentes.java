
package com.unicauca.apliweb.managedbean.incidentes;

import com.unicauca.apliweb.beans.CambioFacade;
import com.unicauca.apliweb.beans.IncidenteFacade;
import com.unicauca.apliweb.beans.PersonaFacade;
import com.unicauca.apliweb.beans.RespondeFacade;
import com.unicauca.apliweb.entities.Cambio;
import com.unicauca.apliweb.entities.Incidente;
import com.unicauca.apliweb.entities.Intentosolucion;
import com.unicauca.apliweb.entities.Persona;
import com.unicauca.apliweb.entities.Responde;
import java.io.Serializable;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

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
    private Incidente incidente;    
    
    @EJB
    private RespondeFacade respondeEJB;
    private List<Responde> respuestas;    
    private Intentosolucion solucion;
    
    @EJB
    private PersonaFacade personaEJB;
    
    @EJB
    private CambioFacade cambioEJB;
    private TreeNode root;
    

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
    
    public void actionVerIncidente(Incidente incSeleccionado)
    {        
        this.incidente=incSeleccionado;
        respuestas=respondeEJB.obtnRespuestas(incidente.getIncid());
        RequestContext req=RequestContext.getCurrentInstance();
        req.update("frmDialogVer");
        req.execute("PF('dialogVer').show()");
    }
    public void actionAtenderIncidente(Incidente incSeleccionado)
    {
        RequestContext req=RequestContext.getCurrentInstance();
        if(incidenteEJB.estaSolucionado(incSeleccionado.getIncid()))
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "INCIDENTE YA SOLUCIONADO", "El respectivo usuario ya dio de alta este incidente"));
            this.visibilidadTabla=false;
            req.update("frmTablaIncidentes");
        }
        else
        {
            this.incidente=incSeleccionado;
            solucion=new Intentosolucion();            
            req.update("frmDialogAtender");
            req.execute("PF('dialogAtender').show()");
        }
        
    }
    
    public void actionAplicarSolucion()
    {
        // OJO FALTA LO DE ATIENDE !!
        
        solucion.setIntfecha(new Date());
        // como esto se llama despues de actionAtenderIncidente, se garantiza que incidente ya
        // esta cargado correctamente        
        
        Principal user=FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
        Persona empleado=personaEJB.obtnPersonaPrincipal(user.getName());
        Cambio cambio=new Cambio();
        cambio.setCamfecha(new Date());
        cambio.setCamdescripcion("El empleado "+empleado.getPernombre()+" "+empleado.getPerapellido()+" aplica la solucion: "+solucion.getIntnombre());                        
        
        try
        {
            incidenteEJB.guardarCambios(incidente,cambio,solucion,empleado.getPerid());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Exitoso","Se ha registrado correctamente la Atencion"));
        }
        catch(Exception ex)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error al registrar la atencion", ex.getMessage()));
        }
        RequestContext req=RequestContext.getCurrentInstance();
        req.execute("PF('dialogAtender').hide()");
        
    }
    
    public void actionCargarHistorial(Incidente incidente)
    {        
        System.out.println("Mi MSG: Entrando a CARGARHISTORIAL");
        List<Cambio> cambios;                
        cambios=cambioEJB.obtnCambios(incidente);
        root=new DefaultTreeNode("Historial",null);
        if(cambios.size()>0)
        {            
            TreeNode anterior=root;
            for (Cambio cambio : cambios) {
                TreeNode nodo=new DefaultTreeNode(cambio.getCamfecha(),anterior);
                TreeNode nodoContenido=new DefaultTreeNode(cambio.getCamdescripcion(),nodo);                
                nodo.setExpanded(true);
                anterior=nodo;                
            }
        }
        else
        {
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN, "El Historial esta vacio", "El Historial esta vacio"));
        }
        
        RequestContext req=RequestContext.getCurrentInstance();
        req.update("frmDialogHistorial");
        req.execute("PF('dialogHistorial').show()");
    }
    
    public void actionSalirDialogHistorial()
    {
        RequestContext req=RequestContext.getCurrentInstance();
        req.execute("PF('dialogHistorial').hide()");
    }
    
    public void actionDarDeAlta(Incidente incidente,MostrarIncidentes mostrarIncidentes)
    {
        try
        {
            incidenteEJB.darDeAlta(incidente);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Operacion Exitosa", "El incidente ya se dio de alta"));
            mostrarIncidentes.quitarIncidente(incidente);
        }
        catch(Exception ex)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Hubo un error al intentar dar de alta al incidente"));
        }                    
    }
    
    
    public GestionarIncidentes() 
    {        
    }

    public Intentosolucion getSolucion() {
        return solucion;
    }

    public void setSolucion(Intentosolucion solucion) {
        this.solucion = solucion;
    }
    
    

    public List<Responde> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<Responde> respuestas) {
        this.respuestas = respuestas;
    }        

    public Incidente getIncidente() {
        return incidente;
    }

    public void setIncidente(Incidente incidente) {
        this.incidente = incidente;
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

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
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
