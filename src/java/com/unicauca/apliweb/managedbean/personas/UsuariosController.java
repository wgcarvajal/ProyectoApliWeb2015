/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.apliweb.managedbean.personas;

import com.unicauca.apliweb.beans.PersonagrupoFacade;
import java.io.IOException;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
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
public class UsuariosController implements Serializable
{
    private String nombreUsuario;
    private String contrasena;
    
    @EJB
    private PersonagrupoFacade personaGrupoEJB;

   
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
        if (personaGrupoEJB.estaUsuario(this.nombreUsuario)) 
        {
            if (req.getUserPrincipal() == null) 
            {

                try 
                {
                    req.login(this.nombreUsuario, this.contrasena);
                    req.getServletContext().log("Autenticacion exitosa");
                    FacesContext.getCurrentInstance().getExternalContext().redirect("/ProyectoApliWeb2015/");

                } catch (ServletException e) 
                {
                    fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nombre de usuario o contraseña incorrectos", "Nombre de usuario o contraseña incorrectos"));
                    requestContext.update("formularioInicioSession");
                }
            } else 
            {
                req.getServletContext().log("El usuario ya estaba logueado:  ");
                requestContext.update("formularioInicioSession");
            }
        }
        else
        {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nombre de usuario o contraseña incorrectos", "Nombre de usuario o contraseña incorrectos"));
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
    
    
    public boolean esusuarioSinSession()
    {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest req = (HttpServletRequest) fc.getExternalContext().getRequest();       
        if (req.getUserPrincipal() == null) 
        {
            return true;
        }
        return false;
    }
    
    
    public boolean esusuarioConSession()
    {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest req = (HttpServletRequest) fc.getExternalContext().getRequest();       
        if (req.getUserPrincipal() == null) 
        {
            return false;
            
        }
       return true;
        
    }
    
    public String nombreUsuario()
    {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest req = (HttpServletRequest) fc.getExternalContext().getRequest();       
        if (req.getUserPrincipal() == null) 
        {
            return "";
        }
        else
        {
            return req.getUserPrincipal().getName();
        }
    }
    
     public void logout() throws IOException, ServletException 
    {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest req = (HttpServletRequest) fc.getExternalContext().getRequest();
        try {
            req.logout();            
            req.getSession().invalidate();
            fc.getExternalContext().invalidateSession();
            FacesContext.getCurrentInstance().getExternalContext().redirect("/ProyectoApliWeb2015/");

        } catch (ServletException e) {            
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "FAILED", "Logout failed on backend"));            
        }
        
    }
     
    public boolean esUsuarioOempleado()
    {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest req = (HttpServletRequest) fc.getExternalContext().getRequest();       
        if (req.getUserPrincipal() == null) 
        {
            return false;            
        }
        else
        {
            String tipo=personaGrupoEJB.buscarPorNombreUsuario(req.getUserPrincipal().getName()).get(0).getPersonagrupoPK().getGruid();
            if(tipo.equals("empl")|| tipo.equals("user"))
            {
                return true;
            }
            return false;            
        }
      
    }
    
    
    public boolean esEmpleado()
    {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest req = (HttpServletRequest) fc.getExternalContext().getRequest();       
        if (req.getUserPrincipal() == null) 
        {
            return false;
            
        }
        else
        {
            String tipo=personaGrupoEJB.buscarPorNombreUsuario(req.getUserPrincipal().getName()).get(0).getPersonagrupoPK().getGruid();
            if(tipo.equals("empl"))
            {
                return true;
            }
            return false;            
        }
      
    }
    
    public void registrarse() throws IOException
    {
        FacesContext fc = FacesContext.getCurrentInstance();
        FacesContext.getCurrentInstance().getExternalContext().redirect("/ProyectoApliWeb2015/faces/Registro.xhtml"); 
    }
    
    public void iniciarSession() throws IOException
    {
        FacesContext fc = FacesContext.getCurrentInstance();
        FacesContext.getCurrentInstance().getExternalContext().redirect("/ProyectoApliWeb2015/"); 
    }
    
    
}
