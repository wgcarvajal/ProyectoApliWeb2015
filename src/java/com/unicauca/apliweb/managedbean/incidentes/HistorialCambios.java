/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.apliweb.managedbean.incidentes;

import com.unicauca.apliweb.beans.CambioFacade;
import com.unicauca.apliweb.beans.CategoriaFacade;
import com.unicauca.apliweb.beans.IncidenteFacade;
import com.unicauca.apliweb.beans.PersonaFacade;
import com.unicauca.apliweb.beans.RespondeFacade;
import com.unicauca.apliweb.entities.Cambio;
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
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author miguel
 */
@ManagedBean
@ViewScoped
public class HistorialCambios implements Serializable{
    

    private TreeNode root;
    
    @EJB
    private CambioFacade cambioEJB;
    
        
    public void actionCargarHistorial(Incidente incidente)
    {
        System.out.println("Mi MSG: Entrando a CARGARHISTORIAL");
        List<Cambio> cambios;
        //OJO.. TIENEN QUE ESTAR ORDENADOS DE MAS ANTIGUO A MAS RECIENTE
        
        cambios=cambioEJB.obtnCambios(incidente);
        
        root=new DefaultTreeNode("Historial",null);
        TreeNode anterior=root;
        for (Cambio cambio : cambios) {
            TreeNode nodo=new DefaultTreeNode(cambio.getCamfecha(),anterior);
            TreeNode nodoContenido=new DefaultTreeNode(cambio.getCamdescripcion(),nodo);
            
            anterior=nodo;
        }                
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }
    
    
    
    public HistorialCambios() {                
        
    }
                       
}
