/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.apliweb.beans;

import com.unicauca.apliweb.entities.Cambio;
import com.unicauca.apliweb.entities.Incidente;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author miguel
 */
@Stateless
public class CambioFacade extends AbstractFacade<Cambio> {
    @PersistenceContext(unitName = "ProyectoApliWebPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CambioFacade() {
        super(Cambio.class);
    }

    public List<Cambio> obtnCambios(Incidente incidente) {
        TypedQuery query=em.createNamedQuery("Cambio.findByIncidASC", Cambio.class);
        query.setParameter("incId", incidente.getIncid());
        return query.getResultList();
    }
    
}
