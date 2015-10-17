
package com.unicauca.apliweb.managedbean.personas;

import com.unicauca.apliweb.beans.PersonaFacade;
import com.unicauca.apliweb.entities.Persona;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author geovanny
 */
@ManagedBean
@ViewScoped
public class MostrarUsuarios implements Serializable 
{
    @EJB
    private PersonaFacade personaEJB;
    
    private List<String> listaTiposdePersonas;
    private List<Persona> listaPersonas; 
    private boolean habilitarAdministradores;    
    private boolean habilitarEmpleados;
    private boolean habilitarUsuarios;
    private boolean habilitarTablaPersonas;
    
    private String nombrePersona;   

    
    public MostrarUsuarios() 
    {       
    } 
    
      
    @PostConstruct
    private void init()
    {
        this.cargarListaTiposPersonas();
        this.InicializarValores();
    }  

    public List<String> getListaTiposdePersonas() {
        return listaTiposdePersonas;
    }

    public void setListaTiposdePersonas(List<String> listaTiposdePersonas) {
        this.listaTiposdePersonas = listaTiposdePersonas;
    }

    public List<Persona> getListaPersonas() {
        return listaPersonas;
    }

    public void setListaPersonas(List<Persona> listaPersonas) {
        this.listaPersonas = listaPersonas;
    }

    public boolean isHabilitarAdministradores() {
        return habilitarAdministradores;
    }

    public void setHabilitarAdministradores(boolean habilitarAdministradores) {
        this.habilitarAdministradores = habilitarAdministradores;
    }

    public boolean isHabilitarEmpleados() {
        return habilitarEmpleados;
    }

    public void setHabilitarEmpleados(boolean habilitarEmpleados) {
        this.habilitarEmpleados = habilitarEmpleados;
    }

    public boolean isHabilitarUsuarios() {
        return habilitarUsuarios;
    }

    public void setHabilitarUsuarios(boolean habilitarUsuarios) {
        this.habilitarUsuarios = habilitarUsuarios;
    }

    public boolean isHabilitarTablaPersonas() {
        return habilitarTablaPersonas;
    }

    public void setHabilitarTablaPersonas(boolean habilitarTablaPersonas) {
        this.habilitarTablaPersonas = habilitarTablaPersonas;
    }

    public String getNombrePersona() {
        return nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }
    
    
     private void cargarListaTiposPersonas()
    {
        this.listaTiposdePersonas=new ArrayList();
        this.listaTiposdePersonas.add("Administradores");
        this.listaTiposdePersonas.add("Empleados");
        this.listaTiposdePersonas.add("Usuarios");
    }
    
    private void InicializarValores()
    {
        this.habilitarAdministradores=false;    
        this.habilitarEmpleados=false;
        this.habilitarUsuarios=false;
        this.habilitarTablaPersonas=false;
       
    }
    
    public void cambiarTipoUsuario(ValueChangeEvent e)
    {
        String tipo=e.getNewValue().toString();
        this.habilitarAdministradores=false;
        this.habilitarEmpleados=false;
        this.habilitarUsuarios=false;
        this.habilitarTablaPersonas=false;
        this.nombrePersona=null;
        if(tipo.equals("Administradores"))
        {
            this.habilitarAdministradores=true;
            this.habilitarTablaPersonas=true;
            this.listaPersonas=this.personaEJB.buscarPorAdministradores();
        }
        if(tipo.equals("Empleados"))
        {
            this.habilitarEmpleados=true;
            this.habilitarTablaPersonas=true;
            this.listaPersonas=this.personaEJB.buscarPorEmpleados();
        }
        if(tipo.equals("Usuarios"))
        {
            this.habilitarUsuarios=true;
            this.habilitarTablaPersonas=true;
            this.listaPersonas=this.personaEJB.buscarPorUsuarios();
        }
    }    
       
    public void buscarPorNombrePersona()
    {
        
        if(this.habilitarAdministradores==true)
        {
            System.out.println("nombre:"+this.nombrePersona);
            this.listaPersonas=personaEJB.buscarPorNombreAdministrador(this.nombrePersona);
        }
        else
        {
            if(this.habilitarEmpleados==true)
            {
                this.listaPersonas=personaEJB.busacarPorNombreEmpleado(this.nombrePersona);
 
            }
            else
            {
                this.listaPersonas=personaEJB.busacarPorNombreUsuario(this.nombrePersona);
            }
        }
    } 
}
