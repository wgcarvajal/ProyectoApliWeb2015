package com.unicauca.apliweb.managedbean.personas;

import com.unicauca.apliweb.entities.Persona;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author geovanny
 */ 
@ManagedBean
@ViewScoped
public class VerEditarPersona implements Serializable
{
    private MostrarUsuarios mgb;
    private Persona persona;
    private String tipo;
    private boolean habilitarNombres;
    private boolean habilitarApellidos;
    private boolean habilitarTipoPersona;
    private boolean habilitarContrasena;
    private boolean habilitarNombreUsuario;
    
    

    public VerEditarPersona() 
    {
       persona=new Persona();
    }

    public Persona getPersona()
    {
        return persona;
    }

    public void setPersona(Persona persona) 
    {
        this.persona = persona;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public void  abrirVentanaVerEditarUsuario(Persona persona,MostrarUsuarios mgb)
    {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.persona=persona;
        this.mgb=mgb;
        tipo="";
        if(this.persona.getPersonagrupoList().get(0).getGrupo().getGruid().equals("admin"))
        {
            tipo="Administrador";
        }
        if(this.persona.getPersonagrupoList().get(0).getGrupo().getGruid().equals("empl"))
        {
            tipo="Empleado";
        }
        if(this.persona.getPersonagrupoList().get(0).getGrupo().getGruid().equals("user"))
        {
            tipo="Usuario";
        }
        requestContext.update("formVerEditarUsuario");
        requestContext.execute("PF('verEditarUsuario').show()"); 
        
    }
    
    
    
}
