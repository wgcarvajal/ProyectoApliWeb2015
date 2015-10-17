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
    
    
    
    public void  abrirVentanaVerEditarUsuario(Persona persona,MostrarUsuarios mgb)
    {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.persona=persona;
        this.mgb=mgb;
        requestContext.update("formVerEditarUsuario");
        requestContext.execute("PF('verEditarUsuario').show()"); 
        
    }
    
    
    
}
