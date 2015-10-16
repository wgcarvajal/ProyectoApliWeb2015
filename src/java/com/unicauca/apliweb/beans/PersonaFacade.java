/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.apliweb.beans;

import com.unicauca.apliweb.entities.Persona;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author geovanny
 */
@Stateless
public class PersonaFacade extends AbstractFacade<Persona> {
    @PersistenceContext(unitName = "ProyectoApliWebPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonaFacade() {
        super(Persona.class);
    }

    public boolean existsPerson(String perUser) {
        try
        {            
            TypedQuery<Persona> query =em.createNamedQuery("Persona.findByPeruser", Persona.class);
            query.setParameter("peruser", perUser);            
            query.getSingleResult();
            return true;
        }
        catch(NoResultException ex)
        {
            return false;
        }
        //return em.find(Persona.class, perid)!=null;
    }

    public void registrar(Persona persona) {
        em.persist(persona);
    }
    
    
    
}
