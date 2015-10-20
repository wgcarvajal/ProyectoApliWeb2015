/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.apliweb.beans;

import com.unicauca.apliweb.entities.Atiende;
import com.unicauca.apliweb.entities.AtiendePK;
import com.unicauca.apliweb.entities.Cambio;
import com.unicauca.apliweb.entities.Incidente;
import com.unicauca.apliweb.entities.Intentosolucion;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author miguel
 */
@Stateless
public class IncidenteFacade extends AbstractFacade<Incidente> {
    @PersistenceContext(unitName = "ProyectoApliWebPU")
    private EntityManager em;
        
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public IncidenteFacade() {
        super(Incidente.class);
    }

    public List<Incidente> buscarTodos(int id) 
    {            
        Query query = getEntityManager().createNamedQuery("Incidente.findByPerId");
        query.setParameter("perid",id);
        List<Incidente> resultquery =query.getResultList();
        return resultquery;  
    }

    public List<Incidente> buscarPorPendientes(int id) 
    {
        Query query = getEntityManager().createNamedQuery("Incidente.findByPerIdPendientes");
        query.setParameter("perid",id);
        List<Incidente> resultquery =query.getResultList();
        return resultquery;
    }

    public List<Incidente> buscarPorSolucionados(int id) 
    {
       Query query = getEntityManager().createNamedQuery("Incidente.findByPerIdSolucionados");
       query.setParameter("perid",id);
       List<Incidente> resultquery =query.getResultList();
       return resultquery;
    }
    
    public void registrar(Incidente incidente) {
        em.persist(incidente);
    }

    public List<Incidente> buscarPorPendientesFecha(int perid, Date fechainicial, Date fechafinal) 
    {
       Query query = getEntityManager().createNamedQuery("Incidente.findByPerIdPendientesFecha");
       query.setParameter("perid",perid);
       query.setParameter("fechainicial",fechainicial);
       query.setParameter("fechafinal",fechafinal);
       List<Incidente> resultquery =query.getResultList();
       return resultquery;
    }

    public List<Incidente> buscarPorSolucionadosFecha(int perid, Date fechainicial, Date fechafinal) 
    {
       Query query = getEntityManager().createNamedQuery("Incidente.findByPerIdSolucionadosFecha");
       query.setParameter("perid",perid);
       query.setParameter("fechainicial",fechainicial);
       query.setParameter("fechafinal",fechafinal);
       List<Incidente> resultquery =query.getResultList();
       return resultquery;
    }
    
    public List<Incidente> buscarPorFecha(int perid, Date fechainicial, Date fechafinal) 
    {
       Query query = getEntityManager().createNamedQuery("Incidente.findByPerIdFecha");
       query.setParameter("perid",perid);
       query.setParameter("fechainicial",fechainicial);
       query.setParameter("fechafinal",fechafinal);
       List<Incidente> resultquery =query.getResultList();
       return resultquery;
    }
    
    public List<Incidente> buscarIncidentes(String estado)
    {
        TypedQuery<Incidente> query;
        if(estado.equals("Todos"))
            query=em.createNamedQuery("Incidente.findAll",Incidente.class);       
        else
        {                        
            query=em.createNamedQuery("Incidente.findByIncsolucionado",Incidente.class);
            if(estado.equals("Pendiente"))
                query.setParameter("incsolucionado", false);
            else
                query.setParameter("incsolucionado", true);                
        }
        return query.getResultList();
    }

    public void guardarCambios(Incidente incidente, Cambio cambio, Intentosolucion solucion, int empPerId) {        
        Atiende atiende=new Atiende(incidente.getIncid(), empPerId, new Date());          
        try
        {
            em.persist(atiende);
        }
        catch(Exception ex)
        {
            System.out.println("Mi MSG: "+ex.getMessage());
        }
        em.persist(solucion);
        em.flush();        
        incidente.getIntentosolucionList().add(solucion);        
        em.merge(incidente);
        cambio.setIncidente(incidente);        
        em.persist(cambio);        
        
    }

    public void darDeAlta(Incidente incidente) {
        incidente.setIncsolucionado(true);
        em.merge(incidente);        
    }
    
}
