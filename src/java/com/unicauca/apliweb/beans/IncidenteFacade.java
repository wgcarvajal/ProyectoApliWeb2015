/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.apliweb.beans;

import com.unicauca.apliweb.entities.Incidente;
import java.security.Principal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
    
}
