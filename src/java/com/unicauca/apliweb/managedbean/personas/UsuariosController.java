/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.apliweb.managedbean.personas;

import java.io.IOException;
import javax.faces.application.Application;
import javax.faces.application.ViewHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.context.RequestContext;

/**
 *
 * @author geovanny
 */
@ManagedBean
@SessionScoped
public class UsuariosController
{
    private String nombreUsuario;
    private String contrasena;

   
    public UsuariosController() 
    {
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) 
    {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() 
    {
        return contrasena;
    }

    public void setContrasena(String contrasena)
    {
        this.contrasena = contrasena;
    }
    
    
     public void login()throws IOException, ServletException 
    {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest req = (HttpServletRequest) fc.getExternalContext().getRequest();        
        if (req.getUserPrincipal() == null) {
            try 
            {
                req.login(this.nombreUsuario, this.contrasena);
                req.getServletContext().log("Autenticacion exitosa");
                
            } 
            catch (ServletException e) 
            {
                //fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nombre de usuario o contraseña incorrectos", "Nombre de usuario o contraseña incorrectos"));
                requestContext.update("formularioInicioSession");                
            }
        } 
        else 
        {
            req.getServletContext().log("El usuario ya estaba logueado:  ");
            requestContext.update("formularioInicioSession");
        }
    }
    
    
    public void ventanaInicioSession()
    {
       RequestContext requestContext = RequestContext.getCurrentInstance();          
       FacesContext context = FacesContext.getCurrentInstance();
       Application application = context.getApplication();
       ViewHandler viewHandler = application.getViewHandler();
       UIViewRoot viewRoot = viewHandler.createView(context, context.getViewRoot().getViewId());
       context.setViewRoot(viewRoot);       
       context.renderResponse();
       this.contrasena=null;
       this.nombreUsuario=null;
       requestContext.update("formularioInicioSession");       
       requestContext.execute("PF('IniciarSesion').show()");
    }
    
    
    
}
