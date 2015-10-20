/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.apliweb.managedbean.personas;


import com.unicauca.apliweb.Converters.HashGenerator;
import com.unicauca.apliweb.beans.GrupoFacade;
import com.unicauca.apliweb.beans.PersonaFacade;
import com.unicauca.apliweb.beans.PersonagrupoFacade;
import com.unicauca.apliweb.entities.Grupo;
import com.unicauca.apliweb.entities.Persona;
import com.unicauca.apliweb.entities.Personagrupo;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;



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
    private String REPpassword;
    private List<Grupo> gruposDisponibles;
    private String idGrupoSeleccionado;
    @EJB
    private GrupoFacade grupoEJB;

    @EJB
    private PersonagrupoFacade personagrupoEJB;    
    
    public String actionRegistrar(){
        FacesContext ctx=FacesContext.getCurrentInstance();
        if(persona.getPerpassword().length()<7)
        {            
            ctx.addMessage("regPersona:password", new FacesMessage("La longitud minima es de 7 caracteres"));
        }
        else
        {
            if(persona.getPerpassword().equals(this.REPpassword)==false)
            {
                ctx.addMessage("regPersona:password", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Las contraseñas NO coinciden", "Las contraseñas NO coinciden"));
            }
            else
            {
                if(personaEJB.existsPerson(persona.getPeruser()))
                    ctx.addMessage("regPersona:user", new FacesMessage("Usuario ya existe"));
                else
                {                    
                    try
                    {   
                        HashGenerator hash=new HashGenerator();                        
                        persona.setPerpassword(hash.generateSHA256(REPpassword));
                        personaEJB.registrar(persona);
                        Principal userRol=ctx.getExternalContext().getUserPrincipal();
                        
                        if(userRol!=null)
                        {
                            String tipo;
                            tipo = personagrupoEJB.buscarPorNombreUsuario(userRol.getName()).get(0).getPersonagrupoPK().getGruid();
                            if (tipo.equals("admin"))
                                personagrupoEJB.registrar(new Personagrupo(idGrupoSeleccionado, persona.getPerid(),persona.getPeruser()));                            
                            
                        }                                                    
                        ctx.addMessage(null, new FacesMessage("Registro Exitoso"));
                        FacesContext.getCurrentInstance().getExternalContext().redirect("Bienvenida.xhtml");
                        
                    }
                    catch(Exception ex)
                    {            
                        ctx.addMessage(null, new FacesMessage("Hubo un error"));                            
                    }  
                            
                }
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

    public String getREPpassword() {
        return REPpassword;
    }

    public void setREPpassword(String REPpassword) {
        this.REPpassword = REPpassword;
    }
    
    public void registrarse() throws IOException
    {
        FacesContext fc = FacesContext.getCurrentInstance();
        FacesContext.getCurrentInstance().getExternalContext().redirect("/ProyectoApliWeb2015/faces/Registro.xhtml"); 
    }
    
    public void registrarUsuario() throws IOException
    {
        FacesContext fc = FacesContext.getCurrentInstance();
        FacesContext.getCurrentInstance().getExternalContext().redirect("/ProyectoApliWeb2015/faces/administrador/Registro.xhtml"); 
    }
    
}